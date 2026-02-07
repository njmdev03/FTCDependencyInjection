package org.firstinspires.ftc.teamcode.lib.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Pose2DTest {

    @Test
    void testDefaultConstructor() {
        Pose2D p = new Pose2D();
        assertEquals(0, p.x);
        assertEquals(0, p.y);
        assertEquals(0, p.h);
    }

    @Test
    void testCopyConstructor() {
        Pose2D original = new Pose2D(1, 2, 3);
        Pose2D copy = new Pose2D(original);
        assertEquals(1, copy.x);
        assertEquals(2, copy.y);
        assertEquals(3, copy.h);
    }

    @Test
    void testAddSubtract() {
        Pose2D a = new Pose2D(1, 2, 3);
        Pose2D b = new Pose2D(4, 5, 6);

        Pose2D sum = a.add(b);
        assertEquals(5, sum.x);
        assertEquals(7, sum.y);
        assertEquals(9, sum.h);

        Pose2D diff = b.subtract(a);
        assertEquals(3, diff.x);
        assertEquals(3, diff.y);
        assertEquals(3, diff.h);
    }

    @Test
    void testDistanceTo() {
        Pose2D a = new Pose2D(0, 0, 0);
        Pose2D b = new Pose2D(3, 4, 0);
        assertEquals(5, a.distanceTo(b), 1e-9);
    }

    @Test
    void testPolarConstructorAndGetTheta() {
        double r = 2;
        double theta = Math.PI / 4; // 45 degrees
        Pose2D p = new Pose2D(r, theta);

        assertEquals(r * Math.cos(theta), p.x, 1e-9);
        assertEquals(r * Math.sin(theta), p.y, 1e-9);
        assertEquals(theta, p.getTheta(), 1e-9);
    }

    @Test
    void testToString() {
        Pose2D p = new Pose2D(1, 2, 3);
        assertEquals("(1.0, 2.0, 3.0)", p.toString());
    }
}
