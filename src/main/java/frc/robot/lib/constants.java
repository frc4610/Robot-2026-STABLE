package frc.robot.lib;

public class constants {

    public class DeviceIds {

        public static final int kDriverControllerPort = 0;
        public static final int kOperatorController = 1;
        public static final int kPIDoperatorontrollerport = 2;

        public class IntakeIds {
            public static final int IntakeRoller = 21;
            public static final int kIntakeWrist = 22;
            
            public static final int kIntakeEncoderID = 23;

        }

        public class HopperIds {
            public static final int kHopperMotor = 31;
            
            public static final int kHopperEncoder = 32;
        }

        public class ShooterIds {
            public static final int kShooterMotor = 41;

            public static final int kShooterEncoder = 42;

        }

        public class climbIds {
            public static final int kClimbMotor = 51;

            public static final int kClimbEncoderId = 52;

        }



    }

    public class MechConstants{

        public class IntakeConstants {

            public static final double kIntakeSpeed = -0.10;
            public static final double kEjectSpeed = 0.10;
            public static final double kKillIntake = 0.0;

            public static final double kTurnUpSpeed = 0.10;
            public static final double kKillTurnMotor = 0.0;
            public static final double kTurnDownSpeed = -0.10;

            public static final double kIntakeDownPOS = 0;
            public static final double kIntakeUpPOS = 65;

        }

                public class ShooterConstants {
        /**Shooter Subsystem Constants */

             /* Testing Shooter Speeds */
            public static final double kRollerSpeeds = 0.10;
            public static final double kReverseRollerSpeeds = -0.10;
            public static final double kKillRollers = 0.0;

            /* testing Actuator Speeds */
            public static final double kClockwise = -0.10;
            public static final double kCounterClockwise = 0.10;
            public static final double kActuatorDie = 0.0;
           
             /** public static final double kEncoderOffset = 0;*/
        }


         public class HopperConstants {
            
            public static final double kForwardSpeed = 0.10;
            public static final double kStopHopper = 0.0;
            public static final double KBackwardSpeed = -0.10;

            public static final double kForwardIndexer = 0.10;
            public static final double kBackwardIndexer = -0.10;
            public static final double kStopIndexer = 0.0;

        }


         public class ClimberConstants {

            public static final  double kRiseSpeed = 0.10;
            public static final double kStopClimbing = 0.0;
            public static final double kLowerSpeed = -0.10;

                //tbd
            public static final double kStartPOS = 0;
            public static final double KClimbingPOS = 45;

        }
    }
    
}
