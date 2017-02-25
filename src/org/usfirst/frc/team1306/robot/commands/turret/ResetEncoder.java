package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class ResetEncoder extends CommandBase {
	
	public ResetEncoder() {
		requires(turret);
	}
	
	@Override
	protected void initialize() {
		turret.resetEncoder();
	}

	@Override
	protected void execute() {

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
