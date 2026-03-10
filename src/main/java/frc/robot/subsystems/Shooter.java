// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
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
<<<<<<< HEAD
  public static TalonFX m_LeftShooterMotor = new TalonFX(ShooterIds.kLeftShooterMotor);
  public static TalonFX m_RightShooterMotor = new TalonFX(ShooterIds.kRightShooterMotor);
=======
  public static TalonFX m_ShooterMotor = new TalonFX(ShooterIds.kRightShooterMotor);
  public static TalonFX m_RightShooterMotor = new TalonFX(ShooterIds.kLeftShooterMotor);
>>>>>>> ac094b0bde1cb6a143dd96368569503a29152f13

    /* Encoder */

  //creates a DutyCycleEncoder for shooter subsystem and binds it to IDS 1 and 2
   DutyCycleEncoder m_ShooterEncoder =
   new DutyCycleEncoder(ShooterIds.kShooterEncoderI, ShooterIds.kShooterEncoderII,0);

  
  double roundedAngle;
  Boolean manMode = false;
  double Speed = ShooterConstants.kShootingSpeed1;

    /* Shuffleboard */
  static Shuffleboard m_Sensors;
  static ShuffleboardTab m_SensorsTab = Shuffleboard.getTab("Sensors");

  PIDController m_ShooterPID = new PIDController(0, 0, 0);


  public Shooter() {
    //sets safty for shooter motor
<<<<<<< HEAD

    m_LeftShooterMotor.setSafetyEnabled(false);
=======
    m_ShooterMotor.setSafetyEnabled(false);
>>>>>>> ac094b0bde1cb6a143dd96368569503a29152f13
    m_RightShooterMotor.setSafetyEnabled(false);
    
    //sets the shooter endcoder to inverted 
    m_ShooterEncoder.setInverted(false);

    //m_LeftShooterMotor.setControl(new Follower(ShooterIds.kRightShooterMotor,false));


    //creates sensor tab for shooter 

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(manMode != true) {
      m_LeftShooterMotor.set(m_ShooterPID.calculate(m_ShooterEncoder.get(),Speed));
    }

      //  m_SensorsTab.addDouble("Shooter Speed", () -> m_ShooterEncoder.get());
  }
   
  //creates a shooting actions for PID 
  public Command ShootPID () {
    return Commands.runOnce(() -> {Speed = ShooterConstants.kShootingSpeed1;
    manMode = false;});
  }
  
  //sest the shooting speed to a double 
<<<<<<< HEAD
  public void Shoot (double speed) {
    m_LeftShooterMotor.set(speed);
    m_RightShooterMotor.set(speed);
=======
  public void Shoot (double speed, double speed2) {
    m_ShooterMotor.set(speed);
    m_RightShooterMotor.set(speed2);
>>>>>>> ac094b0bde1cb6a143dd96368569503a29152f13
    manMode = true;
  }


<<<<<<< HEAD
  public void revShoot (double speed) {
    m_LeftShooterMotor.set(speed);
=======
  public void revShoot (double speed, double speed2) {
    m_ShooterMotor.set(speed);
>>>>>>> ac094b0bde1cb6a143dd96368569503a29152f13
    m_RightShooterMotor.set(speed);
    manMode = true;
  }
  public void stopShooting () {
<<<<<<< HEAD
    m_LeftShooterMotor.set(ShooterConstants.kKillShooter);
=======
    m_ShooterMotor.set(ShooterConstants.kKillShooter);
>>>>>>> ac094b0bde1cb6a143dd96368569503a29152f13
    m_RightShooterMotor.set(ShooterConstants.kKillShooter);
    manMode = true;
  }

  public void getEncoderapprox () {
    roundedAngle = Math.round(m_ShooterEncoder.get());
  }



}
