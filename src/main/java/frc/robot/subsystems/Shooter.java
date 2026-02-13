// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants;

public class Shooter extends SubsystemBase {
  //Shooter motor
  TalonFX m_rollerMotor = new TalonFX(51);
  TalonFX m_shooterWrist = new TalonFX(52);
  TalonFX m_actuatorMotor = new TalonFX(53);

  // Shooter encoder
  Encoder m_wristEncoder = new Encoder(54, 55);
  Encoder m_actuatorEncoder = new Encoder(56, 57);

  public Shooter() {
    // Set safety
    m_rollerMotor.setSafetyEnabled(false);
    m_shooterWrist.setSafetyEnabled(false);
    m_actuatorMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Roller motor speeds
  public void rollerForward(double Speed){
    m_rollerMotor.set(Constants.Shooter.rollerForwardSpeed);
  }
  public void rollerBackward(double Speed){
    m_rollerMotor.set(Constants.Shooter.rollerBackwardSpeed);
  }
  public void rollerStop(double Speed){
    m_rollerMotor.set(Constants.Shooter.rollerStopSpeed);
  }

  // Wrist motor speeds
  public void shooterWristUp(double Speed){
    m_shooterWrist.set(Constants.Shooter.shootWristUpSpeed);
  }
  public void shooterWristDown(double Speed){
    m_shooterWrist.set(Constants.Shooter.shootWristDownSpeed);
  }
  public void shooterWristStop(double Speed){
    m_shooterWrist.set(Constants.Shooter.shootWristUpSpeed);
  }

  // Actuator motor speeds
  public void actuatorPositive(double Speed){
    m_actuatorMotor.set(Constants.Shooter.actuatorPositiveSpeed);
  }
  public void actuatorNegative(double Speed){
    m_actuatorMotor.set(Constants.Shooter.actuatorNegativeSpeed);
  }
  public void actuatorStop(double Speed){
    m_actuatorMotor.set(Constants.Shooter.actuatorStopSpeed);
  }
}
