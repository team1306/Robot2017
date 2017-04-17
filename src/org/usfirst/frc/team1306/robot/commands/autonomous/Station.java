package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * Enum that contains each robot position and their associated autonomous constants.
 * @author Jackson Goth
 */
public enum Station {
	
	RED_ONE(Constants.GEAR_LOADINGSIDE_INIT_SPEED,Constants.GEAR_LOADINGSIDE_INIT_TIME,Constants.GEAR_LOADINGSIDE_END_SPEED,Constants.GEAR_LOADINGSIDE_END_TIME), //Red - Far from boiler
	RED_TWO(Constants.GEAR_CENTER_SPEED,Constants.GEAR_CENTER_TIME,0,0), //Red - Center position
	RED_THREE(Constants.GEAR_BOILERSIDE_INIT_SPEED,Constants.GEAR_BOILERSIDE_INIT_TIME,Constants.GEAR_BOILERSIDE_END_SPEED,Constants.GEAR_BOILERSIDE_END_TIME), //Red - Close to boiler
	BLUE_ONE(Constants.GEAR_BOILERSIDE_INIT_SPEED,Constants.GEAR_BOILERSIDE_INIT_TIME,Constants.GEAR_BOILERSIDE_END_SPEED,Constants.GEAR_BOILERSIDE_END_TIME), //Blue - Close to boiler
	BLUE_TWO(Constants.GEAR_CENTER_SPEED,Constants.GEAR_CENTER_TIME,0,0), //Blue - Center position
	BLUE_THREE(Constants.GEAR_LOADINGSIDE_INIT_SPEED,Constants.GEAR_LOADINGSIDE_INIT_TIME,Constants.GEAR_LOADINGSIDE_END_SPEED,Constants.GEAR_LOADINGSIDE_END_TIME); //Blue - Far from boiler
	
	private final double initSpeed;
	private final double initTime;
	private final double endSpeed;
	private final double endTime;
	
	private Station(double initSpeed, double initTime, double endSpeed, double endTime) {
		this.initSpeed = initSpeed; //Initial auto speed
		this.initTime = initTime; //Initial auto time
		this.endSpeed = endSpeed; //End auto speed
		this.endTime = endTime; //End auto time
	}
	
	/**
	 * Method that returns a throttle based on where the robot is in it's autonomous routine.
	 * @param runNext
	 * 		If the robot is running it's second TimedDrive
	 * @return
	 * 		The throttle at the required point in the autonomous routine.
	 */
	public double getThrottle(boolean runNext) {
		if(runNext) {
			return endSpeed;
		} else {
			return initSpeed;
		}
	}
	
	/**
	 * Method that returns a time based on where the robot is in it's autonomous routine.
	 * @param runNext
	 * 		If the robot is running it's second TimedDrive
	 * @return
	 * 		The time at the required point in the autonomous routine.
	 */
	public double getTime(boolean runNext) {
		if(runNext) {
			return endTime;
		} else {
			return initTime;
		}
	}
}
