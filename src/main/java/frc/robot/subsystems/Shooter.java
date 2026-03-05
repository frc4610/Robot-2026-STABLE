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
import frc.robot.lib.constants.DeviceIds.ShooterIds;
import frc.robot.lib.constants.MechConstants.ShooterConstants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public static TalonFX m_ShooterMotor = new TalonFX(ShooterIds.kShooterMotor);

   DutyCycleEncoder m_ShooterEncoder =
   new DutyCycleEncoder(ShooterIds.kShooterEncoderI, ShooterIds.kShooterEncoderII,0);

   double roundedAngle;
  Boolean manMode = false;
  double Speed = ShooterConstants.kShootingSpeed;

  Shuffleboard m_Sensors;
  ShuffleboardTab m_SensorsTab = Shuffleboard.getTab("Sensors");

  PIDController m_ShooterPID = new PIDController(0, 0, 0);


  public Shooter() {
    m_ShooterMotor.setSafetyEnabled(false);
    m_ShooterEncoder.setInverted(true);

    m_SensorsTab.addDouble("Shooter Speed", () -> m_ShooterEncoder.get());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(manMode != true) {
      m_ShooterMotor.set(m_ShooterPID.calculate(m_ShooterEncoder.get(),Speed));
    }
  }
   
  public Command ShootPID () {
    return Commands.runOnce(() -> {Speed = ShooterConstants.kShootingSpeed;
    manMode = false;});
  }
  
  public void Shoot (double speed) {
    m_ShooterMotor.set(speed);
    manMode = true;
  }
  public void revShoot (double speed) {
    m_ShooterMotor.set(speed);
    manMode = true;
  }
  public void stopShooting () {
    m_ShooterMotor.set(ShooterConstants.kKillShooter);
    manMode = true;
  }

  public void getEncoderapprox () {
    roundedAngle = Math.round(m_ShooterEncoder.get());
  }

}
