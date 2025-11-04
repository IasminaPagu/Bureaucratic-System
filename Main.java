import java.util.*;

public class Main {
    public static void main(String[] args) {

        Document A = new Document("A");
        Document B = new Document("B");
        Document C = new Document("C");
        Document D = new Document("D");
        Document E = new Document("E");

        Document A1 = new Document("A1");
        Document B1 = new Document("B1");
        Document C1 = new Document("C1");
        Document D1 = new Document("D1");
        Document E1 = new Document("E1");

        Document A2 = new Document("A2");
        Document B2 = new Document("B2");
        Document C2 = new Document("C2");
        Document D2 = new Document("D2");
        Document E2 = new Document("E2");

        GrafDocumente graf = new GrafDocumente();
        List<Document> toateDocumentele = List.of(
                A, B, C, D, E,
                A1, A2, B1, B2, C1, C2, D1, D2, E1, E2);
        toateDocumentele.forEach(graf::adaugaDocument);

        graf.adaugaDepedenta(A, A1);
        graf.adaugaDepedenta(A, A2);
        graf.adaugaDepedenta(B, B1);
        graf.adaugaDepedenta(B, B2);
        graf.adaugaDepedenta(C, C1);
        graf.adaugaDepedenta(C, C2);
        graf.adaugaDepedenta(D, D1);
        graf.adaugaDepedenta(D, D2);
        graf.adaugaDepedenta(E, E1);
        graf.adaugaDepedenta(E, E2);

        System.out.println("Structura graf:");
        graf.afiseazaGraf();

        List<Birou> birouri = new ArrayList<>();

        Birou birou1 = new Birou("Birou A-B",
                Set.of(A, A1, A2, B, B1, B2),
                new ArrayList<>());

        Birou birou2 = new Birou("Birou C-D",
                Set.of(C, C1, C2, D, D1, D2),
                new ArrayList<>());

        Birou birou3 = new Birou("Birou E",
                Set.of(E, E1, E2),
                new ArrayList<>());

        birouri.add(birou1);
        birouri.add(birou2);
        birouri.add(birou3);

    // Instantiem Supervisorul care detine graful si birourile
    Supervisor supervisor = new Supervisor(graf, birouri);

        Ghiseu g1 = new Ghiseu("Ghiseu A1", birou1);
        Ghiseu g2 = new Ghiseu("Ghiseu A2", birou1);
        Ghiseu g3 = new Ghiseu("Ghiseu C1", birou2);
        Ghiseu g4 = new Ghiseu("Ghiseu C2", birou2);
        Ghiseu g5 = new Ghiseu("Ghiseu E", birou3);

        birou1.getGhisee().addAll(List.of(g1, g2));
        birou2.getGhisee().addAll(List.of(g3, g4));
        birou3.getGhisee().add(g5);

    // Porneste firele ghiseelor si pastreaza-le pentru oprire/join
        Thread t1 = g1.proceseazaCereri();
        Thread t2 = g2.proceseazaCereri();
        Thread t3 = g3.proceseazaCereri();
        Thread t4 = g4.proceseazaCereri();
        Thread t5 = g5.proceseazaCereri();


    Client client1 = new Client("Ion", A, supervisor);
    Client client2 = new Client("Maria", B, supervisor);
    Client client3 = new Client("George", C, supervisor);
    Client client4 = new Client("Andreea", D, supervisor);
    Client client5 = new Client("Vasile", E, supervisor);

        List<Client> clienti = List.of(client1, client2, client3, client4, client5);
        clienti.forEach(Thread::start);

        clienti.forEach(c -> {
            try {
                c.join();
            } catch (InterruptedException e) {
                System.out.println("Thread intrerupt: " + c.getNume());
            }
        });

        // Opreste ghiseele si asteapta inchiderea lor
        g1.opreste(); g2.opreste(); g3.opreste(); g4.opreste(); g5.opreste();
        try {
            t1.join(); t2.join(); t3.join(); t4.join(); t5.join();
        } catch (InterruptedException e) {
            System.out.println("Asteptare ghisee intrerupta.");
        }

        System.out.println("Sfarsitul programului");
    }
}
