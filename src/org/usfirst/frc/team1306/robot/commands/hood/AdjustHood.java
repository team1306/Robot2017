package org.usfirst.frc.team1306.robot.commands.hood;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class AdjustHood extends CommandBase{

	private final int direction;
	private final String name;
	
	public AdjustHood(HoodAngle direction) {
		this.direction = direction.getDir();
		this.name = direction.getName();
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		hood.setPos(direction,name);
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
