package frc.robot.lib;

public class Constants {
    public class Hopper{
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
        // Climber speed
        public static final double climbUpSpeed = 0.1;
        public static final double climbDownSpeed = -0.1;
        public static final double climbStop = 0;

    }

    public class Shooter{
        // Roller
        public static final double rollerForwardSpeed = 0.1;
        public static final double rollerBackwardSpeed = -0.1;
        public static final double rollerStopSpeed = 0;
        // Wrist
        public static final double shootWristUpSpeed = 0.1;
        public static final double shootWristDownSpeed = -0.1;
        public static final double shootWristStopSpeed = 0;
        // Actuator
        public static final double actuatorPositiveSpeed = 0.1;
        public static final double actuatorNegativeSpeed = -0.1;
        public static final double actuatorStopSpeed = 0;
        
    }
}
