package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class MagicDrive extends CommandBase {

	private double distance;
	
	public MagicDrive(double distance) {
		requires(drivetrain);
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		drivetrain.resetGyro();
		drivetrain.driveDistance(distance);
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
		drivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
