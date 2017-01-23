package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * Enum that contains different basic autonomous modes
 * @author Jackson Goth
 */
public enum Direction {

	FORWARD_SLOW(Constants.FORWARD_SLOW_SPEED, Constants.FORWARD_SLOW_TIME),
	FORWARD_FAST(Constants.FORWARD_FAST_SPEED, Constants.FORWARD_FAST_TIME),
	BACKWARD_SLOW(Constants.BACKWARD_SLOW_SPEED, Constants.BACKWARD_SLOW_TIME),
	BACKWARD_FAST(Constants.BACKWARD_FAST_SPEED, Constants.BACKWARD_FAST_TIME);
	
	private final double speed;
	private final double time;
	
	private Direction(double speed, double time) {
		this.speed = speed;
		this.time = time;
	}
	
	public TimedDrive getDriveCommand() {
		return new TimedDrive(speed, time);
	}
}
