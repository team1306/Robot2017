package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @AngledTurn
 * 
 * 
 * @author Jackson Goth
 */
public class AngledTurn extends CommandBase {

	private double initAngle, angle;
	
	public AngledTurn(double a) {
		requires(drivetrain);
		angle = a;
	}
	
	@Override
	protected void initialize() {
		initAngle = drivetrain.gyro.getAngle();
	}

	@Override
	protected void execute() {
		
		double speed = (drivetrain.gyro.getAngle() - initAngle) / (angle/2);
		
		if(speed > 1) {
			speed -= Math.abs(drivetrain.gyro.getAngle() / (angle/2));
		} else if(speed < -1) {
			speed += Math.abs(drivetrain.gyro.getAngle() / (angle/2));
		} else if(speed < 0.15 || speed > 0.15) {
			
		}
		
		drivetrain.driveVBus(speed,-speed);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(drivetrain.gyro.getAngle() - (initAngle + angle)) < 0.5;
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
