// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.constants.DeviceIds.HopperIds;
import frc.robot.lib.constants.MechConstants.HopperConstants;


public class Hopper extends SubsystemBase {
  /** Creates a new Hopper. */

  public static TalonFX m_HopperMotor = new TalonFX(HopperIds.kHopperMotor);

  DutyCycleEncoder m_HopperEncoder =
  new DutyCycleEncoder(HopperIds.kHopperEncoder, 1,0 );

  Boolean manMode = false;

  Shuffleboard m_HopperShuffle;
  ShuffleboardTab m_HopperTab = Shuffleboard.getTab("Sensors");
  public Hopper() {
    m_HopperMotor.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void moveForward (double speed) {
    m_HopperMotor.set(speed);
    manMode = true;
  }

  public void moveBackwards(double speed) {
    m_HopperMotor.set(speed);
    manMode = true;
  }

  public void KillHopper() {
    m_HopperMotor.set(HopperConstants.kStopHopper);
    manMode = true;
  }

}
