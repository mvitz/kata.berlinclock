package de.mvitz.kata.berlinclock;

public class BerlinClock {

    public static enum Lamp {
        YELLOW("Y"), OFF("O");
        private final String mnemonic;

        private Lamp(final String mnemonic) {
            this.mnemonic = mnemonic;
        }

        @Override
        public String toString() {
            return mnemonic;
        }
    }

    private int seconds;

    public BerlinClock(final int seconds) {
        this.seconds = seconds;
    }

    Lamp getSeconds() {
        return seconds % 2 == 0 ? Lamp.YELLOW : Lamp.OFF;
    }

    public void tick() {
        tick(1);
    }

    void tick(final int seconds) {
        this.seconds = (this.seconds + seconds) % 60;
    }

}
