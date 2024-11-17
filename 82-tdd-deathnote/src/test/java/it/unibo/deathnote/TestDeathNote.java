package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.function.Executable;
import org.junit.platform.commons.util.StringUtils;

import it.unibo.deathnote.impl.DeathNoteImplementation;
import it.unibo.deathnote.api.DeathNote;

import org.junit.jupiter.api.Test;

class TestDeathNote {

    private final static int NUM_OF_RULES = DeathNote.RULES.size();

    @Test
    void testGetRule() {

        final DeathNote dn = new DeathNoteImplementation();

        Exception e = assertThrowsExactly(IllegalArgumentException.class, new Executable() {
            public void execute() throws Throwable {
                dn.getRule(0);
            }
        });
        assertNotNull(e.getMessage());
        assertTrue(StringUtils.isNotBlank(e.getMessage()));

        e = assertThrowsExactly(IllegalArgumentException.class, new Executable() {
            public void execute() throws Throwable {
                dn.getRule(-1);
            }
        });
        assertNotNull(e.getMessage());
        assertTrue(StringUtils.isNotBlank(e.getMessage()));

        e = assertThrowsExactly(IllegalArgumentException.class, new Executable() {
            public void execute() throws Throwable {
                dn.getRule(NUM_OF_RULES + 1);
            }
        });
        assertNotNull(e.getMessage());
        assertTrue(StringUtils.isNotBlank(e.getMessage()));

        for (int i = 1; i <= NUM_OF_RULES; i++) {
            assertNotNull(dn.getRule(i));
            assertTrue(StringUtils.isNotBlank(dn.getRule(i)));
        }
    }

    @Test
    void testWriteName() {
        final DeathNote dn = new DeathNoteImplementation();
        final var name = "Pippo";
        assertFalse(dn.isNameWritten(name));
        dn.writeName(name);
        assertTrue(dn.isNameWritten(name));
        assertFalse(dn.isNameWritten("Paperino"));
        dn.writeName("");
        assertFalse(dn.isNameWritten(""));
        assertThrowsExactly(NullPointerException.class, new Executable() {
            public void execute() throws Throwable {
                dn.writeName(null);
            }
        });
    }

    @Test
    void testWriteCause() throws InterruptedException {
        final DeathNote dn = new DeathNoteImplementation();
        assertThrowsExactly(IllegalStateException.class, new Executable() {
            public void execute() throws Throwable {
                dn.writeDeathCause("accident");
            }
        });
        assertThrowsExactly(IllegalStateException.class, new Executable() {
            public void execute() throws Throwable {
                dn.writeDeathCause(null);
            }
        });
        final var namePippo = "pippo";
        dn.writeName(namePippo);
        assertEquals("heart attack", dn.getDeathCause(namePippo).toLowerCase());
        final var namePluto = "pluto";
        dn.writeName(namePluto);
        final var dcAccident = "karting accident";
        assertTrue(dn.writeDeathCause(dcAccident));
        assertEquals(dcAccident, dn.getDeathCause(namePluto).toLowerCase());
        Thread.sleep(100);
        final var dcSuicide = "suicide";
        assertFalse(dn.writeDeathCause(dcSuicide));
        assertNotEquals(dcSuicide, dn.getDeathCause(namePluto).toLowerCase());
    }

    @Test
    void testWriteDetails() throws InterruptedException {
        final DeathNote dn = new DeathNoteImplementation();
        assertThrowsExactly(IllegalStateException.class, new Executable() {
            public void execute() throws Throwable {
                dn.writeDetails("while swimming");
            }
        });
        assertThrowsExactly(IllegalStateException.class, new Executable() {
            public void execute() throws Throwable {
                dn.writeDetails(null);
            }
        });
        final var namePippo = "pippo";
        dn.writeName(namePippo);
        assertEquals("", dn.getDeathDetails(namePippo));
        final var detailsRan = "ran for too long";
        assertTrue(dn.writeDetails(detailsRan));
        assertEquals(detailsRan, dn.getDeathDetails(namePippo).toLowerCase());
        final var namePluto = "pluto";
        dn.writeName(namePluto);
        Thread.sleep(6100);
        assertFalse(dn.writeDetails("at work"));
        assertEquals("", dn.getDeathDetails(namePluto));
    }

    
}