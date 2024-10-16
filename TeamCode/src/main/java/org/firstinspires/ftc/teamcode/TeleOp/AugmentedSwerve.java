package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.libs.Movement;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="SwerveWithoutSwerve", group="TeleOp")
public class AugmentedSwerve extends OpMode {
    Movement move;
    //RevColorSensorV3 cs;
    //DistanceSensor ds;
    double multiplier = 1;

    IMU imu;

    public static double imuVal;

    public void init() {
        //manip = new Manipulators(hardwareMap);
        //manip.droneServo.setPosition(0.5);
        move = new Movement(hardwareMap);
        //cs = hardwareMap.get(RevColorSensorV3.class, "cs");
        //ds = hardwareMap.get(DistanceSensor.class, "ds");
        gamepad1.setLedColor(0, 0, 256, 100000);
        telemetry.addData("init", "completed");
        telemetry.update();
        // imu for field oriented drive
        imu = hardwareMap.get(IMU.class, "imu");
        //TODO: Change for acc roibot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(new Orientation(
                AxesReference.INTRINSIC,
                AxesOrder.ZXY,
                AngleUnit.DEGREES,
                -90,
                90,
                -33,
                0
        )
        ));
        imu.initialize(parameters);
        imu.resetYaw();
    }

    @Override
    public void loop()
    {


        double leftY;
        double leftX;
        double rightX;


        if (move.isPressed("options1",gamepad1.options)) {
            imu.resetYaw();
        }



        if (gamepad1.right_bumper) {multiplier = 0.5;}
        else if (gamepad1.left_bumper) {multiplier = 0.25;}
        else{multiplier=1;}
        double heading = move.drive.getExternalHeading();



        imuVal = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        if (Math.abs(gamepad1.left_stick_y)  > 0.1 ||
                Math.abs(gamepad1.left_stick_x)  > 0.1 ||
                Math.abs(gamepad1.right_stick_x) > 0.1) {

            leftY = gamepad1.left_stick_y*multiplier;
            leftX = (gamepad1.left_stick_x*multiplier);
            rightX = -(gamepad1.right_stick_x*multiplier);

            double targetAngle =  imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            if (targetAngle > 0)
                targetAngle -= (Math.atan2(leftY,leftX)+(Math.PI/2));
            else
                targetAngle += (Math.atan2(leftY,leftX)+(Math.PI/2));

            move.fieldDrive(0,0, 0, heading, targetAngle);

        }
        else {
            move.fieldDrive(0,0, 0, heading, 0);
        }

        telemetry.addData("imu:",  imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        telemetry.update();

}}
