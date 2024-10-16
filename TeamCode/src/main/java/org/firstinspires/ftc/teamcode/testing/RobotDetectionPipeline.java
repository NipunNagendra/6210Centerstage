package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
@Config

public class RobotDetectionPipeline extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();

    public static String positionMain = "middle";

    public enum Location {
        LEFT,
        RIGHT,
        FRONT,
        NOT_FOUND
    }
    private Location location;

    //Left rectangle coordinates
    public static double left_x1 = 60;
    public static double left_y1 = 35;
    public static double left_x2 = 120;
    public static double left_y2 = 75;
    //Right rectangle coordinates
    public static double right_x1 = 280;
    public static double right_y1 = 70;
    public static double right_x2 = 320;
    public static double right_y2 = 130;
    //Front rectangle coordinates
    public static double front_x1 = 110;
    public static double front_y1 = 55;
    public static double front_x2 = 170;
    public static double front_y2 = 115;

    //HSV for Red
    public static double lowerhue = 0;
    public static double lowersat = 1;
    public static double lowerval = 41;

    public static double higherhue = 360;
    public static double highersat = 5;
    public static double higherval = 85;

    static final Rect RIGHT_ROI = new Rect(
            new Point(right_x1, right_y1),
            new Point(right_x2, right_y2));
    static final Rect FRONT_ROI = new Rect(
            new Point(front_x1, front_y1),
            new Point(front_x2, front_y2));

    static double PERCENT_COLOR_THRESHOLD = 0.3;
    public RobotDetectionPipeline(Telemetry t) { telemetry = t; }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowerHSV = new Scalar(lowerhue, lowersat, lowerval);
        Scalar highHSV = new Scalar(higherhue, highersat, higherval);

        Core.inRange(mat, lowerHSV, highHSV, mat);

        Mat right = mat.submat(RIGHT_ROI);
        Mat front = mat.submat(FRONT_ROI);

        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;
        double frontValue = Core.sumElems(front).val[0] / FRONT_ROI.area() / 255;

        right.release();
        front.release();

        /*telemetry.addData("Left Raw Value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Right Raw Value", (int) Core.sumElems(right).val[0]);
        telemetry.addData("Front Raw Value", (int) Core.sumElems(front).val[0]);
        telemetry.addData("Left Percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Right Percentage", Math.round(rightValue * 100) + "%");
        telemetry.addData("Front Percentage", Math.round(frontValue * 100) + "%");*/

        boolean pixelRight = rightValue > PERCENT_COLOR_THRESHOLD;
        boolean pixelFront = frontValue > PERCENT_COLOR_THRESHOLD;


        if (pixelRight && pixelFront) {
            if(rightValue>=frontValue){
                positionMain = "right";
                telemetry.addData("Pixel Location", "right");
                location = RobotDetectionPipeline.Location.RIGHT;
            }
            else{
                positionMain = "middle";
                telemetry.addData("Pixel Location", "front");
                location = RobotDetectionPipeline.Location.FRONT;
            }

        }
        else if (pixelFront) {
            positionMain = "middle";
            location = RobotDetectionPipeline.Location.FRONT;
            telemetry.addData("Pixel Location", "front");
        }
        else if(pixelRight){
            positionMain = "right";
            telemetry.addData("Pixel Location", "right");
            location = RobotDetectionPipeline.Location.RIGHT;
        }
        else{
            positionMain = "left";
            location = RobotDetectionPipeline.Location.LEFT;
            telemetry.addData("Pixel Location", "left");
        }
        telemetry.update();

        //telemetry.update();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        Scalar colorStone = new Scalar(255, 0, 0);
        Scalar colorSkystone = new Scalar(0, 255, 0);

        Imgproc.rectangle(mat, RIGHT_ROI, location == Location.RIGHT? colorSkystone:colorStone);
        Imgproc.rectangle(mat, FRONT_ROI, location == Location.FRONT? colorSkystone:colorStone);

        return mat;
    }

    public Location getLocation() {
        return location;
    }
}