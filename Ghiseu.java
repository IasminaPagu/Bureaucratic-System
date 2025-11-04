import java.util.LinkedList;
import java.util.Queue;

public class Ghiseu {
    private final String nume;
    private final Birou birou;
    private final Queue<Cerere> coada = new LinkedList<>();
    private boolean inPauza = false;
    // worker thread and running flag to allow clean shutdown
    private Thread worker;
    private volatile boolean ruleaza = false;

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
        notifyAll(); 
    }
    /**
     * Pornește un thread worker care procesează cererile și returnează referința
     * pentru a putea face join mai târziu. Dacă este deja pornit, returnează
     * thread-ul existent.
     */
    public synchronized Thread proceseazaCereri() {
        if (worker != null && worker.isAlive()) return worker;

        ruleaza = true;
        worker = new Thread(() -> {
            while (ruleaza) {
                Cerere cerere = null;

                synchronized (this) {
                    while (coada.isEmpty() && ruleaza) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            // ieșim din așteptare; bucla verifică flagul
                        }
                    }
                    if (!ruleaza) break;
                    cerere = coada.poll();
                }

                if (cerere == null) continue;

                if (Math.random() < 0.2) {
                    inPauza = true;
                    System.out.println(nume + " de la " + birou.getNume() + " a intrat în pauza de cafea.");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) { }
                    inPauza = false;
                    System.out.println(nume + " a revenit din pauza.");
                }

                System.out.println(nume + " de la " + birou.getNume() + " proceseaza cererea: " + cerere);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) { }

                cerere.marcheazaRezolvata();
                System.out.println(nume + " a finalizat cererea pentru: " + cerere.getNumeClient());
            }
            System.out.println(nume + " s-a oprit.");
        }, "Ghiseu-" + nume);

        worker.start();
        return worker;
    }

    /** Oprește worker-ul; sigur de apelat de mai multe ori. */
    public synchronized void opreste() {
        ruleaza = false;
        if (worker != null) {
            worker.interrupt();
        }
        notifyAll();
    }

    public boolean esteInPauza() {
        return inPauza;
    }
}
