package frc.robot.subsystems;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.RobotContainer;

public class Vision {

    public static PhotonCamera m_Camera = new PhotonCamera("BearTecs4610");

    public static PIDController m_TurnController = 
        new PIDController(0.01, 0, 0);
    
    public static CommandXboxController m_Driver = RobotContainer.joystick;
    public static CommandSwerveDrivetrain m_DriveTrain = RobotContainer.drivetrain;



    boolean isTargetVisible;
    double targetAngle;
    double turn;
    double strafe;
    double forward;
    
    public Vision() {

    }

public void turnToTag(int tagId) {
        var results = m_Camera.getAllUnreadResults(); // Retrieves all instances of a detected apriltag, creates a tag queue

         if(!results.isEmpty()) { // checks if any tags have been detected
                var result = results.get(results.size() - 1); // a var where a tag has been detected, one is removed fromt the tag queue
                    if (result.hasTargets()) { // if a tag has been detected and removed from queue:
                        for (var target : result.getTargets()) {
                            if (target.getFiducialId() == tagId) {
                                targetAngle = target.getYaw();
                                isTargetVisible = true;
                            }
                        }
                    }
                }

            if (m_Driver.leftBumper().getAsBoolean() && isTargetVisible) {
                turn = m_TurnController.getP() * targetAngle * -1;
            }
         
    }
}
