package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote {

    private static final int DETAILS_DEADLINE_MILLIS = 640;
    private static final int CAUSE_DEADLINE_MILLIS = 40;
    private List<DeathNoteEntry> entries;
    private long lastWritingTimeMillis;

    public DeathNoteImplementation() {
        this.entries = new ArrayList<>();
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
            updateWritingTime();
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (this.entries.size() == 0) {
            throw new IllegalStateException("No names in the Death Note");
        }
        if (cause == null) {
            throw new IllegalStateException("Cause cannot be null");
        }
        if (!isBeforeDeadline(CAUSE_DEADLINE_MILLIS)) {
            return false;
        }
        this.entries.getLast().setCause(cause);
        updateWritingTime();
        return true;
    }

    @Override
    public boolean writeDetails(String details) {
        if (this.entries.size() == 0) {
            throw new IllegalStateException("No names in the Death Note");
        }
        if (details == null) {
            throw new IllegalStateException("Details cannot be null");
        }
        if (!isBeforeDeadline(DETAILS_DEADLINE_MILLIS)) {
            return false;
        }
        this.entries.getLast().setDetails(details);
        return true;
    }

    @Override
    public String getDeathCause(String name) {
        for (DeathNoteEntry entry : this.entries) {
            if (entry.getName().equals(name)) {
                return entry.getCause();
            }
        }
        throw new IllegalArgumentException("Name is not written in the Death Note");
    }

    @Override
    public String getDeathDetails(String name) {
        for (DeathNoteEntry entry : this.entries) {
            if (entry.getName().equals(name)) {
                return entry.getDetails();
            }
        }
        throw new IllegalArgumentException("Name is not written in the Death Note");
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.entries.contains(new DeathNoteEntry(name));
    }

    private void updateWritingTime() {
        this.lastWritingTimeMillis = System.currentTimeMillis();
    }

    private boolean isBeforeDeadline(int deadlineMillis) {
        return System.currentTimeMillis() - this.lastWritingTimeMillis < deadlineMillis;
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

        private String getCause() {
            return cause;
        }

        private void setCause(String cause) {
            this.cause = Objects.requireNonNull(cause);
        }

        private String getDetails() {
            return details;
        }

        private void setDetails(String details) {
            this.details = Objects.requireNonNull(details);
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