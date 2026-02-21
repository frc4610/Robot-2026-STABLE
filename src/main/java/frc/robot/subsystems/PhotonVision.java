// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class PhotonVision extends SubsystemBase {
  /** Creates a new PhotonVision. */


  /*This subsystem calculates photonvisions autotracking and sends it to robotcontainer to be used
   * it only tracks turning not distance
   * can track a moving target
   * can move and track at the same time
   * has issues with stuttering but it's so fast it's likely not an issue to be concerned with
   */

    public static double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); //Calculates the max speed the robot can turn

    PhotonCamera front_Limelight = new PhotonCamera("Front_Limelight"); //creates a new front limelight
    PhotonCamera back_Limelight = new PhotonCamera("Back_LimeLight"); //creates a new back limelight

    public final double VISION_TURN_kP = 0.01; //It's needed dont question
    public static double turn; //the turn value used to control the robots turning

    public static final CommandXboxController m_DriverController = new CommandXboxController(0);//creates the driver controller used here for tracking enabling and driving the robot in robotcontainer

    static Shuffleboard m_Shuffleboard; //shuffleboard
    public static ShuffleboardTab m_Photontab = Shuffleboard.getTab("PhotonTab"); //makes a photon tab



  public PhotonVision() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

        turn = -m_DriverController.getRightX() * MaxAngularRate; //sets turn, when not tracking, to the controllers input

        boolean targetVisible = false; //self explanitory
        double targetYaw = 0.0; //yaw for the camera is left and right, so it's used for tracking and turning to face

        var results = back_Limelight.getAllUnreadResults(); //this reads all possible apriltag detections made by the camera

        if (!results.isEmpty()) { //if NOT empty; remember, if ! is before a if statement, then it is the opposite

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
        }

  }
}
