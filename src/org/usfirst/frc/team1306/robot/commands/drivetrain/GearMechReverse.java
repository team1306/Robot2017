package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearMechReverse extends CommandBase {

	public GearMechReverse() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		drivetrain.resetEncoders();
	}

	@Override
	protected void execute() {
		drivetrain.tankDrive(-0.5, -0.5);
		SmartDashboard.putNumber("Encoder Positions",drivetrain.getPosition());
	}
	
	@Override
	protected boolean isFinished() {
		if(drivetrain.getPosition() > -(256 * 2.55)) {
			return false;
		} else {
			return true;
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
