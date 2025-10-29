// import java.util.*;

// public class Client extends Thread {
//     private final String nume;
//     private final Document documentFinal;
//     private final GrafDocumente graf;
//     private final List<Birou> birouri;

//     public Client(String nume, Document documentFinal, GrafDocumente graf, List<Birou> birouri) {
//         this.nume = nume;
//         this.documentFinal = documentFinal;
//         this.graf = graf;
//         this.birouri = birouri;
//     }
//     public String getNume() {
//         return nume;
//     }

//     @Override
//     public void run() {
//         System.out.println(nume + " doreste documentul final: " + documentFinal.getNume());

//         List<Document> plan;
//         try {
//             plan = graf.getOrdine(documentFinal);
//         } catch (Exception e) {
//             System.out.println(nume + " eroare: " + e.getMessage());
//             return;
//         }
//         System.out.println(nume + " trebuie sa obtina documentele in ordine: " +
//                 plan.stream().map(Document::getNume).toList());


//         for (Document doc : plan) {
//             Ghiseu ghiseu = alegeGhiseuPentru(doc);
//             if (ghiseu == null) {
//                 System.out.println(nume + " nu a gasit niciun ghiseu pentru " + doc.getNume());
//                 continue;
//             }

//             Cerere cerere = new Cerere(nume, doc);
//             ghiseu.adaugaCerere(cerere);
//             System.out.println(nume + " a trimis cerere pentru " + doc.getNume() + " la " + ghiseu.getNume());

//             cerere.asteaptaRezolvare();
//             System.out.println(nume + " a primit documentul: " + doc.getNume());
//         }

//         System.out.println(nume + " a terminat toate documentele necesare pentru " + documentFinal.getNume());
//     }

//     private Ghiseu alegeGhiseuPentru(Document doc) {
//         List<Ghiseu> toateGhiseele = new ArrayList<>();

//         // Găsește toate ghișeele din birourile care pot emite documentul
//         for (Birou birou : birouri) {
//             if (birou.poateEmite(doc)) {
//                 toateGhiseele.addAll(birou.getGhisee());
//             }
//         }

//         // Separă ghișeele active de cele în pauză
//         List<Ghiseu> active = new ArrayList<>();
//         for (Ghiseu g : toateGhiseele) {
//             if (!g.esteInPauza())
//                 active.add(g);
//         }

//         // 1. Există ghișeu activ cu coadă goală?
//         for (Ghiseu g : active) {
//             if (g.getDimensiuneCoada() == 0)
//                 return g;
//         }

//         // 2. Dacă nu, cel mai liber activ
//         if (!active.isEmpty()) {
//             return Collections.min(active, Comparator.comparingInt(Ghiseu::getDimensiuneCoada));
//         }

//         // 3. Dacă toate sunt în pauză, alegem ghișeul cu coada cea mai scurtă
//         return toateGhiseele.stream()
//                 .min(Comparator.comparingInt(Ghiseu::getDimensiuneCoada))
//                 .orElse(null);
//     }
// }
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

        // Verificare suplimentară: afișăm ordinea exactă în care clientul va cere
        // actele
        System.out.println(nume + " trebuie sa obtina documentele in ordine: " +
                plan.stream().map(Document::getNume).toList());

        // ⚠️ FOARTE IMPORTANT: parcurgem lista în ordine, nu invers
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

        // Găsim toate ghișeele care pot emite acest document
        for (Birou birou : birouri) {
            if (birou.poateEmite(doc)) {
                toateGhiseele.addAll(birou.getGhisee());
            }
        }

        // Separăm ghișeele active de cele în pauză
        List<Ghiseu> active = new ArrayList<>();
        for (Ghiseu g : toateGhiseele) {
            if (!g.esteInPauza())
                active.add(g);
        }

        // 1. Exista un ghișeu activ cu coadă goală?
        for (Ghiseu g : active) {
            if (g.getDimensiuneCoada() == 0)
                return g;
        }

        // 2. Cel mai liber dintre active
        if (!active.isEmpty()) {
            return Collections.min(active, Comparator.comparingInt(Ghiseu::getDimensiuneCoada));
        }

        // 3. Toate sunt în pauză → alegem ghișeul cu coada cea mai scurtă
        return toateGhiseele.stream()
                .min(Comparator.comparingInt(Ghiseu::getDimensiuneCoada))
                .orElse(null);
    }
}
