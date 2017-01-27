package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class AngledTurn extends CommandBase {

	private final double angle;
	private final double speed = Constants.AUTO_SPEED;
	
	public AngledTurn(double angle) {
		requires(drivetrain);
		this.angle = angle;
	}
	
	@Override
	protected void initialize() {
		// Set Encoder Values to 0?
	}

	@Override
	protected void execute() {
		
		drivetrain.tankDrive(speed,speed);
	}
	
	@Override
	protected boolean isFinished() {
		// Encoder values pass certain threshold?
		return false;
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
