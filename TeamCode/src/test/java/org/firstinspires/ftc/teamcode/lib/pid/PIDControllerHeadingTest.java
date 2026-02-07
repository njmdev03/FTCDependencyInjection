package org.firstinspires.ftc.teamcode.lib.pid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PIDControllerHeadingTest {

    @Test
    void wrapsAroundPositiveBoundary() {
        PIDConstants constants = new PIDConstants(1.0, 0.0, 0.0);

        PIDControllerHeading controller =
                new PIDControllerHeading(constants, 170, 1, 100);

        // Current at -170 (difference 340 degrees)
        double output = controller.calculate(-170);

        // Should wrap to shortest path (~20 degrees)
        assertTrue(Math.abs(output) < 180);
    }

    @Test
    void wrapsAroundNegativeBoundary() {
        PIDConstants constants = new PIDConstants(1.0, 0.0, 0.0);

        PIDControllerHeading controller =
                new PIDControllerHeading(constants, -170, 1, 100);

        double output = controller.calculate(170);

        assertTrue(Math.abs(output) < 180);
    }

    @Test
    void outputIsNegatedFromParent() {
        PIDConstants constants = new PIDConstants(1.0, 0.0, 0.0);

        PIDControllerHeading controller =
                new PIDControllerHeading(constants, 10, 1, 100);

        double output = controller.calculate(0);

        // normal P output would be +10
        assertEquals(-10.0, output, 1e-9);
    }
}
