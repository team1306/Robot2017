package org.usfirst.frc.team1306.robot.commands.geartake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class RetractGeartake extends CommandBase {

	public RetractGeartake() {
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
