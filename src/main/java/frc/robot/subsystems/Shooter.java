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
  TalonFX m_rollerMotor = new TalonFX(Constants.Shooter.rollerMotorId);
  TalonFX m_shooterWrist = new TalonFX(Constants.Shooter.shooterWristMotorId);
  TalonFX m_actuatorMotor = new TalonFX(Constants.Shooter.actuatorMotorId);

  // Shooter encoder
  Encoder m_shootWristEncoder = new Encoder(Constants.Shooter.shootWristEncoder_A, Constants.Shooter.shootWristEncoder_B);
  Encoder m_actuatorEncoder = new Encoder(Constants.Shooter.actuatorEncoder_A, Constants.Shooter.actuatorEncoder_B);

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
  public void rollerForward(){
    m_rollerMotor.set(Constants.Shooter.rollerForwardSpeed);
  }
  public void rollerBackward(){
    m_rollerMotor.set(Constants.Shooter.rollerBackwardSpeed);
  }
  public void rollerStop(){
    m_rollerMotor.set(Constants.Shooter.rollerStopSpeed);
  }

  // Wrist motor speeds
  public void shooterWristUp(){
    m_shooterWrist.set(Constants.Shooter.shootWristUpSpeed);
  }
  public void shooterWristDown(){
    m_shooterWrist.set(Constants.Shooter.shootWristDownSpeed);
  }
  public void shooterWristStop(){
    m_shooterWrist.set(Constants.Shooter.shootWristUpSpeed);
  }

  // Actuator motor speeds
  public void actuatorPositive(){
    m_actuatorMotor.set(Constants.Shooter.actuatorPositiveSpeed);
  }
  public void actuatorNegative(){
    m_actuatorMotor.set(Constants.Shooter.actuatorNegativeSpeed);
  }
  public void actuatorStop(){
    m_actuatorMotor.set(Constants.Shooter.actuatorStopSpeed);
  }
}
