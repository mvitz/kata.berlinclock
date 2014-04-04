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
        YELLOW, RED, OFF;
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

    Lamp[] getTopMinutes() {
        return lamps(
                glowYellow(minutes() > 4),
                glowYellow(minutes() > 9),
                glowRed(minutes() > 14),
                glowYellow(minutes() > 19),
                glowYellow(minutes() > 24),
                glowRed(minutes() > 29),
                glowYellow(minutes() > 34),
                glowYellow(minutes() > 39),
                glowRed(minutes() > 44),
                glowYellow(minutes() > 49),
                glowYellow(minutes() > 54));
    }

    private long seconds() {
        return time;
    }

    private long hours() {
        return SECONDS.toHours(seconds()) % 24;
    }

    private long minutes() {
        return SECONDS.toMinutes(seconds()) % 60;
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
