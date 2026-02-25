// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.Constants.Constants;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PhotonVision;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
    // Import subsystems
    Climber m_Climber = new Climber();
    Hopper m_Hopper = new Hopper();
    Intake m_Intake = new Intake();
    PhotonVision m_PhotonVision = new PhotonVision();
    Shooter m_Shooter = new Shooter();


    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); //Calculates the max speed the robot can turn4
    private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed



    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(MaxSpeed);

    public static final CommandXboxController joystick = PhotonVision.m_DriverController;
    public static final CommandXboxController m_operatorController = new CommandXboxController(1);

    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

    public RobotContainer() {
        configureBindings();
        operatorBindings();
    }

    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(PhotonVision.turn) // Drive counterclockwise with negative X (left)
            )
        );

        // Idle while the robot is disabled. This ensures the configured
        // neutral mode is applied to the drive motors while disabled.
        final var idle = new SwerveRequest.Idle();
        RobotModeTriggers.disabled().whileTrue(
            drivetrain.applyRequest(() -> idle).ignoringDisable(true)
        );

        joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
        joystick.b().whileTrue(drivetrain.applyRequest(() ->
            point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))
        ));

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        // Reset the field-centric heading on left bumper press.
        joystick.leftBumper().onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    private void operatorBindings() {

        //Hopper Out = Left Bumper
        m_operatorController.leftBumper().whileTrue(Commands.startEnd(
            ()-> m_Hopper.hopperRegurgitate(Constants.MechConsants.HopperMech.hopperRegurgitateSpeed), 
            ()-> m_Hopper.hopperStop(Constants.MechConsants.HopperMech.hopperStopSpeed), 
            m_Hopper));

        //Hopper In = Right Bumper
        m_operatorController.rightBumper().whileTrue(Commands.startEnd(
            ()-> m_Hopper.hopperTakeIn(Constants.MechConsants.HopperMech.hopperIntakeSpeed), 
            ()-> m_Hopper.hopperStop(Constants.MechConsants.HopperMech.hopperStopSpeed), 
            m_Hopper));

        //Climber Down = Left Trigger
        m_operatorController.leftTrigger().whileTrue(Commands.startEnd(
            ()-> m_Climber.climbDown(Constants.MechConsants.ClimberMech.climbDownSpeed), 
            ()-> m_Climber.climbStop(Constants.MechConsants.ClimberMech.climbStopSpeed), m_Climber));

        //Climber Up = Right Trigger
        m_operatorController.rightTrigger().whileTrue(Commands.startEnd(
            ()-> m_Climber.climbUp(Constants.MechConsants.ClimberMech.climbUpSpeed), 
            ()-> m_Climber.climbStop(Constants.MechConsants.ClimberMech.climbStopSpeed), 
            m_Climber));

        //Shoot Balls = Y
        m_operatorController.y().whileTrue(Commands.startEnd(
            ()-> m_Shooter.rollerForward(Constants.MechConsants.ShooterMech.shootRollerForwardSpeed), 
            ()-> m_Shooter.rollerStop(Constants.MechConsants.ShooterMech.shootRolllerStopSpeed), 
            m_Shooter));

        //Reverse Balls = B
        m_operatorController.b().whileTrue(Commands.startEnd(
            ()-> m_Shooter.rollerBackward(Constants.MechConsants.ShooterMech.shootRollerBackwardSpeed),
            ()-> m_Shooter.rollerStop(Constants.MechConsants.ShooterMech.shootRolllerStopSpeed), 
            m_Shooter));

        //Intake Wrist move Up = POV Up
        m_operatorController.povUp().whileTrue(Commands.startEnd(
            ()-> m_Intake.intakeWristUp(Constants.MechConsants.IntakeMech.intakeWristSpeed),
            ()-> m_Intake.intakeStop(Constants.MechConsants.IntakeMech.intakeStopSpeed),
            m_Intake));

        //Intake Balls = POV Left
        m_operatorController.povLeft().whileTrue(Commands.startEnd(
            ()-> m_Intake.intakeBall(Constants.MechConsants.IntakeMech.inBallSpeed),
            ()-> m_Intake.intakeStop(Constants.MechConsants.IntakeMech.intakeStopSpeed),
            m_Intake));

        //Outtake Balls? = POV Right
        m_operatorController.povRight().whileTrue(Commands.startEnd(
            ()-> m_Intake.outakeBall(Constants.MechConsants.IntakeMech.outBallSpeed),
            ()-> m_Intake.intakeStop(Constants.MechConsants.IntakeMech.intakeStopSpeed),
            m_Intake));

        //Intake Wrist move Down = POV Down
        m_operatorController.povDown().whileTrue(Commands.startEnd(
            ()-> m_Intake.intakeWristDown(Constants.MechConsants.IntakeMech.intakeWristDownSpeed),
            ()-> m_Intake.intakeWristStop(Constants.MechConsants.IntakeMech.intakeWristStopSpeed),
            m_Intake));
    
    }

    public Command getAutonomousCommand() {
        // Simple drive forward auton
        final var idle = new SwerveRequest.Idle();
        return Commands.sequence(
            // Reset our field centric heading to match the robot
            // facing away from our alliance station wall (0 deg).
            drivetrain.runOnce(() -> drivetrain.seedFieldCentric(Rotation2d.kZero)),
            // Then slowly drive forward (away from us) for 5 seconds.
            drivetrain.applyRequest(() ->
                drive.withVelocityX(0.5)
                    .withVelocityY(0)
                    .withRotationalRate(0)
            )
            .withTimeout(5.0),
            // Finally idle for the rest of auton
            drivetrain.applyRequest(() -> idle)
        );
    }
}
