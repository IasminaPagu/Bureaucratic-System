import java.util.List;
import java.util.Set;

public class Birou {
    private final String nume;
    private final Set<Document> documenteEmise;
    private final List<Ghiseu> ghisee;

    public Birou(String nume, Set<Document> documenteEmise, List<Ghiseu> ghisee) {
        this.nume = nume;
        this.documenteEmise = documenteEmise;
        this.ghisee = ghisee;
    }

    public String getNume() {
        return nume;
    }

    public Set<Document> getDocumenteEmise() {
        return documenteEmise;
    }

    public List<Ghiseu> getGhisee() {
        return ghisee;
    }

    public boolean poateEmite(Document doc) {
        return documenteEmise.contains(doc);
    }

    @Override
    public String toString() {
        return "Birou{" +
                "nume='" + nume + '\'' +
                ", documenteEmise=" + documenteEmise +
                ", ghisee=" + ghisee +
                '}';
    }
}
