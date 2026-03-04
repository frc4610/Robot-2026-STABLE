package frc.robot.lib;

public class constants {

    public class DeviceIds {

        public static final int kDriverControllerPort = 0;
        public static final int kOperatorController = 1;
        public static final int kPIDoperatorontrollerport = 2;

        public class IntakeIds {

            public static final int IntakeRoller = 21;
            public static final int kIntakeWrist = 22;
            
            public static final int kIntakeEncoder = 0;

        }

        public class HopperIds {

            public static final int kHopperMotor = 31;

        }

        public class ShooterIds {

            public static final int kShooterMotor = 41;

            public static final int kShooterEncoderI = 1;
            public static final int kShooterEncoderII = 2;

        }

        public class climbIds {

            public static final int kClimbMotor = 51;

            public static final int kClimbEncoderId = 3;

        }



    }

    public class MechConstants{

        public class IntakeConstants {

            public static final double kIntakeSpeed = -0.3;
            public static final double kReverseIntakeSpeed = 0.30;
            public static final double kKillIntakeRoller = 0.0;

            public static final double kIntakeWristUpSpeed = 0.20;
            public static final double kKillIntakeWrist = 0.0;
            public static final double kIntakeWristDownSpeed = -0.20;

            public static final int kIntakeDownPOS = 0;
            public static final int kIntakeUpPOS = 65;

        }

                public class ShooterConstants {
        /**Shooter Subsystem Constants */

             /* Testing Shooter Speeds */
            public static final double kShootingSpeed = 0.25;
            public static final double kReverseShooterSpeed = -0.25;
            public static final double kKillShooter = 0.0;
           
             /** public static final double kEncoderOffset = 0;*/
        }


         public class HopperConstants {
            
            public static final double kForwardHopperSpeed = 0.25;
            public static final double kKillHopper = 0.0;
            public static final double KBackwardHopperSpeed = -0.25;

        }


         public class ClimbConstants {

            public static final  double kClimbingSpeed = 0.20;
            public static final double kStopClimbing = 0.0;
            public static final double kLowerClimbSpeed = -0.20;

                //tbd
            public static final int kHookingPOS = 0;
            public static final int kClimbingPOS = 30;
            public static final int kDefence = 0;

        }
    }
    
}
