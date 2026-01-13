import java.util.*;

public class Client extends Thread {
    private final String nume;
    private final Document documentFinal;
    private final Supervisor supervisor;

    public Client(String nume, Document documentFinal, Supervisor supervisor) {
        this.nume = nume;
        this.documentFinal = documentFinal;
        this.supervisor = supervisor;
    }
    public String getNume() {
        return nume;
    }

    @Override
    public void run() {
        System.out.println(nume + " doreste documentul final: " + documentFinal.getNume());

        List<Document> plan;
        try {
            plan = supervisor.obtineOrdineDocumente(documentFinal);
        } catch (Exception e) {
            System.out.println(nume + " eroare: " + e.getMessage());
            return;
        }

        System.out.println(nume + " trebuie sa obtina documentele in ordine: " +
                plan.stream().map(Document::getNume).toList());

        
        for (Document doc : plan) {
            Ghiseu ghiseu = supervisor.alegeGhiseuPentru(doc);
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

    // Logica de alegere a ghiseului a fost mutata in Supervisor.
}
