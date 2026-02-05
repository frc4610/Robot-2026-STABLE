// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants;

public class Hopper extends SubsystemBase {
  /** Creates a new Hopper. */
  TalonFX m_HopperMotorIO = new TalonFX(31);
  TalonFX m_HopperMotorIn = new TalonFX(32);
  public Hopper() {
    m_HopperMotorIO.setSafetyEnabled(false);
    m_HopperMotorIn.setSafetyEnabled(false);


  }
  // Speed of Hopper Intake/Outake motor 
  public void HopperTakeIn(){
    m_HopperMotorIO.set(Constants.Hopper.hopperTakeInSpeed);
    
  }
  public void HopperStop(){
    m_HopperMotorIO.set(Constants.Hopper.hopperStopSpeed);
  }
  public void HopperRegurgitate(){
    m_HopperMotorIO.set(Constants.Hopper.hopperRegurgitateSpeed);
  }
  // Speed of Hopper Index
  public void IndexTakeIn(){
    m_HopperMotorIn.set(Constants.Hopper.indexTakeInSpeed);
  }
  public void IndexOutTake(){
    m_HopperMotorIn.set(Constants.Hopper.indexOutTakeSpeed);
  }
  public void IndexStop(){
    m_HopperMotorIn.set(Constants.Hopper.indexStopSpeed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}