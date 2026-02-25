// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Constants;

public class Shooter extends SubsystemBase {

//Shooter motors
  TalonFX m_rollerMotor = new TalonFX(Constants.DeviceIds.ShooterIds.shooterRollerMotorId);

  // Shooter encoders
  //Encoder m_shooterRollerEncoder = new Encoder(Constants.DeviceIds.ShooterIds.shooterRollerEncoder_A, Constants.DeviceIds.ShooterIds.shooterRollerEncoder_B);

  /** Creates a new Shooter. */
  public Shooter() {
    // Shooter motor safety
    m_rollerMotor.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Roller set motor speeds
  public void rollerForward(double speed) {
    m_rollerMotor.set(0.25);
  }

  public void rollerBackward(double speed) {
    m_rollerMotor.set(-0.25);
  }

  public void rollerStop(double speed) {
    m_rollerMotor.set(0.0);
  }

  public void tired() {
    System.out.println("tired...");
  }
}
