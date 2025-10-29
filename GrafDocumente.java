import java.util.*;

public class GrafDocumente {
    private final Map<Document, Set<Document>> dependente = new HashMap<>();

    public void adaugaDocument(Document doc) {
        dependente.putIfAbsent(doc, new LinkedHashSet<>());
    }

    public void adaugaDepedenta(Document docFinal, Document docIntermediar) {
        dependente.putIfAbsent(docFinal, new LinkedHashSet<>());
        dependente.putIfAbsent(docIntermediar, new LinkedHashSet<>());
        dependente.get(docFinal).add(docIntermediar);
    }

    // public void adaugaDocument(Document doc) {
    //     dependente.putIfAbsent(doc, new HashSet<>());
    // }

    // public void adaugaDepedenta(Document docFinal, Document docIntermediar) {
    //     dependente.putIfAbsent(docFinal, new HashSet<>());
    //     dependente.putIfAbsent(docIntermediar, new HashSet<>());
    //     dependente.get(docFinal).add(docIntermediar);
    // }

    public List<Document> getOrdine(Document tinta) {
        Set<Document> vizitat = new HashSet<>();
        Set<Document> inStack = new HashSet<>();
        List<Document> rezultat = new ArrayList<>();

        dfs(tinta, vizitat, inStack, rezultat);
        // Collections.reverse(rezultat);
        return rezultat;
    }

    private void dfs(Document curent, Set<Document> vizitat, Set<Document> inStack, List<Document> rezultat) {
        if (inStack.contains(curent)) {
            throw new RuntimeException("Ciclu detectat în graf! Document: " + curent.getNume());
        }

        if (vizitat.contains(curent)) return;

        inStack.add(curent);
        for (Document dep : dependente.getOrDefault(curent, Set.of())) {
            dfs(dep, vizitat, inStack, rezultat);
        }
        inStack.remove(curent);
        vizitat.add(curent);
        rezultat.add(curent);
    }

    public void afiseazaGraf() {
        for (Map.Entry<Document, Set<Document>> entry : dependente.entrySet()) {
            System.out.println(entry.getKey().getNume() + " depinde de: " +
                    entry.getValue().stream().map(Document::getNume).toList());
        }
    }
}
