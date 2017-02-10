package org.usfirst.frc.team1306.robot.commands.climber;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that runs the climber motor and stops when a button is pressed or reaches top
 * @author Jackson Goth
 */
public class Climb extends CommandBase {
	
	public Climb() {
		requires(climber);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		climber.spinClimber();
	}

	@Override
	protected boolean isFinished() {
		
		return false;
	}

	@Override
	protected void end() {
		climber.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
