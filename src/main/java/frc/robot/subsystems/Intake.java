// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {
  /** Creates a new intake. */
    public static TalonFX m_IntakeRoller = new TalonFX(21);
    public static TalonFX m_IntakeWrist = new TalonFX(22);

    public static Encoder m_IntakeEncoder = new  Encoder(0, 0);

    
  public Intake() {

   m_IntakeRoller.setSafetyEnabled(false);
    m_IntakeWrist.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

   public void IntakeMovement(boolean on, double speed, double ReverseSpeed) {
    /*Sets the shooter motors intake speed */
    if (on == true) {
    m_IntakeRoller.set(speed);
    } else if(on != true) {
      m_IntakeRoller.stopMotor();
    } else {
      m_IntakeRoller.set(ReverseSpeed);
    }
  } 

   public void IntakeWristMovements(boolean on, double speed, double ReverseSpeed) {
    /*sets the rate that the intake wrist articulates */
    if (on == true) {
    m_IntakeWrist.set(speed);
    } else if(on != true) {
      m_IntakeWrist.stopMotor();
    } else {
      m_IntakeWrist.set(ReverseSpeed);
    }
  } 

}
