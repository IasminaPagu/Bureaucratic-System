public class Cerere {
    private final String numeClient;
    private final Document documentCerut;
    private boolean rezolvata = false;

    public Cerere(String numeClient, Document documentCerut) {
        this.numeClient = numeClient;
        this.documentCerut = documentCerut;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public Document getDocumentCerut() {
        return documentCerut;
    }

    public synchronized boolean esteRezolvata() {
        return rezolvata;
    }

    public synchronized void marcheazaRezolvata() {
        this.rezolvata = true;
        notify(); // trezim clientul care așteaptă
    }

    public synchronized void asteaptaRezolvare() {
        while (!rezolvata) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Clientul a fost intrerupt in timp ce astepta raspunsul");
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Cerere{" +
                "client='" + numeClient + '\'' +
                ", document=" + documentCerut +
                ", rezolvata=" + rezolvata +
                '}';
    }
}
