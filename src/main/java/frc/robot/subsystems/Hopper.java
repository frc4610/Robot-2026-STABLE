// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants.DeviceIds.HopperIds;


public class Hopper extends SubsystemBase {  
    public static TalonFX m_HopperMotor = new TalonFX(HopperIds.kHopperMotor); 
    public static TalonFX m_IndexMotor = new TalonFX(47);

    public static Encoder m_IndexEncoder = new Encoder(48, 49);
    public static Encoder m_HopperEncoder = new Encoder(HopperIds.kHopperEncoder_A, HopperIds.kHopperEncoder_B);
  /** Creates a new hopper. */
  public Hopper() {
       m_HopperMotor.setSafetyEnabled(false);
       m_IndexMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void HopperMovement(boolean on, double speed, double ReverseSpeed){
    if (on == true) {
      m_HopperMotor.set(speed);
    } else if(on != true) {
      m_HopperMotor.stopMotor();
    } else {
      m_HopperMotor.set(ReverseSpeed);
    }
  }

  public void IndexerMovement(boolean on, double speed, double ReverseSpeed){
    if (on == true) {
      m_IndexMotor.set(speed);
    } else if (on != true){
      m_IndexMotor.stopMotor();
    } else {
      m_HopperMotor.set(ReverseSpeed);
    }
  }
  
}
