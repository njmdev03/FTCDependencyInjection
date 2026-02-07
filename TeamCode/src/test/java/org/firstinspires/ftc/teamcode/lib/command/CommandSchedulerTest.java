package org.firstinspires.ftc.teamcode.lib.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandSchedulerTest {

    private CommandScheduler scheduler;

    @BeforeEach
    void setUp() {
        scheduler = new CommandScheduler();
    }

    /**
     * Simple mock command for testing
     */
    static class TestCommand extends Command {

        int initCalls = 0;
        int loopCalls = 0;
        int loopsUntilComplete;
        boolean completed = false;

        TestCommand(int loopsUntilComplete) {
            this.loopsUntilComplete = loopsUntilComplete;
        }

        @Override
        public void init() {
            initCalls++;
        }

        @Override
        public void loop() {
            loopCalls++;
            if (loopCalls >= loopsUntilComplete) {
                completed = true;
            }
        }

        @Override
        public boolean isCompleted() {
            return completed;
        }
    }

    @Test
    void testSingleCommandExecution() {
        TestCommand command = new TestCommand(3);
        scheduler.add(command);

        scheduler.run(); // init + loop
        scheduler.run(); // loop
        scheduler.run(); // loop (complete)

        assertEquals(1, command.initCalls);
        assertEquals(3, command.loopCalls);
        assertTrue(scheduler.isCompleted());
    }

    @Test
    void testMultipleCommandsExecuteSequentially() {
        TestCommand command1 = new TestCommand(2);
        TestCommand command2 = new TestCommand(3);

        scheduler.add(command1, command2);

        // Run enough times to complete both
        for (int i = 0; i < 5; i++) {
            scheduler.run();
        }

        assertEquals(1, command1.initCalls);
        assertEquals(2, command1.loopCalls);

        assertEquals(1, command2.initCalls);
        assertEquals(3, command2.loopCalls);

        assertTrue(scheduler.isCompleted());
    }

    @Test
    void testRunDoesNothingWhenQueueEmpty() {
        scheduler.run();
        assertTrue(scheduler.isCompleted());
    }

    @Test
    void testInitOnlyCalledOncePerCommand() {
        TestCommand command = new TestCommand(5);
        scheduler.add(command);

        for (int i = 0; i < 5; i++) {
            scheduler.run();
        }

        assertEquals(1, command.initCalls);
    }
}
