package org.firstinspires.ftc.teamcode.mocks;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class MockDcMotorEx extends MockDcMotor implements DcMotorEx {

    private boolean enabled = true;
    private double velocity = 0.0;
    private int targetTolerance = 0;

    private PIDFCoefficients pidfCoefficients;

    @Override
    public void setMotorEnable() {
        enabled = true;
    }

    @Override
    public void setMotorDisable() {
        enabled = false;
    }

    @Override
    public boolean isMotorEnabled() {
        return enabled;
    }

    @Override
    public void setVelocity(double angularRate) {
        this.velocity = angularRate;
    }

    @Override
    public void setVelocity(double angularRate, AngleUnit unit) {
        this.velocity = angularRate;
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    @Override
    public double getVelocity(AngleUnit unit) {
        return velocity;
    }

    @Override
    @Deprecated
    public void setPIDCoefficients(RunMode mode, PIDCoefficients pidCoefficients) {}

    @Override
    public void setPIDFCoefficients(RunMode mode, PIDFCoefficients pidfCoefficients) {
        this.pidfCoefficients = pidfCoefficients;
    }

    @Override
    public void setVelocityPIDFCoefficients(double p, double i, double d, double f) {
        this.pidfCoefficients = new PIDFCoefficients(p, i, d, f);
    }

    @Override
    public void setPositionPIDFCoefficients(double p) {
        this.pidfCoefficients = new PIDFCoefficients(p, 0, 0, 0);
    }

    @Override
    @Deprecated
    public PIDCoefficients getPIDCoefficients(RunMode mode) {
        return null;
    }

    @Override
    public PIDFCoefficients getPIDFCoefficients(RunMode mode) {
        return pidfCoefficients;
    }

    @Override
    public void setTargetPositionTolerance(int tolerance) {
        this.targetTolerance = tolerance;
    }

    @Override
    public int getTargetPositionTolerance() {
        return targetTolerance;
    }

    @Override
    public double getCurrent(CurrentUnit unit) {
        return 0;
    }

    @Override
    public double getCurrentAlert(CurrentUnit unit) {
        return 0;
    }

    @Override
    public void setCurrentAlert(double current, CurrentUnit unit) {}

    @Override
    public boolean isOverCurrent() {
        return false;
    }
}
