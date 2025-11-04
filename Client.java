import java.util.*;

public class Client extends Thread {
    private final String nume;
    private final Document documentFinal;
    private final GrafDocumente graf;
    private final List<Birou> birouri;

    public Client(String nume, Document documentFinal, GrafDocumente graf, List<Birou> birouri) {
        this.nume = nume;
        this.documentFinal = documentFinal;
        this.graf = graf;
        this.birouri = birouri;
    }
    public String getNume() {
        return nume;
    }

    @Override
    public void run() {
        System.out.println(nume + " doreste documentul final: " + documentFinal.getNume());

        List<Document> plan;
        try {
            plan = graf.getOrdine(documentFinal);
        } catch (Exception e) {
            System.out.println(nume + " eroare: " + e.getMessage());
            return;
        }

        System.out.println(nume + " trebuie sa obtina documentele in ordine: " +
                plan.stream().map(Document::getNume).toList());

        
        for (Document doc : plan) {
            Ghiseu ghiseu = alegeGhiseuPentru(doc);
            if (ghiseu == null) {
                System.out.println(nume + " nu a gasit niciun ghiseu pentru " + doc.getNume());
                continue;
            }

            Cerere cerere = new Cerere(nume, doc);
            ghiseu.adaugaCerere(cerere);
            System.out.println(nume + " a trimis cerere pentru " + doc.getNume() + " la " + ghiseu.getNume());

            cerere.asteaptaRezolvare();
            System.out.println(nume + " a primit documentul: " + doc.getNume());
        }

        System.out.println(nume + " a terminat toate documentele necesare pentru " + documentFinal.getNume());
    }

    private Ghiseu alegeGhiseuPentru(Document doc) {
        List<Ghiseu> toateGhiseele = new ArrayList<>();

        // Gasim toate ghiseele care pot emite acest document
        for (Birou birou : birouri) {
            if (birou.poateEmite(doc)) {
                toateGhiseele.addAll(birou.getGhisee());
            }
        }

        // Separam ghiseele active de cele care sunt in pauza
       List<Ghiseu> active = new ArrayList<>();
        for (Ghiseu g : toateGhiseele) {
            if (!g.esteInPauza())
                active.add(g);
        }

        // Aici facem mai multe verificari pentru ca, clientul sa aleaga cel mai optim ghiseu
        // 1. Exista un ghiseu activ cu coada goala?
        for (Ghiseu g : active) {
            if (g.getDimensiuneCoada() == 0)
                return g;
        }

        // 2. Cel mai liber dintre active
        if (!active.isEmpty()) {
            return Collections.min(active, Comparator.comparingInt(Ghiseu::getDimensiuneCoada));
        }

        // 3. Toate sunt in pauza → alegem ghiseul cu coada cea mai scurta
        return toateGhiseele.stream()
                .min(Comparator.comparingInt(Ghiseu::getDimensiuneCoada))
                .orElse(null);
    }
}
