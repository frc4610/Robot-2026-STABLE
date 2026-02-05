// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import java.security.Key;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants;

public class Intake extends SubsystemBase {
  // Encoder for Intake
  Encoder m_intake = new Encoder(0, 1);

  // Setting motor IDs
  TalonFX m_IntakeMotor = new TalonFX(21);
  TalonFX m_WristMotor = new TalonFX(22);
  //Setting wrist motor to PID
  PIDController m_wrist = new PIDController(0.50, 0.05, 0.05);
  //Setpoint starts at 0
  double setpoint = 0;
  //Not manual mode
  Boolean manMode = false;
  
  /** Creates a new Intake. */
  public Intake() {
    //Safety is our Number 1 priority
    m_IntakeMotor.setSafetyEnabled(false);
    m_WristMotor.setSafetyEnabled(false);
        
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //Calculates new PID
    if(manMode != true){
      m_WristMotor.set(m_wrist.calculate(m_intake.get(), setpoint));
    }
  }

  public void Intakeball(){
    //Intake: If button is held, then output motors to spin in opposite directions
    m_IntakeMotor.set(Constants.Intake.inBallSpeed);

  }
  public void Outtakeball(){
    //Outtake: If a different button is held then output motors to spin in a reserve direction
    m_IntakeMotor.set(Constants.Intake.outBallSpeed);

  }
  public void Intakestop(){
    //Once released the motors stop spinning
    m_IntakeMotor.set(Constants.Intake.stopBallSpeed);
    
  }
  public void Wriststop(){
    //If button is released motors stop spinning
    m_WristMotor.set(Constants.Intake.stopWristSpeed);

  }
  public Command Wristup(){
    //If button is pressed setpoint will be set to point 0
    return Commands.runOnce(() -> {setpoint = Constants.Intake.upSetPoint;
      manMode = false;});
  
  }
  public Command Wristdown(){
        //If button is pressed setpoint will be set to point 270
    return Commands.runOnce(() -> {setpoint = Constants.Intake.downSetPoint;
      manMode = false;});

  }
}