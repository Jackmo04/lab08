package it.unibo.deathnote.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote {

    private List<DeathNoteEntry> entries;

    public DeathNoteImplementation() {
        this.entries = new LinkedList<>();
    }

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > DeathNote.RULES.size()) {
            throw new IllegalArgumentException("Rule number must be between 1 and 13");
        }
        return DeathNote.RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if (!name.isBlank() && !isNameWritten(name)) {
            this.entries.add(new DeathNoteEntry(name));
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.entries.contains(new DeathNoteEntry(name));
    }

    private class DeathNoteEntry {

        private String name;
        private String cause;
        private String details;

        private DeathNoteEntry(String name, String cause, String details) {
            this.name = Objects.requireNonNull(name);
            this.cause = Objects.requireNonNull(cause);
            this.details = Objects.requireNonNull(details);
        }

        private DeathNoteEntry(String name) {
            this(name, "heart attack", "");
        }

        private String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        private String getCause() {
            return cause;
        }

        private void setCause(String cause) {
            this.cause = cause;
        }

        private String getDetails() {
            return details;
        }

        private void setDetails(String details) {
            this.details = details;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DeathNoteEntry other = (DeathNoteEntry) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }

        private DeathNoteImplementation getEnclosingInstance() {
            return DeathNoteImplementation.this;
        }

        
       
    }
    
}