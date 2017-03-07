package org.usfirst.frc.team1306.robot.commands.gearmech;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class ReverseGear extends CommandBase {

	public ReverseGear() {
		requires(gearmech);
	}
	
	@Override
	protected void initialize() {
		gearmech.reverseGear();
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
