package de.mvitz.kata.berlinclock;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import de.mvitz.kata.berlinclock.BerlinClock.Lamp;

public class BerlinClockTest {

    @Test
    public void secondLampIsOnEveryTwoSeconds() throws Exception {
        final BerlinClock clock = new BerlinClock(0);
        assertThat(clock.getSeconds()).isEqualTo(Lamp.YELLOW);
        clock.tick(); // 1
        assertThat(clock.getSeconds()).isEqualTo(Lamp.OFF);
        clock.tick(); // 2
        assertThat(clock.getSeconds()).isEqualTo(Lamp.YELLOW);
        clock.tick(2); // 4
        assertThat(clock.getSeconds()).isEqualTo(Lamp.YELLOW);
        clock.tick(3); // 7
        assertThat(clock.getSeconds()).isEqualTo(Lamp.OFF);
        clock.tick(52); // 59
        assertThat(clock.getSeconds()).isEqualTo(Lamp.OFF);
        clock.tick(); // 0
        assertThat(clock.getSeconds()).isEqualTo(Lamp.YELLOW);
        clock.tick(); // 1
        assertThat(clock.getSeconds()).isEqualTo(Lamp.OFF);
    }

}
