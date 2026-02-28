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
import frc.robot.lib.constants.DeviceIds;
import frc.robot.lib.constants.MechConstants.ClimbConstants;
import frc.robot.lib.constants.MechConstants.HopperConstants;
import frc.robot.lib.constants.MechConstants.IntakeConstants;
import frc.robot.lib.constants.MechConstants.ShooterConstants;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
    public final Intake m_Intake = new Intake();
    public final Shooter m_shooter = new Shooter();
    public final Hopper m_Hopper = new Hopper();
    public final Climb m_Climber = new Climb();

    public final CommandXboxController m_OperatorManual = new CommandXboxController(DeviceIds.kOperatorController);
    public final CommandXboxController m_OperatorPID = new CommandXboxController(DeviceIds.kPIDoperatorontrollerport);
    public final CommandXboxController m_driverController = new CommandXboxController(DeviceIds.kDriverControllerPort);


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

 

  public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

  // creates controller (x)in and (a)out


  public RobotContainer() {
    configureBindings();
    OperatorBindings();
    PIDControllerBindings();  
  }

  private void configureBindings() {
    // Note that X is defined as forward according to WPILib convention,
    // and Y is defined as to the left according to WPILib convention.
    drivetrain.setDefaultCommand(
        // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-m_driverController.getLeftY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-m_driverController.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-m_driverController.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ));

    // Idle while the robot is disabled. This ensures the configured
    // neutral mode is applied to the drive motors while disabled.
    final var idle = new SwerveRequest.Idle();
    RobotModeTriggers.disabled().whileTrue(
        drivetrain.applyRequest(() -> idle).ignoringDisable(true));

    m_driverController.a().whileTrue(drivetrain.applyRequest(() -> brake));
    m_driverController.b().whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(-m_driverController.getLeftY(), -m_driverController.getLeftX()))));

    // Run SysId routines when holding back/start and X/Y.
    // Note that each routine should be run exactly once in a single log.
    m_driverController.back().and(m_driverController.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
    m_driverController.back().and(m_driverController.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
    m_driverController.start().and(m_driverController.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
    m_driverController.start().and(m_driverController.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

    // Reset the field-centric heading on left bumper press.
    m_driverController.leftBumper().onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

    drivetrain.registerTelemetry(logger::telemeterize);

    // 6 binds used
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
            drivetrain.applyRequest(() -> idle)
        );
    }

    
    public void  OperatorBindings () {
        /* Intake Roller Bindings */
    m_OperatorManual.a().whileTrue(Commands.startEnd(
        () -> m_Intake.IntakeRollers(IntakeConstants.kIntakeSpeed), 
        () -> m_Intake.stopIntakeRollers(), 
        m_Intake));

    m_OperatorManual.b().whileTrue(Commands.startEnd(
        () -> m_Intake.reverseIntakeRollers(IntakeConstants.kReverseIntakeSpeed),
        () -> m_Intake.stopIntakeRollers(),
        m_Intake));

        /* Intake Wrist Bindings */
    m_OperatorManual.povLeft().whileTrue(Commands.startEnd(
        () -> m_Intake.ArticulateUp(IntakeConstants.kIntakeWristUpSpeed), 
        () -> m_Intake.wristStop(), 
        m_Intake));

    m_OperatorManual.povRight().whileTrue(Commands.startEnd(
        () -> m_Intake.ArticulateDown(IntakeConstants.kIntakeWristUpSpeed), 
        () -> m_Intake.wristStop(), 
        m_Intake));

        /* Hopper Bindings */
    m_OperatorManual.leftBumper().whileTrue(Commands.startEnd(
        () -> m_Hopper.moveForward(HopperConstants.kForwardHopperSpeed), 
        () -> m_Hopper.KillHopper(), 
        m_Hopper));
        
    m_OperatorManual.rightBumper().whileTrue(Commands.startEnd(
      () -> m_Hopper.moveBackwards(HopperConstants.KBackwardHopperSpeed),
      () -> m_Hopper.KillHopper(), 
      m_Hopper));

        /* Shooter Bindings */
    m_OperatorManual.x().whileTrue(Commands.startEnd(
        ()-> m_shooter.Shoot(ShooterConstants.kShootingSpeed), 
        () -> m_shooter.stopShooting(),
        m_shooter));

    m_OperatorManual.y().whileTrue(Commands.startEnd(
        () -> m_shooter.revShoot(ShooterConstants.kReverseShooterSpeed), 
        () -> m_shooter.stopShooting(), 
        m_shooter));

        /*Climber Bindings */
    m_OperatorManual.povUp().whileTrue(Commands.startEnd(
        () -> m_Climber.climb(ClimbConstants.kClimbingSpeed), 
        () -> m_Climber.stopClimb(),
        m_Climber));

    m_OperatorManual.povDown().whileTrue(Commands.startEnd(
        () -> m_Climber.lower(ClimbConstants.kLowerClimbSpeed), 
        () -> m_Climber.stopClimb(), 
        m_Climber));
    }

  public void PIDControllerBindings() {

        /* Intake PID actions */
    m_OperatorPID.a().onTrue(m_Intake.WristDown());

    m_OperatorPID.b().onTrue(m_Intake.defaultSetpoint());

        /* Climbing actions */
    m_OperatorPID.povCenter().onTrue(m_Climber.defaultSetPoint());

    m_OperatorPID.povUp().onTrue(m_Climber.ClimbPOS());

    m_OperatorPID.povDown().onTrue(m_Climber.Climbing());

        /* Command for compact/defence mode */
    m_OperatorPID.leftBumper().onTrue(Commands.parallel(
        m_Climber.defaultSetPoint().alongWith(m_Intake.defaultSetpoint())));

        /* Shooter Speeds */
    m_OperatorPID.x().onTrue(m_shooter.ShootPID());

        /* hopper Speeds */
    m_OperatorPID.leftBumper().onTrue(m_Hopper.HopperForward());
 


  }




}
