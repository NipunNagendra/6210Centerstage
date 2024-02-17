package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SplineTestMeep {
    //coordinates for starting position (0, 0, 0)
    public static double startPoseX= -38.15845302224215;
    public static double startPoseY= -65.13672263931143;
    public static double startPoseAngle= Math.toRadians(270);

    Pose2d startPose = new Pose2d(startPoseX, startPoseY, startPoseAngle);

    Pose2d posEstimate;

    //coordinates for left spike position
    public static double spike1X = -41.64633638294297;
    public static double spike1Y = -32.1247700133697;
    public static double spike1Angle = Math.toRadians(180);

    //coordinates for middle spike position
    public static double spike2X =  -37.812297556497846;
    public static double spike2Y = -27.023006373520104;
    public static double spike2Angle = Math.toRadians(90);

    //coordinates for right spike position
//    public static double spike3X = -34.26642694740993;
//    public static double spike3Y = 29.54644728121096;
//    public static double spike3Angle = Math.toRadians(180);
    public static double moveBackwards3 = 31;
    public static double moveForward3 = 11;
    public static double turn3 = 90;

    public static double preTrussX = -38.15845302224215;
    public static double trussX = -14;
    public static double trussY = -55.93672263931143;
    public static double trussAngle = Math.toRadians(180);
    public static double backdropMiddleX = 46;
    public static double backdropMiddleY = -38;
    public static double backdropMiddleAngle = trussAngle;
    public static double backdropLeftStrafe = 8;
    public static double backdropRightStrafe = 8;

    public static double casenum=1;

    public static double throughTrussStrafe=35;

    public static String myPosition;

    public static void main(String[] args) {
        MeepMeep meepmeep = new MeepMeep(600);


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(35.86228895377674, 35.86228895377674, 2.4242626190185548, Math.toRadians(214.79), 14)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(trussX, trussY, trussAngle))
//                                        .back(moveBackwards3)
//                                        .turn(Math.toRadians(turn3))
//                                        .forward(moveForward3)
//                                        .back(10)
//                                        .turn(Math.toRadians(-180))
//                                        .lineToLinearHeading(new Pose2d(preTrussX, trussY, trussAngle))
//                                        .lineToLinearHeading(new Pose2d(trussX, trussY, trussAngle))
//                                        .strafeRight(throughTrussStrafe)
//                                        .back(20)
//                                        .lineToLinearHeading(new Pose2d(backdropMiddleX, backdropMiddleY, backdropMiddleAngle))
                                        .splineTo(new Vector2d(backdropMiddleX, backdropMiddleY), Math.toRadians(0))
//                                        .forward(10)
//                                        .strafeLeft(20)
//                                        .back(10)
//                                        .splineToLinearHeading(new Pose2d(58,-60, Math.toRadians(90)), Math.toRadians(0))
                                        .build()


                );

        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepmeep)
                .setColorScheme(new ColorSchemeRedLight())
                .setConstraints(35.86228895377674, 35.86228895377674, 2.4242626190185548, Math.toRadians(214.79), 14)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(trussX, trussY, trussAngle))
//                                        .back(moveBackwards3)
//                                        .turn(Math.toRadians(turn3))
//                                        .forward(moveForward3)
//                                        .back(10)
//                                        .turn(Math.toRadians(-180))
//                                        .lineToLinearHeading(new Pose2d(preTrussX, trussY, trussAngle))
//                                        .lineToLinearHeading(new Pose2d(trussX, trussY, trussAngle))
//                                        .strafeRight(throughTrussStrafe)
//                                        .back(20)
//                                        .lineToLinearHeading(new Pose2d(backdropMiddleX, backdropMiddleY, backdropMiddleAngle))
                                        .splineTo(new Vector2d(backdropMiddleX, backdropMiddleY), Math.toRadians(90))
//                                        .forward(10)
//                                        .strafeLeft(20)
//                                        .back(10)
//                                        .splineToLinearHeading(new Pose2d(58,-60, Math.toRadians(90)), Math.toRadians(0))
                                        .build()


                );

        RoadRunnerBotEntity myBot3 = new DefaultBotBuilder(meepmeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(35.86228895377674, 35.86228895377674, 2.4242626190185548, Math.toRadians(214.79), 14)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(trussX, trussY, trussAngle))
//                                        .back(moveBackwards3)
//                                        .turn(Math.toRadians(turn3))
//                                        .forward(moveForward3)
//                                        .back(10)
//                                        .turn(Math.toRadians(-180))
//                                        .lineToLinearHeading(new Pose2d(preTrussX, trussY, trussAngle))
//                                        .lineToLinearHeading(new Pose2d(trussX, trussY, trussAngle))
//                                        .strafeRight(throughTrussStrafe)
//                                        .back(20)
//                                        .lineToLinearHeading(new Pose2d(backdropMiddleX, backdropMiddleY, backdropMiddleAngle))
                                        .splineTo(new Vector2d(backdropMiddleX, backdropMiddleY), Math.toRadians(180))
//                                        .forward(10)
//                                        .strafeLeft(20)
//                                        .back(10)
//                                        .splineToLinearHeading(new Pose2d(58,-60, Math.toRadians(90)), Math.toRadians(0))
                                        .build()


                );

        RoadRunnerBotEntity myBot4 = new DefaultBotBuilder(meepmeep)
                .setColorScheme(new ColorSchemeBlueLight())
                .setConstraints(35.86228895377674, 35.86228895377674, 2.4242626190185548, Math.toRadians(214.79), 14)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(trussX, trussY, trussAngle))
//                                        .back(moveBackwards3)
//                                        .turn(Math.toRadians(turn3))
//                                        .forward(moveForward3)
//                                        .back(10)
//                                        .turn(Math.toRadians(-180))
//                                        .lineToLinearHeading(new Pose2d(preTrussX, trussY, trussAngle))
//                                        .lineToLinearHeading(new Pose2d(trussX, trussY, trussAngle))
//                                        .strafeRight(throughTrussStrafe)
//                                        .back(20)
//                                        .lineToLinearHeading(new Pose2d(backdropMiddleX, backdropMiddleY, backdropMiddleAngle))
                                        .splineTo(new Vector2d(backdropMiddleX, backdropMiddleY), Math.toRadians(270))
//                                        .forward(10)
//                                        .strafeLeft(20)
//                                        .back(10)
//                                        .splineToLinearHeading(new Pose2d(58,-60, Math.toRadians(90)), Math.toRadians(0))
                                        .build()


                );

        meepmeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(.95f)
                .addEntity(myBot)
                .addEntity(myBot2)
                .addEntity(myBot3)
                .addEntity(myBot4)
                .start();
    }



}