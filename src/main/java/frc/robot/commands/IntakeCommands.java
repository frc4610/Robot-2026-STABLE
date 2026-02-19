// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.lib.Constants.MechConstants.IntakeConstants;
import frc.robot.subsystems.Intake;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class IntakeCommands extends Command {
  /** Creates a new IntakeCommands. */
  private final Intake m_Intake;
  private final boolean m_On;

  public IntakeCommands(Intake Intake, boolean on) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Intake = new Intake();
    m_On = on;

    addRequirements(m_Intake);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Intake.IntakeMovement(m_On, IntakeConstants.kIntakeSpeed, IntakeConstants.kEjectSpeed);
    m_Intake.IntakeWristMovements(m_On, IntakeConstants.kTurnDownSpeed, IntakeConstants.kTurnUpSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
