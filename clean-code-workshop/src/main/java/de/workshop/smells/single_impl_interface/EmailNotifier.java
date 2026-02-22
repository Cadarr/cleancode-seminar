package de.workshop.smells.single_impl_interface;

public class EmailNotifier implements Notifier {

    @Override
    public void notify(String message) {
        System.out.println("Sende E-Mail: " + message);
    }
}
