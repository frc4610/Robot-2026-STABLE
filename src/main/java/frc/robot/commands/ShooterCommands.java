package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.lib.Constants.MechConstants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class ShooterCommands  extends Command {
    private final  Shooter m_Shooter;
    private final boolean m_On;
    
public ShooterCommands(Shooter shooter, boolean on) {
    m_Shooter = new Shooter();
    m_On = on;

    addRequirements(m_Shooter);
}

@Override
public void initialize() {}

@Override
public void execute() {
    m_Shooter.shoot(m_On, ShooterConstants.kRollerSpeeds, ShooterConstants.kReverseRollerSpeeds);
    m_Shooter.ActuatorMovement(m_On, ShooterConstants.kCounterClockwise, ShooterConstants.kClockwise);
}

@Override
public void end(boolean interrupted) {}

@Override
public boolean isFinished() {
    return true;
}
    
}