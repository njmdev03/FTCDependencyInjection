package org.firstinspires.ftc.teamcode.mocks;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class MockDcMotorSimple implements DcMotorSimple {

    private Direction direction = Direction.FORWARD;
    private double power = 0.0;

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public double getPower() {
        return power;
    }

    // HardwareDevice methods (minimal stub)
    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.Other;
    }

    @Override
    public String getDeviceName() {
        return "MockDcMotorSimple";
    }

    @Override
    public String getConnectionInfo() {
        return "mock";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {}

    @Override
    public void close() {}
}
