// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Constants;

public class Hopper extends SubsystemBase {

// Hopper motors
  TalonFX m_HopperMotorIO = new TalonFX(Constants.DeviceIds.HopperIds.hopperMotorId);

  // Hopper encoder
  //Encoder m_hopperEncoder = new Encoder(Constants.DeviceIds.HopperIds.hopperEncoder_A, Constants.DeviceIds.HopperIds.hopperEncoder_B);

  public Hopper() {
    // Set hopper motor safety
    m_HopperMotorIO.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }



  // Speed of Hopper Intake/Outake motor
  public void hopperTakeIn(double speed){
    m_HopperMotorIO.set(0.1);
  }

  public void hopperRegurgitate(double speed){
    m_HopperMotorIO.set(-0.1);
  }

  public void hopperStop(double speed){
    m_HopperMotorIO.set(0.0);
  }

}
