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

import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Hopper;

public class RobotContainer {
  private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top
                                                                                      // speed
  private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max
                                                                                    // angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

  private final Telemetry logger = new Telemetry(MaxSpeed);

  private final CommandXboxController joystick = new CommandXboxController(0);

  public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

  // creates controller (x)in and (a)out
  CommandXboxController m_OperatorController = new CommandXboxController(1);

  // intake = intake.java
  Intake m_Intake = new Intake();
  Hopper m_Hopper = new Hopper();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // Note that X is defined as forward according to WPILib convention,
    // and Y is defined as to the left according to WPILib convention.
    drivetrain.setDefaultCommand(
        // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ));

    // Idle while the robot is disabled. This ensures the configured
    // neutral mode is applied to the drive motors while disabled.
    final var idle = new SwerveRequest.Idle();
    RobotModeTriggers.disabled().whileTrue(
        drivetrain.applyRequest(() -> idle).ignoringDisable(true));

    joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
    joystick.b().whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))));

    // Run SysId routines when holding back/start and X/Y.
    // Note that each routine should be run exactly once in a single log.
    joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
    joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
    joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
    joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

    // Reset the field-centric heading on left bumper press.
    joystick.leftBumper().onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

    drivetrain.registerTelemetry(logger::telemeterize);

    // 6 binds used

    // Intake bind = (a)
    m_OperatorController.a().whileTrue(Commands.startEnd(
        () -> m_Intake.Intakeball(), () -> m_Intake.Intakestop(), m_Intake));

    // Intake and hopper bind = (RightBumper)
    m_OperatorController.rightBumper().whileTrue(Commands.startEnd(
        () -> m_Hopper.HopperTakeIn(), () -> m_Hopper.HopperStop(), m_Hopper));

    m_OperatorController.rightBumper().whileTrue(Commands.startEnd(
        () -> m_Intake.Intakeball(), () -> m_Intake.Intakestop(), m_Intake));

    // HopperIN and IndexIn (RightTrigger)
    m_OperatorController.rightTrigger().whileTrue(Commands.startEnd(
        () -> m_Hopper.HopperTakeIn(), () -> m_Hopper.HopperStop(), m_Hopper));

    m_OperatorController.rightBumper().whileTrue(Commands.startEnd(
        () -> m_Hopper.IndexTakeIn(), () -> m_Hopper.IndexStop(), m_Intake));

    // Outtake bind = (X)
    m_OperatorController.x().whileTrue(Commands.startEnd(
        () -> m_Intake.Outtakeball(), () -> m_Intake.Intakestop(), m_Intake));

    // hopper Out/Regeguritate = (LeftBumper)
    m_OperatorController.leftBumper().whileTrue(Commands.startEnd(
        () -> m_Hopper.HopperRegurgitate(), () -> m_Hopper.HopperStop(), m_Hopper));

    // Wrist up = (pov up)
    m_OperatorController.povUp().whileTrue(Commands.startEnd(
        () -> m_Intake.Wristup(), () -> m_Intake.Wriststop(), m_Intake));

    // Wrist down = (Pov Down)
    m_OperatorController.povUp().whileTrue(Commands.startEnd(
        () -> m_Intake.Wristdown(), () -> m_Intake.Wriststop(), m_Intake));

    // index out = (b)
    m_OperatorController.b().whileTrue(Commands.startEnd(
        () -> m_Hopper.IndexOutTake(), () -> m_Hopper.IndexStop(), m_Intake));

  }

  public Command getAutonomousCommand() {
    // Simple drive forward auton
    final var idle = new SwerveRequest.Idle();
    return Commands.sequence(
        // Reset our field centric heading to match the robot
        // facing away from our alliance station wall (0 deg).
        drivetrain.runOnce(() -> drivetrain.seedFieldCentric(Rotation2d.kZero)),
        // Then slowly drive forward (away from us) for 5 seconds.
        drivetrain.applyRequest(() -> drive.withVelocityX(0.5)
            .withVelocityY(0)
            .withRotationalRate(0))
            .withTimeout(5.0),
        // Finally idle for the rest of auton
        drivetrain.applyRequest(() -> idle));
  }

}
