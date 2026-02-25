// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Constants;

/** Add your docs here. */
public class Constants {
    public class DeviceIds{

        public class IntakeIds {
            public static final int intakeMotorId = 21;
            public static final int intakeWristMotorId = 22;

            public static final int intakeEncoder_A = 23;
        }

        public class HopperIds {
            public static final int hopperMotorId = 31;

            public static final int hopperEncoder_A = 32;
        }

        public class ClimberIds {
            public static final int climberMotorId = 51;
            
            public static final int climberEncoder_A = 52;
        }

        public class ShooterIds {
            public static final int shooterRollerMotorId = 41;

            public static final int shooterRollerEncoder_A = 42;
        }
    }

    public class MechConsants {

        public class IntakeMech {
            public static final double inBallSpeed = 0.4;
            public static final double outBallSpeed = -0.4;
            public static final double intakeStopSpeed = 0.0;

            public static final double intakeWristSpeed = 0.2;
            public static final double intakeWristDownSpeed = -0.2;
            public static final double intakeWristStopSpeed = 0.0;
        }

        public class HopperMech {
            public static final double hopperIntakeSpeed = 0.1;
            public static final double hopperRegurgitateSpeed = -0.1;
            public static final double hopperStopSpeed = 0.0;
        }

        public class ClimberMech {
            public static final double climbUpSpeed = 0.1;
            public static final double climbDownSpeed = -0.1;
            public static final double climbStopSpeed = 0.0;
        }

        public class ShooterMech {
            public static final double shootRollerForwardSpeed = 0.25;
            public static final double shootRollerBackwardSpeed = -0.25;
            public static final double shootRolllerStopSpeed = 0.0;

            public static final double shootWristUpSpeed = 0.1;
            public static final double shootWristDownSpeed = -0.1;
            public static final double shootWristStopSpeed = 0.0;

            public static final double actuatorPositiveSpeed = 0.1;
            public static final double actuatorNegativeSpeed = -0.1;
            public static final double actuatorStopSpeed = 0.0;
        }
    }
}
