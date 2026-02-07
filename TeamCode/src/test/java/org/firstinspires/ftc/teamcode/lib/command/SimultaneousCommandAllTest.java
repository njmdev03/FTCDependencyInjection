package org.firstinspires.ftc.teamcode.lib.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimultaneousCommandAllTest {

    @Test
    void doesNotCompleteUntilAllCommandsComplete() {
        MockCommand slow = new MockCommand(5);
        MockCommand fast = new MockCommand(2);

        SimultaneousCommandAll simAll =
                new SimultaneousCommandAll(slow, fast);

        simAll.init();

        for (int i = 0; i < 2; i++) {
            simAll.loop();
        }

        assertFalse(simAll.isCompleted());

        for (int i = 0; i < 3; i++) {
            simAll.loop();
        }

        assertTrue(simAll.isCompleted());
    }
}
