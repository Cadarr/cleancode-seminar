package de.workshop.smells.heavy_for_loop;

import java.util.List;

public class ReportAssembler {

    public String assemble(List<String> entries) {
        StringBuilder report = new StringBuilder();
        for (String entry : entries) {
            String cleaned = entry.trim();
            String upper = cleaned.toUpperCase();
            String withBrackets = "[" + upper + "]";
            String reversed = new StringBuilder(withBrackets).reverse().toString();
            int hash = reversed.hashCode();
            report.append(reversed).append(" -> ").append(hash).append("\n");
        }
        return report.toString();
    }
}
