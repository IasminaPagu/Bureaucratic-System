import java.util.*;

/**
 * Structuri simple pentru a tine configuratia citita din fisier.
 */
public class InputData {
    public static class BirouSpec {
        public final String nume;
        public final Set<String> documente;
        public final List<String> ghisee;

        public BirouSpec(String nume, Set<String> documente, List<String> ghisee) {
            this.nume = nume;
            this.documente = documente;
            this.ghisee = ghisee;
        }
    }

    public static class ClientSpec {
        public final String nume;
        public final String documentFinal;

        public ClientSpec(String nume, String documentFinal) {
            this.nume = nume;
            this.documentFinal = documentFinal;
        }
    }

    // Map docFinal -> lista documente intermediare
    public final Map<String, List<String>> dependente = new LinkedHashMap<>();
    public final List<BirouSpec> birouri = new ArrayList<>();
    public final List<ClientSpec> clienti = new ArrayList<>();
}
