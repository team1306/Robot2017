package org.usfirst.frc.team1306.robot.commands.gearmech;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class DeployGear extends CommandBase {
	
	public DeployGear() {
		requires(gearmech);
	}
	
	@Override
	protected void initialize() {
		gearmech.deployGear();
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
