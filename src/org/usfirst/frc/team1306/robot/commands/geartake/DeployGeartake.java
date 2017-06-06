package org.usfirst.frc.team1306.robot.commands.geartake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * This command pushes down the geartake using the pneumatic cylinders.
 * @author Jackson Goth
 */
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
