import java.util.LinkedList;
import java.util.Queue;

public class Ghiseu {
    private final String nume;
    private final Birou birou;
    private final Queue<Cerere> coada = new LinkedList<>();
    private boolean inPauza = false;

    public Ghiseu(String nume, Birou birou) {
        this.nume = nume;
        this.birou = birou;
    }

    public String getNume() {
        return nume;
    }

    public Birou getBirou() {
        return birou;
    }

    public synchronized int getDimensiuneCoada() {
        return coada.size();
    }

    public synchronized void adaugaCerere(Cerere cerere) {
        coada.add(cerere);
        notify(); // trezește ghișeul dacă aștepta o cerere
    }

    public void proceseazaCereri() {
        new Thread(() -> {
            while (true) {
                Cerere cerere;

                synchronized (this) {
                    while (coada.isEmpty()) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            System.out.println(nume + " intrerupt.");
                            return;
                        }
                    }
                    cerere = coada.poll();
                }

                // pauză de cafea (20% șansă)
                if (Math.random() < 0.2) {
                    inPauza = true;
                    System.out.println(nume + " de la " + birou.getNume() + " a intrat în pauza de cafea.");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    inPauza = false;
                    System.out.println(nume + " a revenit din pauza.");
                }

                System.out.println(nume + " de la " + birou.getNume() + " proceseaza cererea: " + cerere);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }

                cerere.marcheazaRezolvata();
                System.out.println(nume + " a finalizat cererea pentru: " + cerere.getNumeClient());
            }
        }).start();
    }

    public boolean esteInPauza() {
        return inPauza;
    }
}
