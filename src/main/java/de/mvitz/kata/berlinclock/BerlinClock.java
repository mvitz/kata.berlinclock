package de.mvitz.kata.berlinclock;

import static de.mvitz.kata.berlinclock.BerlinClock.Lamp.OFF;
import static de.mvitz.kata.berlinclock.BerlinClock.Lamp.RED;
import static de.mvitz.kata.berlinclock.BerlinClock.Lamp.YELLOW;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.TimeUnit;

public class BerlinClock {

    public static enum Lamp {
        YELLOW("Y"), RED("R"), OFF("O");
        private final String mnemonic;

        private Lamp(final String mnemonic) {
            this.mnemonic = mnemonic;
        }

        @Override
        public String toString() {
            return mnemonic;
        }
    }

    private long seconds;

    public BerlinClock(final int hours, final int minutes, final int seconds) {
        this.seconds = HOURS.toSeconds(hours) + MINUTES.toSeconds(minutes) + seconds;
    }

    Lamp getSeconds() {
        return glow(YELLOW, seconds % 2 == 0);
    }

    public void tick() {
        tick(1);
    }

    void tick(final int seconds) {
        tick(seconds, SECONDS);
    }

    void tick(final int duration, final TimeUnit unit) {
        this.seconds += unit.toSeconds(duration);
    }

    Lamp[] getTopHours() {
        final long hours = SECONDS.toHours(seconds) % 24;
        return new Lamp[] {
                glow(RED, hours > 4),
                glow(RED, hours > 9),
                glow(RED, hours > 14),
                glow(RED, hours > 19) };
    }

    private Lamp glow(final Lamp lamp, final boolean expression) {
        return expression ? lamp : OFF;
    }

}
