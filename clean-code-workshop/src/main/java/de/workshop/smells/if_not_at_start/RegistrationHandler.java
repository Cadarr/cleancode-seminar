package de.workshop.smells.if_not_at_start;

public class RegistrationHandler {

    public String register(String email) {
        String normalized = email.trim().toLowerCase();
        String result = "Registrierung gestartet";
        if (!normalized.contains("@")) {
            result = "Ungueltige E-Mail";
        }
        return result;
    }
}
