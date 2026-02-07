package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.lib.math.Pose2D;

public class Drivetrain {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    public Drivetrain(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight){
        this.frontLeftMotor = frontLeft;
        this.frontRightMotor = frontRight;
        this.backLeftMotor = backLeft;
        this.backRightMotor = backRight;
        this.frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        setWheelDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD);

    }

    public void fcControl(double y, double x, double h, double angle) {

        double r = Math.hypot(y, x);
        double theta = Math.atan2(y, x);

        double correctedTheta = theta - Math.toRadians(angle);

        double correctedY = r * Math.sin(correctedTheta);
        double correctedX = r * Math.cos(correctedTheta);

        control(correctedY, correctedX, h);

    }

    /**
     * Y IS FORWARDS AND BACKWARDS
     * @param y +forwards and -backwards
     * @param x strafe -left and +right
     * @param h turn +right and -left
     */
    public void control(double y, double x, double h) {
        frontRightMotor.setPower(Range.clip(y - x - h, -1, 1));
        frontLeftMotor.setPower(Range.clip(y + x + h, -1, 1));
        backRightMotor.setPower(Range.clip(y + x - h, -1, 1));
        backLeftMotor.setPower(Range.clip(y - x + h, -1, 1));

    }

    public void joystickMovement(double ly, double lx, double rx, double ry) {
        if (rx != 0 || ry != 0) {
            double magnitude = Math.sqrt((ry*ry)+(rx*rx));
            double fr = (ry + rx) / 2;
            double br = (ry + rx) / 2;
            double fl = (ry - rx) / 2;
            double bl = (ry - rx) / 2;
            double min = Math.min(Math.min(fr, br), Math.min(fl, bl));
            frontRightMotor.setPower((fr/min)*magnitude);
            backRightMotor.setPower((br/min)*magnitude);
            frontLeftMotor.setPower((fl/min)*magnitude);
            backLeftMotor.setPower((bl/min)*magnitude);
        } else {
            //double magnitude = Math.sqrt((ly*ly)+(lx*lx));
            double magnitude = 1;
            double fr = (ly - lx) / 2;
            double br = (ly + lx) / 2;
            double fl = (ly + lx) / 2;
            double bl = (ly - lx) / 2;
            double min = Math.min(Math.min(fr, br), Math.min(fl, bl));
            min = Math.abs(min);
            frontRightMotor.setPower((fr/min)*magnitude);
            backRightMotor.setPower((br/min)*magnitude);
            frontLeftMotor.setPower((fl/min)*magnitude);
            backLeftMotor.setPower((bl/min)*magnitude);

        }
    }

    public void setWheelDirection(DcMotorSimple.Direction lf, DcMotorSimple.Direction rf, DcMotorSimple.Direction lb, DcMotorSimple.Direction rb) {
        this.frontLeftMotor.setDirection(lf);
        this.frontRightMotor.setDirection(rf);
        this.backLeftMotor.setDirection(lb);
        this.backRightMotor.setDirection(rb);

    }

}
