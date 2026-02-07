package org.firstinspires.ftc.teamcode.lib.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequentialCommandTest {

    @Test
    void executesCommandsInOrder() {
        MockCommand cmd1 = new MockCommand(2);
        MockCommand cmd2 = new MockCommand(3);

        SequentialCommand sequential = new SequentialCommand(cmd1, cmd2);

        for (int i = 0; i < 5; i++) {
            sequential.loop();
        }

        assertEquals(1, cmd1.initCalls);
        assertEquals(2, cmd1.loopCalls);

        assertEquals(1, cmd2.initCalls);
        assertEquals(3, cmd2.loopCalls);

        assertTrue(sequential.isCompleted());
    }

    @Test
    void secondDoesNotStartUntilFirstCompletes() {
        MockCommand cmd1 = new MockCommand(3);
        MockCommand cmd2 = new MockCommand(1);

        SequentialCommand sequential = new SequentialCommand(cmd1, cmd2);

        sequential.loop();
        sequential.loop();

        assertEquals(2, cmd1.loopCalls);
        assertEquals(0, cmd2.initCalls);
    }
}
