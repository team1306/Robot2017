package org.usfirst.frc.team1306.robot.commands.gearmech;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class DeployGeartake extends CommandBase {
	
	public DeployGeartake() {
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
