// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.Constants.DeviceIds.HopperIds;
import frc.robot.lib.Constants.MechConstants.HopperConstants;

public class Hopper extends SubsystemBase {  
   public static TalonFX m_HopperMotor = new TalonFX(HopperIds.kHopperMotor); 
   public static TalonFX m_IndexMotor = new TalonFX(47);

  /*  public static Encoder m_IndexEncoder = new Encoder(48, 49);
    public static Encoder m_HopperEncoder = new Encoder(HopperIds.kHopperEncoder_A, HopperIds.kHopperEncoder_B);*/
  /** Creates a new hopper. */
  public Hopper() {
       m_HopperMotor.setSafetyEnabled(false);
       m_IndexMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void MoveFoward(double speed) {
    m_HopperMotor.set(speed);
  }
  public void MoveBackward(double speed) {
    m_HopperMotor.set(speed);
  }
  public void StopMovement(double speed) {
    m_HopperMotor.set(speed);
  }
    public void ForwardIndexer(double speed) {
    m_IndexMotor.set(speed);
  }
  public void BackwardIndexer(double speed) {
    m_IndexMotor.set(speed);
  }
  
}
