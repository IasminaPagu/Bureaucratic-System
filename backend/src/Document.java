public class Document {
    private final String nume;

    public Document(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    @Override
    public String toString() {
        return nume;
    }
}
