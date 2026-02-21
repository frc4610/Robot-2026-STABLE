package frc.robot.lib;

public class Constants {


    public class DeviceIds {
    
        public static final int kDriverControllerPort = 0;
        public static final int KOperatorController = 1;
        public static final int kVoltageController = 2;

        public class IntakeIds {

            public static final int kIntakeRoller = 11;
            public static final int kIntakeWrist = 12;

        }

        public class HopperIds{

            public static final int kHopperMotor = 21;
            public static final int kIndexer = 22;
            public static final int kHopperEncoder_A = 23;
            public static final int kHopperEncoder_B = 24;
        }

        
        public class ClimberIds{

            public static final int kClimbMotorid = 1;
            
            public static final int kClimbEncoder_A = 1;
            public static final int kClimbEncoder_B = 2;


        }


      
        public class ShooterIds {
            public static final int kActuatorMotor = 41;
            public static final int kRollerMotor = 42;

            public static final int kRollerEncoder_A = 43;
            public static final int kRollerEncoder_B = 44;
            public static final int kShooterEncoder_A = 45;
            public static final int kShooterEncoder_B = 46;
        }




    }
    
    public class MechConstants { 
    
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

        public class IntakeConstants {


            public static final double kIntakeSpeed = -0.10;
            public static final double kKillIntake = 0.0;
            public static final double kEjectSpeed = 010;

            public static final double kTurnUpSpeed = 0.10;
            public static final double kKillTurnMotor = 0.0;
            public static final double kTurnDownSpeed = -0.10;

        }

        public class ClimberConstants {


            public static final  double kRiseSpeed = 0.10;
            public static final double kStopClimbing = 0.0;
            public static final double kLowerSpeed = -0.10;

        }
        
        public class HopperConstants {
            
            public static final double kForwardSpeed = 0.10;
            public static final double kStopHopper = 0.0;
            public static final double KBackwardSpeed = -0.10;

            public static final double kForwardIndexer = 0.10;
            public static final double kBackwardIndexer = -0.10;
            public static final double kStopIndexer = 0.0;

        }

    }
}
