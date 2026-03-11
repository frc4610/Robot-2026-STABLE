// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import java.net.Authenticator.RequestorType;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.QuadratureConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ControlModeValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.constants.DeviceIds.ShooterIds;
import frc.robot.lib.constants.MechConstants.ShooterConstants;

public class Shooter extends SubsystemBase {
    /** Creates a new Shooter. */

    /* Motor */

  //creates a shooter motor and binds it to ID 41
  public static TalonFX m_LeftShooterMotor = new TalonFX(ShooterIds.kLeftShooterMotor);
  public static TalonFX m_RightShooterMotor = new TalonFX(ShooterIds.kRightShooterMotor);

  private final VelocityVoltage velocityControl =  new VelocityVoltage(0);

  Slot0Configs m_Feedback = new Slot0Configs()
    .withKP(0.1).withKI(0.0).withKD(0.0);

  TalonFXConfiguration m_Configs = 
    new TalonFXConfiguration();

    /* Encoder */

  //creates a DutyCycleEncoder for shooter subsystem and binds it to IDS 1 and 2
  public static Encoder m_ShooterQuadEncoder = new Encoder(ShooterIds.kShooterEncoderI, ShooterIds.kShooterEncoderII);

  double roundedAngle;
  Boolean manMode = false;
  double Speed = ShooterConstants.kShootingSpeed1;

    /* Shuffleboard */
  static Shuffleboard m_Sensors;
  static ShuffleboardTab m_SensorsTab = Shuffleboard.getTab("Sensors");


  public Shooter() {
    //sets safty for shooter motor

    m_LeftShooterMotor.setSafetyEnabled(false);
    m_RightShooterMotor.setSafetyEnabled(false);
    
    //sets the shooter endcoder to inverted 
    m_ShooterQuadEncoder.setReverseDirection(true);

    m_LeftShooterMotor.setControl(new Follower(ShooterIds.kRightShooterMotor, MotorAlignmentValue.Aligned) );
    m_SensorsTab.addDouble("Shooter Speed", () -> m_ShooterQuadEncoder.getRate());
    m_SensorsTab.addDouble("velocity ", () -> m_ShooterQuadEncoder.getDistancePerPulse());

    m_ShooterQuadEncoder.setDistancePerPulse(1.0);

    //creates sensor tab for shooter 

    m_Configs.withSlot0(m_Feedback).withCurrentLimits(new CurrentLimitsConfigs());
  }
  public double getShooterVel() {

    double RotationsPerSecond = m_ShooterQuadEncoder.getRate();

    double rpm = RotationsPerSecond * 60.0;
    return rpm;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    /*if(manMode != true) {
      m_LeftShooterMotor.set(m_ShooterPID.calculate(m_ShooterQuadEncoder.get(),Speed));
    }*/

    setVelocityRPM(10);
    
  }

  public void setVelocityRPM(double rpm) {
    var leftMotorPositionSignal = m_LeftShooterMotor.getPosition(true);
    var rightMotorPositionSignal = m_RightShooterMotor.getPosition(true);
    var leftMotorVelcitySignal = m_LeftShooterMotor.getVelocity(true); // vel to be calc'ed for pid
    var rightMotorVelocitySignal = m_RightShooterMotor.getVelocity(true); // above

    double rps = rpm / 60;

    double desiredVelocity = 1;
    VelocityVoltage m_VelReq = new VelocityVoltage(0).withSlot(0);


    m_LeftShooterMotor.setControl(m_VelReq.withVelocity(rps));
    m_RightShooterMotor.setControl(new Follower(m_LeftShooterMotor.getDeviceID(), MotorAlignmentValue.Aligned));

    m_SensorsTab.addDouble("Left Motor Position", () -> leftMotorPositionSignal.getValueAsDouble());
    m_SensorsTab.addDouble("Right Motor Position", () -> rightMotorPositionSignal.getValueAsDouble());
    m_SensorsTab.addDouble("Left Motor Velocity", () -> leftMotorVelcitySignal.getValueAsDouble());
    m_SensorsTab.addDouble("Left Motor Position", () -> rightMotorVelocitySignal.getValueAsDouble());
  }
   
  //creates a shooting actions for PID 
  public Command ShootPID () {
    return Commands.runOnce(() -> {Speed = ShooterConstants.kShootingSpeed1;
    manMode = false;});
  }
  
  //sest the shooting speed to a double 
  public void Shoot (double speed) {
    m_LeftShooterMotor.set(speed);
    m_RightShooterMotor.set(speed);
    manMode = true;
  }


  public void revShoot (double speed) {
    m_LeftShooterMotor.set(speed);
    m_RightShooterMotor.set(speed);
    manMode = true;
  }
  public void stopShooting () {
    m_LeftShooterMotor.set(ShooterConstants.kKillShooter);
    m_RightShooterMotor.set(ShooterConstants.kKillShooter);
    manMode = true;
  }

  public void getEncoderapprox () {
    roundedAngle = Math.round(m_ShooterQuadEncoder.get());
  }



}
