package org.firstinspires.ftc.teamcode.libs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.HashMap;

@Config
public class Manipulators {
    HardwareMap robot;
    public Servo outtakeServo;

    public Servo droneServo;

    public DcMotor leftClimberMotor;
    public DcMotor rightClimberMotor;

    public DcMotor intakeMotor;

    public DcMotor outtakeLiftMotor;
    double outtakeLiftTicks = 537.7;
    double newOuttakeLiftTarget;
    public HashMap<String, Boolean> buttons = new HashMap<String, Boolean>();
    public static double outtakeServoPos1 = 0.15;
    public static double outtakeServoPos2 = 0;

    public TouchSensor liftTouchSensor;

    public Manipulators(HardwareMap hardwareMap) {
        this.robot = hardwareMap;

        // declaring climber/hanging motors
        leftClimberMotor = hardwareMap.get(DcMotor.class, "leftClimber");
        rightClimberMotor = hardwareMap.get(DcMotor.class, "rightClimber");

        // setting climber/hanging motor direction
        leftClimberMotor.setDirection(DcMotor.Direction.FORWARD);
        rightClimberMotor.setDirection(DcMotor.Direction.REVERSE);

        // setting break mode for climber/hanging motors
        leftClimberMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightClimberMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        outtakeServo = hardwareMap.get(Servo.class, "outtakeServo");

        outtakeLiftMotor = hardwareMap.get(DcMotor.class, "outtakeLift");
        outtakeLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //declaring intake motor
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        liftTouchSensor = hardwareMap.get(TouchSensor.class, "liftTouchSensor");

    }

    // Creating method for Lift outtake
    public void setOuttakeLiftPower(double liftPower) {
        // setting power to lift
        outtakeLiftMotor.setPower(liftPower);
    }

    //Gate output toggle method
    public void gateToggle(boolean outtakeServoStatus) {
        //Checking the status of the outtake servo
        if (outtakeServoStatus == false){
            outtakeServo.setPosition(outtakeServoPos1); //0.15
        } else if (outtakeServoStatus){
            outtakeServo.setPosition(outtakeServoPos2); //0
        }
    }

    // sets power to control climber/hanging motors
    public void climberLiftPower(double motorPower){
        leftClimberMotor.setPower(motorPower);
        rightClimberMotor.setPower(motorPower);}

    // changes the position of the servo to launch the drone
    public void droneLaunch() {
        droneServo.setPosition(0);
    }

    public void setIntakePower(double power){intakeMotor.setPower(power);}

    public void moveOuttakeLift(int encoderTicks){
        outtakeLiftMotor.setTargetPosition(encoderTicks);
        outtakeLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        outtakeLiftMotor.setPower(0);
    }

    public void bottomOutLift(){
        while(!liftTouchSensor.isPressed()){
            outtakeLiftMotor.setPower(-.9);
        }
        outtakeLiftMotor.setPower(0);
    }
}
