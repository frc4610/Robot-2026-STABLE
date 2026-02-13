// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants;

public class Intake extends SubsystemBase {
  // Intake motors
  TalonFX m_IntakeMotor = new TalonFX(0);
  TalonFX m_WristMotor = new TalonFX(0);
  // Intake Encoder
  Encoder m_intake = new Encoder(0, 0);

  public Intake() {
    // Set safety
    m_IntakeMotor.setSafetyEnabled(false);
    m_WristMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Intake motor speeds
  public void intakeBall(){
    m_IntakeMotor.set(Constants.Intake.inBallSpeed);
  }
  public void outakeBall(){
    m_IntakeMotor.set(Constants.Intake.outBallSpeed);
  }
  public void intakeStop(){
    m_IntakeMotor.set(Constants.Intake.stopBallSpeed);
  }

  // Wrist motor speeds
  public void wristUp(){
    m_WristMotor.set(Constants.Intake.upSetPoint);
  }
  public void wristDown(){
    m_WristMotor.set(Constants.Intake.downSetPoint);
  }
  public void wristStop(){
    m_WristMotor.set(Constants.Intake.stopWristSpeed);
  }
}
