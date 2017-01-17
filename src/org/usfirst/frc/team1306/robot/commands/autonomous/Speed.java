package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;

public enum Speed {

	VELOCITY_TWO(Constants.SPEED_TWO,Constants.PID_TIME),
	VELOCITY_FOUR(Constants.SPEED_FOUR,Constants.PID_TIME),
	VELOCITY_SIX(Constants.SPEED_SIX,Constants.PID_TIME);
	
	private final double speed;
	private final double time;
	
	private Speed(double speed, double time) {
		this.speed = speed;
		this.time = time;
	}
	
	public TimedDrive getDriveCommand() {
		return new TimedDrive(speed, time);
	}
}
