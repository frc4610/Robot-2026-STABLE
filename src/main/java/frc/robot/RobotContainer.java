// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import java.io.FilenameFilter;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.commands.ClimbCommands;
import frc.robot.commands.HopperCommands;
import frc.robot.commands.IntakeCommands;
import frc.robot.commands.ShooterCommands;
import frc.robot.commands.parallel.IntakeHopperCommands;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class RobotContainer {

    Intake m_Intake = new Intake();
    Shooter m_shooter = new Shooter();
    Hopper m_Hopper = new Hopper();
    Climber m_Climber = new Climber();

    private final ShooterCommands m_Shoot = new ShooterCommands(m_shooter, true);
    private final ShooterCommands m_StopShoot = new ShooterCommands(m_shooter, false);
    private final ShooterCommands m_Acuator = new ShooterCommands(m_shooter, true);
    private final ShooterCommands m_StopAcuator = new ShooterCommands(m_shooter, false);
    private final ClimbCommands m_climb = new ClimbCommands(m_Climber, true);
    private final ClimbCommands m_StopClimb = new ClimbCommands(m_Climber, false);
    private final IntakeCommands m_Dragin = new IntakeCommands(m_Intake, true);
    private final IntakeCommands m_stopIntake = new IntakeCommands(m_Intake, false);
    private final HopperCommands m_MoveForwardIndexer = new HopperCommands(m_Hopper, true);
    private final HopperCommands m_StopIndexer = new HopperCommands(m_Hopper, false);
    private final HopperCommands m_HopperMovement = new HopperCommands(m_Hopper, true);
    private final HopperCommands m_StopHopper = new HopperCommands(m_Hopper, false);
    private final IntakeHopperCommands m_IntakeAndIndexer = new IntakeHopperCommands(m_Intake, m_Hopper, true);
    private final IntakeHopperCommands m_StopBoth = new IntakeHopperCommands(m_Intake, m_Hopper, false);
    
    public final CommandXboxController m_OperatorController = new CommandXboxController(1);

    private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(MaxSpeed);

    private final CommandXboxController joystick = new CommandXboxController(0);

    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

    public RobotContainer() {
        configureBindings();
        OperatorBindings();
    }

    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
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
    /*bindings for Operator controller */
    public void OperatorBindings (){
            /*Intake Bindings*/
        m_OperatorController.povRight().whileTrue(Commands.parallel(
            m_Dragin, m_stopIntake));

        m_OperatorController.povLeft().whileTrue(Commands.parallel(
            m_MoveForwardIndexer, m_StopIndexer)); 

        m_OperatorController.povDown().whileTrue(Commands.parallel(
            m_HopperMovement, m_StopHopper));

        m_OperatorController.povUp().whileTrue(Commands.parallel(
            m_IntakeAndIndexer, m_StopBoth));

        m_OperatorController.x().whileTrue(Commands.parallel(
            m_climb, m_StopClimb));

        m_OperatorController.y().whileTrue(Commands.parallel(
            m_Shoot, m_StopShoot));
        
        m_OperatorController.a().whileTrue(Commands.parallel(
            m_Acuator, m_StopAcuator));
       }
       

}
