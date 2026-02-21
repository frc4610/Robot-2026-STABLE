// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants.DeviceIds.ShooterIds;

public class Shooter extends SubsystemBase {
  /** Creates a new shooter. */
  /*instantiates motor and encoder ids to the roller subsystem */
   public static TalonFX m_Rollermotor = new TalonFX(ShooterIds.kRollerMotor);
   public static TalonFX m_ActuatorMotor = new TalonFX(ShooterIds.kActuatorMotor);
    
   public static Encoder m_RollerEncoder = new Encoder(ShooterIds.kRollerEncoder_A,ShooterIds.kRollerEncoder_B);
   public static Encoder m_ShooterEncoder = new Encoder(ShooterIds.kShooterEncoder_A, ShooterIds.kShooterEncoder_B);

  public Shooter() {
    m_ActuatorMotor.setSafetyEnabled(false);
    m_Rollermotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot (boolean on, double speed, double ReverseSpeed){
    if (on == true) {
      m_Rollermotor.set(speed);
    } else if (on != true) {
      m_Rollermotor.stopMotor();
    } else {
       m_Rollermotor.set(ReverseSpeed);
    }
  }

  
  public void ActuatorMovement(boolean on, double speed, double ReverseSpeed){
    if (on == true) {
      m_ActuatorMotor.set(speed);
    } else if(on != true) {
      m_ActuatorMotor.stopMotor();
    } else {
      m_ActuatorMotor.set(ReverseSpeed);
    }
  }

}
