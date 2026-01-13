import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // 1) Citim fisierul de intrare (implicit "sample_input.txt" din folderul proiectului)
        String inputPath = args != null && args.length > 0 ? args[0] : "sample_input.txt";
        InputData data;
        try {
            data = InputLoader.load(Path.of(inputPath));
        } catch (Exception e) {
            System.out.println("Eroare la citirea fisierului '" + inputPath + "': " + e.getMessage());
            System.out.println("Iesire.");
            return;
        }

        // 2) Construim documentele
        Map<String, Document> docByName = new LinkedHashMap<>();
        // adunam toate numele de documente aparute in dependente, birouri si clienti
        for (Map.Entry<String, List<String>> e : data.dependente.entrySet()) {
            docByName.computeIfAbsent(e.getKey(), Document::new);
            for (String dep : e.getValue()) docByName.computeIfAbsent(dep, Document::new);
        }
        for (InputData.BirouSpec b : data.birouri) {
            for (String d : b.documente) docByName.computeIfAbsent(d, Document::new);
        }
        for (InputData.ClientSpec c : data.clienti) {
            docByName.computeIfAbsent(c.documentFinal, Document::new);
        }

        // 3) Construim graful de dependente
        GrafDocumente graf = new GrafDocumente();
        for (Document d : docByName.values()) {
            graf.adaugaDocument(d);
        }
        for (Map.Entry<String, List<String>> e : data.dependente.entrySet()) {
            Document fin = docByName.get(e.getKey());
            for (String dep : e.getValue()) {
                graf.adaugaDepedenta(fin, docByName.get(dep));
            }
        }

        System.out.println("Structura graf:");
        graf.afiseazaGraf();

        // 4) Construim birourile si ghiseele
        List<Birou> birouri = new ArrayList<>();
        List<Ghiseu> toateGhiseele = new ArrayList<>();
        for (InputData.BirouSpec b : data.birouri) {
            Set<Document> docs = new LinkedHashSet<>();
            for (String dn : b.documente) docs.add(docByName.get(dn));

            Birou birou = new Birou(b.nume, docs, new ArrayList<>());
            birouri.add(birou);

            // ghisee
            if (b.ghisee.isEmpty()) {
                // fallback: cream un ghiseu implicit daca nu e specificat niciunul
                Ghiseu g = new Ghiseu("Ghiseu - " + b.nume, birou);
                birou.getGhisee().add(g);
                toateGhiseele.add(g);
            } else {
                for (String gName : b.ghisee) {
                    Ghiseu g = new Ghiseu(gName, birou);
                    birou.getGhisee().add(g);
                    toateGhiseele.add(g);
                }
            }
        }

        // 5) Instantiem Supervisorul
        Supervisor supervisor = new Supervisor(graf, birouri);

        // 6) Pornim ghiseele
        List<Thread> workerThreads = new ArrayList<>();
        for (Ghiseu g : toateGhiseele) {
            workerThreads.add(g.proceseazaCereri());
        }

        // 7) Cream clientii din fisier si ii pornim
        List<Client> clienti = new ArrayList<>();
        for (InputData.ClientSpec c : data.clienti) {
            Document tinta = docByName.get(c.documentFinal);
            if (tinta == null) {
                System.out.println("[WARN] Document inexistent pentru clientul " + c.nume + ": " + c.documentFinal);
                continue;
            }
            clienti.add(new Client(c.nume, tinta, supervisor));
        }
        clienti.forEach(Thread::start);

        // 8) Asteptam clientii sa termine
        for (Client c : clienti) {
            try { c.join(); } catch (InterruptedException ignored) {}
        }

        // 9) Oprim ghiseele si asteptam inchiderea
        for (Ghiseu g : toateGhiseele) g.opreste();
        for (Thread t : workerThreads) {
            try { t.join(); } catch (InterruptedException ignored) {}
        }

        System.out.println("Sfarsitul programului");
    }
}
