// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants.DeviceIds.ClimberIds;
import frc.robot.lib.Constants.MechConstants.ClimberConstants;

public class Climber extends SubsystemBase {
  /** Creates a new climber. */
  public static TalonFX m_ClimbMotor = new TalonFX(ClimberIds.kClimbMotorid);

//public static Encoder m_ClimbEncoder = new Encoder(ClimberIds.kClimbEncoder_A, ClimberIds.kClimbEncoder_B);

  public Climber() {
    m_ClimbMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void Rise(double speed){
    m_ClimbMotor.set(speed);
  }
  public void lower(double speed){
    m_ClimbMotor.set(speed);
  }
  public void stopClimb(double speed){
    m_ClimbMotor.set(speed);
  }
}
