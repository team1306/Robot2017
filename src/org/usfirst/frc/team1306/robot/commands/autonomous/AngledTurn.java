package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class AngledTurn extends CommandBase {

private double eUnits;
	
	public AngledTurn(double degree) {
		requires(drivetrain);
		this.eUnits = degree; //TODO Find 1 Degree in Encoder Position Units and Convert Here
	}

	@Override
	protected void initialize() {
		drivetrain.resetEncoders();
	}

	@Override
	protected void execute() {
		drivetrain.tankDrive(-Constants.ROTATE_VEL,Constants.ROTATE_VEL);
	}

	@Override
	protected boolean isFinished() {
		if(drivetrain.getLeftPosition() > eUnits) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}
