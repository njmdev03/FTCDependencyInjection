package org.firstinspires.ftc.teamcode.lib.pid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PIDControllerSpeedLimitTest {

    @Test
    void clampsPositiveOutput() {
        PIDConstants constants = new PIDConstants(10.0, 0.0, 0.0);
        PIDControllerSpeedLimit controller =
                new PIDControllerSpeedLimit(constants, 10, 0.1, 5);

        double output = controller.calculate(0); // error = 10 → 100 output
        assertEquals(5.0, output, 1e-9);
    }

    @Test
    void clampsNegativeOutput() {
        PIDConstants constants = new PIDConstants(10.0, 0.0, 0.0);
        PIDControllerSpeedLimit controller =
                new PIDControllerSpeedLimit(constants, 0, 0.1, 5);

        double output = controller.calculate(10); // error = -10 → -100
        assertEquals(-5.0, output, 1e-9);
    }

    @Test
    void doesNotClampWhenWithinLimit() {
        PIDConstants constants = new PIDConstants(1.0, 0.0, 0.0);
        PIDControllerSpeedLimit controller =
                new PIDControllerSpeedLimit(constants, 10, 0.1, 5);

        double output = controller.calculate(8); // error = 2
        assertEquals(2.0, output, 1e-9);
    }
}
