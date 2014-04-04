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

    private long time;

    public BerlinClock(final int hours, final int minutes, final int seconds) {
        this.time = HOURS.toSeconds(hours) + MINUTES.toSeconds(minutes) + seconds;
    }

    Lamp getSeconds() {
        return glowYellow(seconds() % 2 == 0);
    }

    Lamp[] getTopHours() {
        return lamps(
                glowRed(hours() > 4),
                glowRed(hours() > 9),
                glowRed(hours() > 14),
                glowRed(hours() > 19));
    }

    Lamp[] getBottomHours() {
        return lamps(
                glowRed(hours() % 5 > 0),
                glowRed(hours() % 5 > 1),
                glowRed(hours() % 5 > 2),
                glowRed(hours() % 5 > 3));
    }

    private long seconds() {
        return time;
    }

    private long hours() {
        return SECONDS.toHours(seconds()) % 24;
    }

    public void tick() {
        tick(1);
    }

    void tick(final int seconds) {
        tick(seconds, SECONDS);
    }

    void tick(final int duration, final TimeUnit unit) {
        this.time += unit.toSeconds(duration);
    }

    private Lamp glowYellow(final boolean expression) {
        return glow(YELLOW, expression);
    }

    private Lamp glowRed(final boolean expression) {
        return glow(RED, expression);
    }

    private Lamp glow(final Lamp lamp, final boolean expression) {
        return expression ? lamp : OFF;
    }

    private Lamp[] lamps(final Lamp... lamps) {
        return lamps;
    }

}
