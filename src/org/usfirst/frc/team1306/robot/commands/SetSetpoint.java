package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class SetSetpoint extends CommandBase {

	Setpoint setpoint;
	
	public SetSetpoint(Setpoint setpoint) {
		requires(shooter);
		requires(turret);
		requires(hood);
		
		this.setpoint = setpoint;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		shooter.setRPM(setpoint.getShooterRPM(),setpoint.getIndexerRPM());
		hood.setPos(setpoint.getHoodAngle());
		
		DriverStation.Alliance alliance = DriverStation.getInstance().getAlliance();
		if(alliance.equals(Alliance.Blue)) {
			turret.moveRot(-setpoint.getTurretPos() + turret.getPosition());
		} else {
		turret.moveRot(setpoint.getTurretPos() + turret.getPosition());
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
