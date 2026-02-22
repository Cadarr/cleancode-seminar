package de.workshop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void shouldReturnHalloWelt() {
        assertEquals("Hallo Welt", App.getGreeting());
    }
}
