package org.firstinspires.ftc.teamcode.mocks;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class MockDcMotor extends MockDcMotorSimple implements DcMotor {

    private ZeroPowerBehavior zeroPowerBehavior = ZeroPowerBehavior.FLOAT;
    private RunMode runMode = RunMode.RUN_WITHOUT_ENCODER;

    private int targetPosition = 0;
    private int currentPosition = 0;
    private boolean busy = false;

    @Override
    public MotorConfigurationType getMotorType() {
        return null;
    }

    @Override
    public void setMotorType(MotorConfigurationType motorType) {}

    @Override
    public DcMotorController getController() {
        return null;
    }

    @Override
    public int getPortNumber() {
        return 0;
    }

    @Override
    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
        this.zeroPowerBehavior = zeroPowerBehavior;
    }

    @Override
    public ZeroPowerBehavior getZeroPowerBehavior() {
        return zeroPowerBehavior;
    }

    @Override
    @Deprecated
    public void setPowerFloat() {
        setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        setPower(0);
    }

    @Override
    public boolean getPowerFloat() {
        return getZeroPowerBehavior() == ZeroPowerBehavior.FLOAT && getPower() == 0;
    }

    @Override
    public void setTargetPosition(int position) {
        this.targetPosition = position;
        busy = true;
    }

    @Override
    public int getTargetPosition() {
        return targetPosition;
    }

    @Override
    public boolean isBusy() {
        return busy;
    }

    @Override
    public int getCurrentPosition() {
        return currentPosition;
    }

    // Allow test to simulate encoder movement
    public void setCurrentPosition(int position) {
        this.currentPosition = position;
        if (position == targetPosition) {
            busy = false;
        }
    }

    @Override
    public void setMode(RunMode mode) {
        this.runMode = mode;
        if (mode == RunMode.STOP_AND_RESET_ENCODER) {
            currentPosition = 0;
        }
    }

    @Override
    public RunMode getMode() {
        return runMode;
    }
}
