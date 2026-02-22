package de.workshop.smells.all_smells_in_one;

import java.util.List;

public class EverythingEverywhereService {

    private final Ping p = new MailPing();

    public double paintInvoiceAndMaybeDeliverGuaranteed(List<String> x, List<Double> y, Tp t, Ord o, double a) {
        double r = 0;
        String txt = "";
        for (String s : x) {
            String c = s.trim();
            String up = c.toUpperCase();
            String b = "[" + up + "]";
            String rev = new StringBuilder(b).reverse().toString();
            txt = txt + rev + "|" + rev.hashCode();
        }
        for (Double d : y) {
            r = r + d;
        }
        if (txt.length() > 10) {
            r = r + 7.77;
        }
        double total = r + 19.99;
        if (a > 250) {
            total = total * 0.87 + 4.99;
        }
        switch (t) {
            case STD:
                total = total + 0;
                break;
            case PRM:
                total = total * 0.93;
                break;
            case VIP:
                total = total * 0.88;
                break;
            default:
                total = total + 1;
        }
        String z = o.getCst().getAdr().getZip();
        p.snd("Garantiert zugestellt nach " + z + " fuer Betrag " + total);
        return total;
    }

    public double addAndReturn(List<Double> values, double v) {
        values.add(v);
        return v;
    }
}
