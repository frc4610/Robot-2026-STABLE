package frc.robot.lib;

public class Constants {
    public class Hopper{
        // Hopper motor ids
        public static final int hopperMotorIOId = 31;
        public static final int hopperMotorInId = 32;

        // Hopper encoder ids
        public static final int hopperEncoder_A = 33;
        public static final int hopperEncoder_B = 34;
        
        // Hopper Intake/Outake
        public static final double hopperTakeInSpeed = 0.1;
        public static final double hopperRegurgitateSpeed = -0.1;
        public static final double hopperStopSpeed = 0;

        // Hopper Index
        public static final double indexTakeInSpeed = 0.1;
        public static final double indexOutTakeSpeed = -0.1;
        public static final double indexStopSpeed = 0;

    }

    public class Intake{
        // Intake motor ids
        public static final int intakeMotorId = 21;
        public static final int intakeWristMotorId = 22;

        // Intake encoder ids
        public static final int intakeEncoder_A = 23;
        public static final int intakeEncoder_B = 24;

        // Intake speed
        public static final double inBallSpeed = 0.1;
        public static final double outBallSpeed = -0.1;
        public static final double stopBallSpeed = 0;

        // Setpoints for wrist
        public static final double upSetPoint = 0.1;
        public static final double downSetPoint = -0.1;
        public static final double stopWristSpeed = 0;

    }

    public class Climber{
        // Climber motor ids
        public static final int climberMotorId = 41;

        // CLimber encoder ids
        public static final int climberEncoder_A = 42;
        public static final int climberEncoder_B = 43;

        // Climber speed
        public static final double climbUpSpeed = 0.1;
        public static final double climbDownSpeed = -0.1;
        public static final double climbStop = 0;

    }

    public class Shooter{
        // Shooter motor ids
        public static final int rollerMotorId = 51;
        public static final int shooterWristMotorId = 52;
        public static final int actuatorMotorId = 53;

        // Shooter encoder ids
        public static final int shootWristEncoder_A = 54;
        public static final int shootWristEncoder_B = 55;

        public static final int actuatorEncoder_A = 56;
        public static final int actuatorEncoder_B = 57;

        // Roller speeds
        public static final double rollerForwardSpeed = 0.1;
        public static final double rollerBackwardSpeed = -0.1;
        public static final double rollerStopSpeed = 0;

        // Wrist speeds
        public static final double shootWristUpSpeed = 0.1;
        public static final double shootWristDownSpeed = -0.1;
        public static final double shootWristStopSpeed = 0;

        // Actuator speeds
        public static final double actuatorPositiveSpeed = 0.1;
        public static final double actuatorNegativeSpeed = -0.1;
        public static final double actuatorStopSpeed = 0;
        
    }
}
