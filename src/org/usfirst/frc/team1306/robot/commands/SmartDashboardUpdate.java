package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Periodically updates the SmartDashboard with new information
 */
public class SmartDashboardUpdate extends CommandBase {

	public SmartDashboardUpdate() {
		requires(hood);
		setRunWhenDisabled(true);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		SmartDashboard.putString("Hood Position",hood.getPos());
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
