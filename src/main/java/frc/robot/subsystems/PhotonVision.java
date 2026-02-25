// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import org.photonvision.PhotonCamera;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.generated.TunerConstants;


public class PhotonVision extends SubsystemBase {
  /** Creates a new PhotonVision. */


  /*This subsystem calculates photonvisions autotracking and sends it to robotcontainer to be used
   * it only tracks turning not distance
   * can track a moving target
   * can move and track at the same time
   * has issues with stuttering but it's so fast it's likely not an issue to be concerned with
   */

    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); //Calculates the max speed the robot can turn4
    private double PhotonMaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed

    PhotonCamera front_Limelight = new PhotonCamera("Front_Limelight"); //creates a new front limelight
    PhotonCamera back_Limelight = new PhotonCamera("Back_LimeLight"); //creates a new back limelight

    public final double VISION_TURN_kP = 0.01; //It's needed dont question
    /*public final double VISION_STRAFE_kP = 0.1;*/
    public final double kTurnOffset = 4.5; //You have to manually find this through testing the camera
    /*public final double ktargetDesiredRange = 2;*/

    public static double turn; //the turn value used to control the robots turning
    public static double drive;

    public static final CommandXboxController m_DriverController = new CommandXboxController(0);//creates the driver controller used here for tracking enabling and driving the robot in robotcontainer

    static Shuffleboard m_Shuffleboard; //shuffleboard
    public static ShuffleboardTab m_Photontab = Shuffleboard.getTab("PhotonTab"); //makes a photon tab

    private GenericEntry turnValue = m_Photontab.add("turn", turn).getEntry();
    //private GenericEntry driveValue = m_Photontab.add("Drive", drive).getEntry();


    boolean targetVisible = false;
    double targetYaw = 0.0;
    //double targetRange = 0.0;
    Timer photonTimer = new Timer();



  public PhotonVision() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run


        var results = back_Limelight.getAllUnreadResults(); //this reads all possible apriltag detections made by the camera

        if (results.isEmpty()) {

            photonTimer.start();

            if (photonTimer.get() > 0.1) {

                photonTimer.stop();
                targetYaw = 0.00;
                targetVisible = false;
            }
        }

        if (!results.isEmpty()) { //if NOT empty; remember, if ! is before a if statement, then it is the opposite

            photonTimer.stop();

            var result = results.get(results.size() - 1); //basically constantly makes this variable the newest apriltag detection
            if (result.hasTargets()) {//if a tag was properly seen...

                for (var target : result.getTargets()) {//idk
                    if (target.getFiducialId() == 1) {//if the detection is apriltag 1 then...

                        targetYaw = target.getYaw();//sets our yaw to its yaw
                        targetVisible = true;//self explainitory
                    }
                }
            }
        }

        // Auto-align when requested
        if (m_DriverController.rightBumper().getAsBoolean() == true && targetVisible) { //if the right bumper is pressed and the target is visible...

            turn = -1.0 * targetYaw * VISION_TURN_kP * MaxAngularRate; //overide the drivers controller joystick and set our turn value to the yaw times a couple of stuff to make it work
            //drive = (ktargetDesiredRange - targetRange) * VISION_STRAFE_kP * MaxSpeed;

        } else {

            turn = -m_DriverController.getRightX() * MaxAngularRate;
            drive = -m_DriverController.getLeftY() * PhotonMaxSpeed * getAllianceM();

        }

        turnValue.setDouble(turn);
        //driveValue.setDouble(drive);

  }

  public double getAllianceM() {
    return DriverStation.getAlliance().get() == Alliance.Blue ? 1 : -1;
  }
}
