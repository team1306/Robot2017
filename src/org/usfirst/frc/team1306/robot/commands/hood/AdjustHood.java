package org.usfirst.frc.team1306.robot.commands.hood;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that adjusts hood to given position
 * @author Jackson Goth
 */
public class AdjustHood extends CommandBase{

	private final HoodAngle direction;
	
	public AdjustHood(HoodAngle direction) {
		this.direction = direction;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		hood.setPos(direction);
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
