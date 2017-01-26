package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * Enum that contains commands for differen't alliance stations
 * @author Jackson Goth
 */
public enum Station {
	
	RED_ONE(Constants.RED_ONE_TIME_INITIAL,Constants.RED_ONE_ANGLE,Constants.RED_ONE_TIME_FINAL),
	RED_TWO(Constants.RED_TWO_TIME_INITIAL,Constants.RED_TWO_ANGLE,Constants.RED_TWO_TIME_FINAL),
	RED_THREE(Constants.RED_THREE_TIME_INITIAL,Constants.RED_THREE_ANGLE,Constants.RED_THREE_TIME_FINAL),
	BLUE_ONE(Constants.BLUE_ONE_TIME_INITIAL,Constants.BLUE_ONE_ANGLE,Constants.BLUE_ONE_TIME_FINAL),
	BLUE_TWO(Constants.BLUE_TWO_TIME_INITIAL,Constants.BLUE_TWO_ANGLE,Constants.BLUE_TWO_TIME_FINAL),
	BLUE_THREE(Constants.BLUE_THREE_TIME_INITIAL,Constants.BLUE_THREE_ANGLE,Constants.BLUE_THREE_TIME_FINAL);
		
	private final double initial_time;
	private final double final_time;
	private final double angle;
	private final double speed = Constants.AUTO_SPEED;
	
	private Station(double initial_time, double angle, double final_time) {
		this.initial_time = initial_time;
		this.final_time = final_time;
		this.angle = angle;
	}
	
	public TimedDrive getDriveCommand(String tracker) {
		
		if(tracker.equals("initial")) {
			return new TimedDrive(speed,initial_time);
		} else {
			return new TimedDrive(speed,final_time);
		}
		
	}
	
	public AngledTurn getTurnCommand() {
		
		return new AngledTurn(angle);
	}
}
