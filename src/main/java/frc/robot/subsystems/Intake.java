// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants.DeviceIds.IntakeIds;
import frc.robot.lib.Constants.MechConstants.IntakeConstants;

public class Intake extends SubsystemBase {
  /** Creates a new intake. */
    public static TalonFX m_IntakeRoller = new TalonFX(21);
    public static TalonFX m_IntakeWrist = new TalonFX(22);

    
  public Intake() {

    m_IntakeRoller.setSafetyEnabled(false);
    m_IntakeWrist.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

   public void DragIn() {
    /*Sets the shooter motors intake speed */
    m_IntakeRoller.set(.1);
  } 
  public void StopIntakeMotor(){
    /*sets the shooter motor to shut off */
    m_IntakeRoller.set(0);
  }
  public void eject(){
    /*sets the speed that the shooter motor shoots*/
    m_IntakeRoller.set(-.1);
  }

   public void turnUp() {
    /*sets the rate that the intake wrist articulates */
    m_IntakeWrist.set(IntakeConstants.kTurnUpSpeed);
  } 
  public void KillturnMotor(){
    m_IntakeWrist.set(0);
  }
  public void TurnDown(){
    m_IntakeRoller.set(-.1);
  }

}
