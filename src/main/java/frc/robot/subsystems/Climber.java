// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants;

public class Climber extends SubsystemBase {
  // Climber motor
  TalonFX m_climberMotor = new TalonFX(Constants.Climber.climberMotorId);
  // Climber encoder
  Encoder m_climberEncoder = new Encoder(Constants.Climber.climberEncoder_A, Constants.Climber.climberEncoder_B);

  public Climber() {
    m_climberMotor.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Climb motor speeds
  public void climbUp(){
    m_climberMotor.set(Constants.Climber.climbUpSpeed);
  }

  public void climbDown(){
    m_climberMotor.set(Constants.Climber.climbDownSpeed);
  }

  public void climbStop(){
    m_climberMotor.set(Constants.Climber.climbStop);
  }
}
