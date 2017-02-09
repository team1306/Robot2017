package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class Scan extends CommandBase {

	private final double direction;
	
	public Scan(ScanDirection direction) {
		requires(turret);
		this.direction = direction.getDir();
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		turret.set(direction);
	}

	@Override
	protected boolean isFinished() {
		//TODO Vision goes here
		return false;
	}

	@Override
	protected void end() {
		turret.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
