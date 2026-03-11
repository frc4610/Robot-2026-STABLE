// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.constants.DeviceIds.HopperIds;
import frc.robot.lib.constants.MechConstants.HopperConstants;


public class Hopper extends SubsystemBase {
  /** Creates a new Hopper. */

  public static TalonFX m_HopperMotor = new TalonFX(HopperIds.kHopperMotor);

  Boolean manMode = false;
  double speed = HopperConstants.kForwardHopperSpeed;

  static Shuffleboard m_Sensors;
  static ShuffleboardTab m_SensorsTab = Shuffleboard.getTab("Sensors");

  //tbd
 // PIDController m_HopperPID = new PIDController(0, 0, 0);

  public Hopper() {
    m_HopperMotor.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
    public Command HopperForward () {
    return Commands.runOnce(() -> { speed = HopperConstants.kForwardHopperSpeed;
    manMode = false;});
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
    m_HopperMotor.set(HopperConstants.kKillHopper);
    manMode = true;
  }

}
