import java.util.*;

public class Supervisor {
    private final GrafDocumente graf;
    private final List<Birou> birouri;

    public Supervisor(GrafDocumente graf, List<Birou> birouri) {
        this.graf = graf;
        this.birouri = birouri;
    }

    /**
     * Returneaza ordinea documentelor pe care trebuie sa le obtina un client
     * pentru a ajunge la documentul final (inclusiv intermediarele).
     */
    public List<Document> obtineOrdineDocumente(Document documentFinal) {
        return graf.getOrdine(documentFinal);
    }

    /**
     * Alege cel mai potrivit ghiseu pentru emiterea unui document.
     * Logica este exact cea din versiunea anterioara din Client.java,
     * doar mutata aici.
     */
    public Ghiseu alegeGhiseuPentru(Document doc) {
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
            if (!g.esteInPauza()) {
                active.add(g);
            }
        }

    // 1. Exista un ghiseu activ cu coada goala?
        for (Ghiseu g : active) {
            if (g.getDimensiuneCoada() == 0) {
                return g;
            }
        }

    // 2. Daca nu, alegem cel mai liber dintre active
        if (!active.isEmpty()) {
            return Collections.min(active, Comparator.comparingInt(Ghiseu::getDimensiuneCoada));
        }

    // 3. Daca toate sunt in pauza, alegem ghiseul cu coada cea mai scurta
        return toateGhiseele.stream()
                .min(Comparator.comparingInt(Ghiseu::getDimensiuneCoada))
                .orElse(null);
    }
}
