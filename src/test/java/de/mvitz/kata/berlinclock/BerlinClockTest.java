package de.mvitz.kata.berlinclock;

import static de.mvitz.kata.berlinclock.BerlinClock.Lamp.OFF;
import static de.mvitz.kata.berlinclock.BerlinClock.Lamp.RED;
import static de.mvitz.kata.berlinclock.BerlinClock.Lamp.YELLOW;
import static java.util.concurrent.TimeUnit.HOURS;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class BerlinClockTest {

    private final BerlinClock clock = new BerlinClock(0, 0, 0);

    @Test
    public void secondLampIsOnEveryTwoSeconds() throws Exception {
        assertThat(clock.getSeconds()).as("Seconds: %s", 0).isEqualTo(YELLOW);
        clock.tick();
        assertThat(clock.getSeconds()).as("Seconds: %s", 1).isEqualTo(OFF);
        clock.tick();
        assertThat(clock.getSeconds()).as("Seconds: %s", 2).isEqualTo(YELLOW);
        clock.tick(2);
        assertThat(clock.getSeconds()).as("Seconds: %s", 4).isEqualTo(YELLOW);
        clock.tick(3);
        assertThat(clock.getSeconds()).as("Seconds: %s", 7).isEqualTo(OFF);
        clock.tick(52);
        assertThat(clock.getSeconds()).as("Seconds: %s", 59).isEqualTo(OFF);
        clock.tick();
        assertThat(clock.getSeconds()).as("Seconds: %s", 0).isEqualTo(YELLOW);
        clock.tick();
        assertThat(clock.getSeconds()).as("Seconds: %s", 1).isEqualTo(OFF);
    }

    @Test
    public void topHourLampAddedEveryFiveHours() throws Exception {
        // 00 - 04
        for (int i = 0; i < 5; i++) {
            assertThat(clock.getTopHours()).as("Hour: %s", i).containsExactly(OFF, OFF, OFF, OFF);
            clock.tick(1, HOURS);
        }
        // 05 - 09
        for (int i = 5; i < 10; i++) {
            assertThat(clock.getTopHours()).as("Hour: %s", i).containsExactly(RED, OFF, OFF, OFF);
            clock.tick(1, HOURS);
        }
        // 10 - 14
        for (int i = 10; i < 15; i++) {
            assertThat(clock.getTopHours()).as("Hour: %s", i).containsExactly(RED, RED, OFF, OFF);
            clock.tick(1, HOURS);
        }
        // 15 - 19
        for (int i = 15; i < 20; i++) {
            assertThat(clock.getTopHours()).as("Hour: %s", i).containsExactly(RED, RED, RED, OFF);
            clock.tick(1, HOURS);
        }
        // 20 - 23
        for (int i = 20; i < 24; i++) {
            assertThat(clock.getTopHours()).as("Hour: %s", i).containsExactly(RED, RED, RED, RED);
            clock.tick(1, HOURS);
        }
        // 24 -> 00
        assertThat(clock.getTopHours()).as("Hour: 24").containsExactly(OFF, OFF, OFF, OFF);
    }
}
