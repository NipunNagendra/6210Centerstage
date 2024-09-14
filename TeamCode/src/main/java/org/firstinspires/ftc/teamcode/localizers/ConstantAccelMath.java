package org.firstinspires.ftc.teamcode.localizers;

import org.firstinspires.ftc.teamcode.util.geometry.Pose;

public class ConstantAccelMath {
    public static final double FIDELITY = 1E-8;

    private double lastLoop = 0.008;
    private Pose lastRelativeDelta = new Pose(0,0,0);

    public void calculate(double loopTime, Pose relDelta, Pose currPose){
        double relDeltaX = relDelta.x;
        double relDeltaY = relDelta.y;
        double deltaHeading = relDelta.heading;

        double arx = (relDeltaX*lastLoop - lastRelativeDelta.x*loopTime)/(loopTime*lastLoop*lastLoop + loopTime*loopTime*lastLoop);
        double vrx = relDeltaX/loopTime - arx*loopTime;
        //v_x = vrx + arx*t
        double ary = (relDeltaY*lastLoop - lastRelativeDelta.y*loopTime)/(loopTime*lastLoop*lastLoop + loopTime*loopTime*lastLoop);
        double vry = relDeltaY/loopTime - ary*loopTime;
        //v_y = vry + ary*t
        double arh = (deltaHeading*lastLoop - lastRelativeDelta.heading*loopTime)/(loopTime*lastLoop*lastLoop + loopTime*loopTime*lastLoop);
        double vrh = deltaHeading/loopTime - arh*loopTime;
        //h = h1 + vry*t + ary*t^2

        AdaptiveQuadrature xQuadrature = new AdaptiveQuadrature(new double[] {vrx,2*arx},new double[] {currPose.heading,vrh,arh});
        AdaptiveQuadrature yQuadrature = new AdaptiveQuadrature(new double[] {vry,2*ary},new double[] {currPose.heading,vrh,arh});

        currPose.x += xQuadrature.evaluateCos(FIDELITY, 0, loopTime, 0) - yQuadrature.evaluateSin(FIDELITY, 0, loopTime, 0);
        currPose.y += yQuadrature.evaluateCos(FIDELITY, 0, loopTime, 0) + xQuadrature.evaluateSin(FIDELITY, 0, loopTime, 0);
        currPose.heading += deltaHeading;

        lastRelativeDelta = relDelta.clone();
        lastLoop = loopTime;
    }
}