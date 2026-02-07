package org.firstinspires.ftc.teamcode.lib.pid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PIDControllerTest {

    @Test
    void proportionalOnlyWorks() {
        PIDConstants constants = new PIDConstants(2.0, 0.0, 0.0);
        PIDController controller = new PIDController(constants, 10, 0.1);

        double output = controller.calculate(8); // error = 2
        assertEquals(4.0, output, 1e-9);
    }

    @Test
    void integralAccumulates() {
        PIDConstants constants = new PIDConstants(0.0, 1.0, 0.0);
        PIDController controller = new PIDController(constants, 10, 0.1);

        controller.calculate(8); // error = 2
        double output = controller.calculate(8); // error = 2 again

        // integral = 4
        assertEquals(4.0, output, 1e-9);
    }

    @Test
    void derivativeRespondsToChange() {
        PIDConstants constants = new PIDConstants(0.0, 0.0, 1.0);
        PIDController controller = new PIDController(constants, 10, 0.1);

        controller.calculate(9);  // error = 1
        double output = controller.calculate(8);  // error = 2 (change = +1)

        assertEquals(1.0, output, 1e-9);
    }

    @Test
    void atTargetWithinTolerance() {
        PIDConstants constants = new PIDConstants(1,0,0);
        PIDController controller = new PIDController(constants, 10, 0.5);

        assertTrue(controller.atTarget(10.3));
        assertFalse(controller.atTarget(9.0));
    }
}
