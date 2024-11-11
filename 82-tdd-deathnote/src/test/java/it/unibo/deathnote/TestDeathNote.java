package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.commons.util.StringUtils;

import it.unibo.deathnote.impl.DeathNoteImplementation;
import it.unibo.deathnote.api.DeathNote;

import org.junit.jupiter.api.Test;

class TestDeathNote {

    private final static int NUM_OF_RULES = 13;

    @Test
    void testGetRule() {

        final DeathNote dn = new DeathNoteImplementation();

        Exception e = assertThrowsExactly(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                dn.getRule(0);
            }
        });
        assertNotNull(e.getMessage());
        assertTrue(StringUtils.isNotBlank(e.getMessage()));

        e = assertThrowsExactly(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                dn.getRule(-1);
            }
        });
        assertNotNull(e.getMessage());
        assertTrue(StringUtils.isNotBlank(e.getMessage()));

        e = assertThrowsExactly(IllegalArgumentException.class, new Executable() {
            @Override
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
}