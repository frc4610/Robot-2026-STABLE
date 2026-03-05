// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.constants.DeviceIds.climbIds;
import frc.robot.lib.constants.MechConstants.ClimbConstants;

public class Climb extends SubsystemBase {
    /* Motors */
  public static TalonFX  m_ClimbMotor = new TalonFX(climbIds.kClimbMotor);
  
    /* Encoders */
  DutyCycleEncoder m_CLimbEncoder = 
  new DutyCycleEncoder(climbIds.kClimbEncoderId, 2, 0);

    /* Shuffleboard */
  static Shuffleboard m_Sensors;
  static ShuffleboardTab m_SensorsTab = Shuffleboard.getTab("Sensors");

  double roundedAngle;
  Boolean manMode = false;
  Boolean Out = false;

  double setpoint;
  PIDController m_climbPID = new PIDController(0, 0, 0);

  /** Creates a new Climb. */
  public Climb() {
    /* Motor Safety */
    m_ClimbMotor.setSafetyEnabled(false);
    m_CLimbEncoder.setInverted(true);

    


  }

  @Override
  public void periodic() {
  // This method will be called once per scheduler run
  if(manMode!= true) {
    m_ClimbMotor.set(m_climbPID.calculate(m_CLimbEncoder.get(), setpoint));
    }
   // m_SensorsTab.addDouble("Climb Angle", () -> m_CLimbEncoder.get());
  }

    /* Sets the angle of the Climber to be extended */
  public Command ClimbingPOS  () {
    return Commands.runOnce(() -> { setpoint = ClimbConstants.kHookingPOS;
    manMode = false;
    });
  }

  public Command HookingClimbPOS() {
    return Commands.runOnce(() -> { setpoint = ClimbConstants.kClimbingPOS;
    manMode = false;});
  }

  public Command defaultClimbPOS () {
    return Commands.runOnce(() -> { setpoint = ClimbConstants.kDefence;
    manMode = false;});
  }
  
  public void climb (double speed) {
    m_ClimbMotor.set(speed);
    manMode = true;
  }

  public void lower (double speed) {
    m_ClimbMotor.set(speed);
    manMode = true;
  }

  public void stopClimb () {
    m_ClimbMotor.set(ClimbConstants.kStopClimbing);
    manMode = true;
  }

  public void getEncoderapprox () {
    roundedAngle = Math.round(m_CLimbEncoder.get());
  }




}
