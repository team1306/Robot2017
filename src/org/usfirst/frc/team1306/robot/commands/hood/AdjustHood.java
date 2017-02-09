package org.usfirst.frc.team1306.robot.commands.hood;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class AdjustHood extends CommandBase{

	private final int direction;
	
	public AdjustHood(HoodAngle direction) {
		this.direction = direction.getDir();
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if(direction == 0) {
			
		} else {
			
		}
	}

	@Override
	protected boolean isFinished() {
		
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
