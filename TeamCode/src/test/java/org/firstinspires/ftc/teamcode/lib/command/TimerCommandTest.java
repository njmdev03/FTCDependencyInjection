package org.firstinspires.ftc.teamcode.lib.command;

import org.firstinspires.ftc.teamcode.mocks.MockElapsedTime;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TimerCommandTest {
    @Test
    void testNotCompletedBeforeTime() {
        MockElapsedTime timer = new MockElapsedTime();
        TimerCommand command = new TimerCommand(timer, 1000, TimeUnit.MILLISECONDS);

        command.init();

        timer.setTime(500, TimeUnit.MILLISECONDS);

        assertFalse(command.isCompleted());
    }

    @Test
    void testCompletedAtExactTime() {
        MockElapsedTime timer = new MockElapsedTime();
        TimerCommand command = new TimerCommand(timer, 1000, TimeUnit.MILLISECONDS);

        command.init();

        timer.setTime(1000, TimeUnit.MILLISECONDS);

        assertTrue(command.isCompleted());
    }

    @Test
    void testCompletedAfterTime() {
        MockElapsedTime timer = new MockElapsedTime();
        TimerCommand command = new TimerCommand(timer, 1000, TimeUnit.MILLISECONDS);

        command.init();

        timer.setTime(1500, TimeUnit.MILLISECONDS);

        assertTrue(command.isCompleted());
    }

    @Test
    void testResetIsCalledOnInit() {
        MockElapsedTime timer = new MockElapsedTime();
        timer.setTime(5000, TimeUnit.MILLISECONDS);

        TimerCommand command = new TimerCommand(timer, 1000, TimeUnit.MILLISECONDS);

        command.init();

        // After reset, time should be 0
        assertFalse(command.isCompleted());
    }

    @Test
    void testDifferentTimeUnits() {
        MockElapsedTime timer = new MockElapsedTime();
        TimerCommand command = new TimerCommand(timer, 2, TimeUnit.SECONDS);

        command.init();

        timer.setTime(1500, TimeUnit.MILLISECONDS);
        assertFalse(command.isCompleted());

        timer.setTime(2, TimeUnit.SECONDS);
        assertTrue(command.isCompleted());
    }
}
