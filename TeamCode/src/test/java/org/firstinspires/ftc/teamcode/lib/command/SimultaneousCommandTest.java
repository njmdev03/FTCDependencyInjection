package org.firstinspires.ftc.teamcode.lib.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimultaneousCommandTest {

    @Test
    void initCallsAllCommands() {
        MockCommand c1 = new MockCommand(5);
        MockCommand c2 = new MockCommand(5);

        SimultaneousCommand sim = new SimultaneousCommand(c1, c2);

        sim.init();

        assertEquals(1, c1.initCalls);
        assertEquals(1, c2.initCalls);
    }

    @Test
    void completesWhenAnyCommandCompletes() {
        MockCommand slow = new MockCommand(5);
        MockCommand fast = new MockCommand(2);

        SimultaneousCommand sim = new SimultaneousCommand(slow, fast);

        sim.init();

        sim.loop();
        assertFalse(sim.isCompleted());

        sim.loop(); // fast completes

        assertTrue(sim.isCompleted());
    }

    @Test
    void loopsAllCommandsEachCycle() {
        MockCommand c1 = new MockCommand(5);
        MockCommand c2 = new MockCommand(5);

        SimultaneousCommand sim = new SimultaneousCommand(c1, c2);

        sim.init();
        sim.loop();

        assertEquals(1, c1.loopCalls);
        assertEquals(1, c2.loopCalls);
    }
}
