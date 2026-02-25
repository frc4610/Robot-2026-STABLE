// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Constants;

public class Intake extends SubsystemBase {

  // Intake motors
  TalonFX m_IntakeMotor = new TalonFX(Constants.DeviceIds.IntakeIds.intakeMotorId);
  TalonFX m_WristMotor = new TalonFX(Constants.DeviceIds.IntakeIds.intakeWristMotorId);

  // Intake Encoder
  //Encoder m_IntakeEncoder = new Encoder(Constants.DeviceIds.IntakeIds.intakeEncoder_A, Constants.DeviceIds.IntakeIds.intakeEncoder_B);
  /** Creates a new Intake. */
  public Intake() {
    // Set intake motor safety
    m_IntakeMotor.setSafetyEnabled(false);
    m_WristMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Intake motor speeds
  public void intakeBall(double speed) {
    m_IntakeMotor.set(0.1); 
  }
  
  public void outakeBall(double speed) {
    m_IntakeMotor.set(-0.1);
  }

  public void intakeStop(double speed) {
    m_IntakeMotor.set(0.0);
  }



  // Wrist motor speeds
  public void intakeWristUp(double speed) {
  m_WristMotor.set(0.1);
  }

  public void intakeWristDown(double speed) {
    m_WristMotor.set(-0.1);
  }
  
  public void intakeWristStop(double speed) {
    m_WristMotor.set(0.0);
  }
}
