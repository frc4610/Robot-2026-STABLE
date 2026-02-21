// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.lib.Constants.MechConstants.HopperConstants;
import frc.robot.subsystems.Hopper;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */

public class HopperCommands extends Command {
  /** Creates a new HopperCommands. */
  private final Hopper m_hopper;
  private final boolean m_On;

  public HopperCommands(Hopper hopper, boolean on) {
    // Use addRequirements() here to declare subsystem dependencies.
      m_hopper = new Hopper();
      m_On = on;
      addRequirements(m_hopper);
      
      }
    
    // Called when the command is initially scheduled.  
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_hopper.HopperMovement(m_On, HopperConstants.kForwardIndexer, HopperConstants.KBackwardSpeed);
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
  
}
