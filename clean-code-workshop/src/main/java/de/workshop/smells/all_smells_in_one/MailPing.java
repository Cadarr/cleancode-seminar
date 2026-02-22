package de.workshop.smells.all_smells_in_one;

public class MailPing implements Ping {

    @Override
    public void snd(String m) {
        System.out.println("MAIL>> " + m);
    }
}
