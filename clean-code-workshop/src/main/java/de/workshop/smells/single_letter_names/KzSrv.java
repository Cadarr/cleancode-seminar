package de.workshop.smells.single_letter_names;

import java.util.List;

public class KzSrv {

    public String p(List<String> x) {
        String r = "";
        for (String s : x) {
            r = r + s.substring(0, 1).toUpperCase();
        }
        return r;
    }
}
