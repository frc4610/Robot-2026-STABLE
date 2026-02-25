// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Constants;

public class Climber extends SubsystemBase {

  //Climber Motor
  TalonFX m_ClimberMotor = new TalonFX(Constants.DeviceIds.ClimberIds.climberMotorId);

  //Climber Encoder
  //Encoder m_ClimberEncoder = new Encoder(Constants.DeviceIds.ClimberIds.climberEncoder_A, Constants.DeviceIds.ClimberIds.climberEncoder_B);
  /** Creates a new Climber. */
  public Climber() {
    //Climber Motor Safety
    m_ClimberMotor.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  //Climb Motor Speeds
  public void climbUp(double speed) {
    m_ClimberMotor.set(0.1);
  }

  public void climbDown(double speed) {
    m_ClimberMotor.set(-0.1);
  }

  
  public void climbStop(double speed) {
    m_ClimberMotor.set(0);
  }
}
