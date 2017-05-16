package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * Command that takes the distance in feet the robot should travel and the allowed time-frame in which it must do it.
 * @author Jackson Goth
 */
public class DriveDistance extends CommandBase { //TODO Make constructor without time-frame

	private double distance, time;
	private Timer timer;
	
	/**
	 * Sets up the command with the desired distance/allowed time and initializes the timer.
	 * @param distance
	 * 		Distance the robot should travel in feet
	 * @param time
	 * 		Time before command will end
	 */
	public DriveDistance(double distance, double time) {
		requires(drivetrain);
		this.distance = distance;
		this.time = time;
		
		timer = new Timer();
	}
	
	/**
	 * This resets the timer, starts the timer, and tells the drivetrain how far to drive.
	 */
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	
		drivetrain.driveDistance(distance);
	}

	@Override
	protected void execute() {
		
	}

	/**
	 * This checks to see if the allowed time has passed and if so resets the encoders and stops the drivetrain.
	 */
	@Override
	protected boolean isFinished() {
		if(timer.hasPeriodPassed(time)) {
			drivetrain.resetEncoders();
			drivetrain.stopAll();
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {	
		
	}
}
