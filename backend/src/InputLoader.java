import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Loader pentru fisierul de intrare cu o sintaxa prietenoasa:
 *
 * Exemple de sectiuni:
 *
 * dependencies:
 * A: A1, A2
 * B: B1, B2
 *
 * birouri:
 * Birou A-B: A, A1, A2, B, B1, B2 | ghisee: Ghiseu A1, Ghiseu A2
 * Birou C-D: C, C1, C2, D, D1, D2 | ghisee: Ghiseu C1, Ghiseu C2
 * Birou E: E, E1, E2 | ghisee: Ghiseu E
 *
 * clients:
 * Ion: A
 * Maria: B
 */
public class InputLoader {

    private enum Section { NONE, DEPENDENCIES, BIROURI, CLIENTS, DOCUMENTS }

    public static InputData load(Path file) throws IOException {
        InputData data = new InputData();

        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            Section section = Section.NONE;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#") || line.startsWith("//")) {
                    continue;
                }

                String lower = line.toLowerCase(Locale.ROOT);
                if (lower.equals("dependencies:") || lower.equals("dependente:") || lower.equals("dependențe:")) {
                    section = Section.DEPENDENCIES;
                    continue;
                }
                if (lower.equals("birouri:")) {
                    section = Section.BIROURI;
                    continue;
                }
                if (lower.equals("clients:") || lower.equals("clienti:") || lower.equals("clienți:")) {
                    section = Section.CLIENTS;
                    continue;
                }
                if (lower.equals("documents:") || lower.equals("documente:")) {
                    // optional; in prezent nu este necesar deoarece deducem din dependente/birouri/clients
                    section = Section.DOCUMENTS;
                    continue;
                }

                switch (section) {
                    case DEPENDENCIES -> parseDependencyLine(line, data);
                    case BIROURI -> parseBirouLine(line, data);
                    case CLIENTS -> parseClientLine(line, data);
                    case DOCUMENTS -> {/* ignoram pentru simplitate */}
                    default -> {/* ignoram linii in afara sectiunilor */}
                }
            }
        }

        return data;
    }

    private static void parseDependencyLine(String line, InputData data) {
        // Format: Final: Dep1, Dep2, Dep3
        String[] parts = line.split(":", 2);
        if (parts.length != 2) return;
        String finalDoc = parts[0].trim();
        List<String> deps = splitList(parts[1]);
        data.dependente.put(finalDoc, deps);
    }

    private static void parseBirouLine(String line, InputData data) {
        // Format: NumeBirou: doc1, doc2 | ghisee: g1, g2
        String[] pipe = line.split("\\|", 2);
        String left = pipe[0].trim();
        String right = pipe.length > 1 ? pipe[1].trim() : "";

        String[] nameAndDocs = left.split(":", 2);
        if (nameAndDocs.length != 2) return;
        String nume = nameAndDocs[0].trim();
        Set<String> docs = new LinkedHashSet<>(splitList(nameAndDocs[1]));

        List<String> ghisee = new ArrayList<>();
        if (!right.isEmpty()) {
            String rr = right;
            // acceptam formele "ghisee:" sau "ghișee:" sau "windows:"
            rr = rr.replaceFirst("(?i)^ghisee:\\s*", "");
            rr = rr.replaceFirst("(?i)^ghișee:\\s*", "");
            rr = rr.replaceFirst("(?i)^windows:\\s*", "");
            ghisee = splitList(rr);
        }

        data.birouri.add(new InputData.BirouSpec(nume, docs, ghisee));
    }

    private static void parseClientLine(String line, InputData data) {
        // Format: Nume: DocFinal
        String[] parts = line.split(":", 2);
        if (parts.length != 2) return;
        String nume = parts[0].trim();
        String doc = parts[1].trim();
        data.clienti.add(new InputData.ClientSpec(nume, doc));
    }

    private static List<String> splitList(String s) {
        String[] arr = s.split(",");
        List<String> out = new ArrayList<>();
        for (String a : arr) {
            String t = a.trim();
            if (!t.isEmpty()) out.add(t);
        }
        return out;
    }
}
