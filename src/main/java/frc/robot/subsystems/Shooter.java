// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new shooter. */
  /*instantiates motor and encoder ids to the roller subsystem */
   public static TalonFX m_Rollermotor = new TalonFX(41);
   public static TalonFX m_ActuatorMotor = new TalonFX(42);
    
  // public static Encoder m_RollerEncoder = new Encoder(43,44);
  // public static Encoder m_ShooterEncoder = new Encoder(45, 46);

  public Shooter() {
    m_ActuatorMotor.setSafetyEnabled(false);
    m_Rollermotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Shoot(double speed) {
    m_Rollermotor.set(speed);
  } 
  public void StopShooting(double speed){
    m_Rollermotor.set(speed);
  }
  public void reverseShoot(double speed){
    m_Rollermotor.set(speed);
  }

  public void ActuatorCW(double speed){
    m_ActuatorMotor.set(speed);
  }
  public void ActuatorCCW(double speed) {
    m_ActuatorMotor.set(speed);
  } 
  public void ActuatorDie(double speed){
    m_ActuatorMotor.set(speed);
  }
}
