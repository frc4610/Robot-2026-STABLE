// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib;

/** Add your docs here. */
public class Constants {
// Constants for Hopper Intake/Outake
    public class Hopper{
        public static final double hopperTakeInSpeed = 0.1;
        public static final double hopperRegurgitateSpeed = -0.1;
        public static final double hopperStopSpeed = 0;
// Constants for Hopper Index
        public static final double indexTakeInSpeed = 0.1;
        public static final double indexOutTakeSpeed = -0.1;
        public static final double indexStopSpeed = 0;
    }
    public class Intake{
        //Intake speed
        public static final double inBallSpeed = 0.1;
        public static final double outBallSpeed = -0.1;
        public static final double stopBallSpeed = 0;
        //Setpoints for wrist
        public static final double upSetPoint = 0;
        public static final double downSetPoint = 270;  
        public static final double stopWristSpeed = 0;
    }
}
