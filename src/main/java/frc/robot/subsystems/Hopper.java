// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants;

public class Hopper extends SubsystemBase {
  // Hopper motors
  TalonFX m_HopperMotorIO = new TalonFX(Constants.Hopper.hopperMotorIOId);
  TalonFX m_HopperMotorIn = new TalonFX(Constants.Hopper.hopperMotorInId);
  // Hopper encoder
  Encoder m_hopperEncoder = new Encoder(Constants.Hopper.hopperEncoder_A, Constants.Hopper.hopperEncoder_B);

  public Hopper() {
    m_HopperMotorIO.setSafetyEnabled(false);
    m_HopperMotorIn.setSafetyEnabled(false);

  }

  // Speed of Hopper Intake/Outake motor
  public void hopperTakeIn(){
    m_HopperMotorIO.set(Constants.Hopper.hopperTakeInSpeed);
  }
  public void hopperStop(){
    m_HopperMotorIO.set(Constants.Hopper.hopperStopSpeed);
  }
  public void hopperRegurgitate(){
    m_HopperMotorIO.set(Constants.Hopper.hopperRegurgitateSpeed);
  }

  // Speed of Hopper Index
  public void indexTakeIn(){
    m_HopperMotorIn.set(Constants.Hopper.indexTakeInSpeed);
  }
  public void indexOutTake(){
    m_HopperMotorIn.set(Constants.Hopper.indexOutTakeSpeed);
  }
  public void indexStop(){
    m_HopperMotorIn.set(Constants.Hopper.indexStopSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
