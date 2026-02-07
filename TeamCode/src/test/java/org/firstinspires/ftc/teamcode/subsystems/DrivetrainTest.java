package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.mocks.MockDcMotor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DrivetrainTest {

    private MockDcMotor fl;
    private MockDcMotor fr;
    private MockDcMotor bl;
    private MockDcMotor br;

    private Drivetrain drivetrain;

    @BeforeEach
    void setup() {
        fl = new MockDcMotor();
        fr = new MockDcMotor();
        bl = new MockDcMotor();
        br = new MockDcMotor();

        drivetrain = new Drivetrain(fl, fr, bl, br);
    }

    // -----------------------------
    // Constructor Tests
    // -----------------------------

    @Test
    void constructor_setsZeroPowerBehaviorToBrake() {
        assertEquals(DcMotor.ZeroPowerBehavior.BRAKE, fl.getZeroPowerBehavior());
        assertEquals(DcMotor.ZeroPowerBehavior.BRAKE, fr.getZeroPowerBehavior());
        assertEquals(DcMotor.ZeroPowerBehavior.BRAKE, bl.getZeroPowerBehavior());
        assertEquals(DcMotor.ZeroPowerBehavior.BRAKE, br.getZeroPowerBehavior());
    }

    @Test
    void constructor_setsWheelDirectionsCorrectly() {
        assertEquals(DcMotorSimple.Direction.REVERSE, fl.getDirection());
        assertEquals(DcMotorSimple.Direction.FORWARD, fr.getDirection());
        assertEquals(DcMotorSimple.Direction.REVERSE, bl.getDirection());
        assertEquals(DcMotorSimple.Direction.FORWARD, br.getDirection());
    }

    // -----------------------------
    // control() Tests
    // -----------------------------

    @Test
    void control_forwardMovement_setsAllMotorsPositive() {
        drivetrain.control(1, 0, 0);

        assertEquals(1, fr.getPower(), 1e-6);
        assertEquals(1, fl.getPower(), 1e-6);
        assertEquals(1, br.getPower(), 1e-6);
        assertEquals(1, bl.getPower(), 1e-6);
    }

    @Test
    void control_strafeRight_setsCorrectMotorPowers() {
        drivetrain.control(0, 1, 0);

        assertEquals(-1, fr.getPower(), 1e-6);
        assertEquals(1, fl.getPower(), 1e-6);
        assertEquals(1, br.getPower(), 1e-6);
        assertEquals(-1, bl.getPower(), 1e-6);
    }

    @Test
    void control_turnRight_setsCorrectMotorPowers() {
        drivetrain.control(0, 0, 1);

        assertEquals(-1, fr.getPower(), 1e-6);
        assertEquals(1, fl.getPower(), 1e-6);
        assertEquals(-1, br.getPower(), 1e-6);
        assertEquals(1, bl.getPower(), 1e-6);
    }

    @Test
    void control_clipsValuesToOne() {
        drivetrain.control(5, 5, 5);

        assertTrue(Math.abs(fr.getPower()) <= 1);
        assertTrue(Math.abs(fl.getPower()) <= 1);
        assertTrue(Math.abs(br.getPower()) <= 1);
        assertTrue(Math.abs(bl.getPower()) <= 1);
    }

    // -----------------------------
    // fcControl() Tests
    // -----------------------------

    @Test
    void fcControl_zeroAngle_behavesLikeNormalControl() {
        drivetrain.fcControl(1, 0, 0, 0);

        assertEquals(1, fl.getPower(), 1e-6);
        assertEquals(1, fr.getPower(), 1e-6);
        assertEquals(1, bl.getPower(), 1e-6);
        assertEquals(1, br.getPower(), 1e-6);
    }

    @Test
    void fcControl_90DegreeRotation_rotatesInputCorrectly() {
        drivetrain.fcControl(1, 0, 0, 90);

        // Forward input with 90° rotation should behave like strafe
        assertEquals(-1, fr.getPower(), 1e-6);
        assertEquals(1, fl.getPower(), 1e-6);
        assertEquals(1, br.getPower(), 1e-6);
        assertEquals(-1, bl.getPower(), 1e-6);
    }

    // -----------------------------
    // joystickMovement() Tests
    // -----------------------------

    @Test
    void joystickMovement_rightStickBranch_executes() {
        drivetrain.joystickMovement(0, 0, 1, 0);

        assertNotEquals(0, fr.getPower());
        assertNotEquals(0, fl.getPower());
        assertNotEquals(0, br.getPower());
        assertNotEquals(0, bl.getPower());
    }

    @Test
    void joystickMovement_leftStickBranch_executes() {
        drivetrain.joystickMovement(1, 0, 0, 0);

        assertNotEquals(0, fr.getPower());
        assertNotEquals(0, fl.getPower());
        assertNotEquals(0, br.getPower());
        assertNotEquals(0, bl.getPower());
    }
}
