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
import frc.robot.lib.constants.DeviceIds.IntakeIds;
import frc.robot.lib.constants.MechConstants.IntakeConstants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  public static TalonFX m_IntakeRoller = new TalonFX(IntakeIds.IntakeRoller);
  public static TalonFX m_IntakeWrist = new TalonFX(IntakeIds.kIntakeWrist);

  DutyCycleEncoder m_IntakeEncoder = 
  new DutyCycleEncoder(IntakeIds.kIntakeEncoder, 0,0 );
  
  double roundedAngle;
  Boolean manMode = false;
  Boolean ifOut = false;
  double setpoint;
  //tbd
  PIDController m_wristPID = new PIDController(0, 0, 0);

  static Shuffleboard m_Sensors;
  static ShuffleboardTab m_SensorsTab = Shuffleboard.getTab("Sensors");


  public Intake() {
    m_IntakeRoller.setSafetyEnabled(false);
    m_IntakeWrist.setSafetyEnabled(false);

    m_IntakeEncoder.setInverted(true);

    m_SensorsTab.addDouble("Intake Angle", () -> m_IntakeEncoder.get());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(manMode != true) {
      m_IntakeWrist.set(m_wristPID.calculate(m_IntakeEncoder.get(), setpoint));
    }
  }

  public Command defaultSetpoint () {
    return Commands.runOnce(() -> {setpoint = IntakeConstants.kIntakeUpPOS;
    manMode = false;
    ifOut = false;});
  }

  public Command WristDown () {
    return Commands.runOnce(() -> {setpoint = IntakeConstants.kIntakeDownPOS;
    manMode = false;
    ifOut = true;});
  }

 public void IntakeRollers (double speed) {
    m_IntakeRoller.set(speed);
    manMode = true;
  }
  
  public void reverseIntakeRollers (double speed) {
    m_IntakeRoller.set(speed);
    manMode = true;
  }

  public void stopIntakeRollers () {
    m_IntakeRoller.set(IntakeConstants.kKillIntakeRoller);
    manMode = true;
  }

  public void ArticulateUp (double speed) {
    m_IntakeWrist.set(speed);
    manMode = true;
  }

  public void ArticulateDown (double speed) {
    m_IntakeWrist.set(speed);
    manMode = true;
  }

  public void wristStop () {
    m_IntakeWrist.set(IntakeConstants.kKillIntakeWrist);
    manMode = true;
  }

  public void getEncoderapprox () {
    roundedAngle = Math.round(m_IntakeEncoder.get());
  }

}
