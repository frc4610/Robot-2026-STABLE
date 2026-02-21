// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants;


public class Shooter extends SubsystemBase {
  //Shooter motors
  TalonFX m_rollerMotor = new TalonFX(Constants.DeviceIds.ShooterIds.shooterRollerMotorId);
  TalonFX m_shooterWrist = new TalonFX(Constants.DeviceIds.ShooterIds.shooterWristMotorId);
  TalonFX m_actuatorMotor = new TalonFX(Constants.DeviceIds.ShooterIds.shooterActuatorId);

  // Shooter encoders
  Encoder m_shootWristEncoder = new Encoder(Constants.DeviceIds.ShooterIds.shooterWristEncoder_A, Constants.DeviceIds.ShooterIds.shooterWristEncoder_B);
  Encoder m_actuatorEncoder = new Encoder(Constants.DeviceIds.ShooterIds.shootActuatorEncoder_A, Constants.DeviceIds.ShooterIds.shootActuatorEncoder_B);

  public Shooter() {
    // Shooter motor safety
    m_rollerMotor.setSafetyEnabled(false);
    m_shooterWrist.setSafetyEnabled(false);
    m_actuatorMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Roller set motor speeds
  public void rollerForward(double speed){
    m_rollerMotor.set(0);
  }
  public void rollerBackward(double speed){
    m_rollerMotor.set(0);
  }
  public void rollerStop(double speed){
    m_rollerMotor.set(0);
  }

  // Wrist set motor speeds
  public void shooterWristUp(double speed){
    m_shooterWrist.set(0);
  }
  public void shooterWristDown(double speed){
    m_shooterWrist.set(0);
  }
  public void shooterWristStop(double speed){
    m_shooterWrist.set(0);
  }

  // Actuator set motor speeds
  public void actuatorPositive(double speed){
    m_actuatorMotor.set(0);
  }
  public void actuatorNegative(double speed){
    m_actuatorMotor.set(0);
  }
  public void actuatorStop(double speed){
    m_actuatorMotor.set(0);
  }
}
