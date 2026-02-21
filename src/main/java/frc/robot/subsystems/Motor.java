package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Acceleration;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
    TalonFX m_Motor = new TalonFX(0);

    TalonFXConfiguration m_Configs;
    TalonFXConfigurator m_rator;

    Slot0Configs m_PID = new Slot0Configs()
        .withKP(0).withKI(0).withKD(0);

    FeedbackConfigs m_Feedback = new FeedbackConfigs();

    StatusSignal<Angle> m_Angle;
    StatusSignal<Velocity> m_Velovity;
    StatusSignal<Acceleration> m_Position;

    ShuffleboardTab m_Shuff;

    public Motor() {
        m_Configs = new TalonFXConfiguration()
        .withFeedback(m_Feedback)
        .withSlot0(m_PID);

        m_Angle = m_Motor.getPosition();

        m_Shuff = Shuffleboard.getTab("Motor");
    }

    public void dashboard() {
        m_Shuff.addDouble("Detected Angle", () -> m_Angle.getValueAsDouble());
    }

    public void shoot() {
        m_Motor.setVoltage(0);
    } 
}
