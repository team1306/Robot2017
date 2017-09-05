package org.usfirst.frc.team1306.robot.commands.autonomous;

/**
 * @PathParams
 * 
 * @author Jackson Goth
 */
public class PathParams {

	public double time, timeInterval, robotTrackWidth;
	
	public PathParams(double time, double timeInterval, double robotTrackWidth) {
		this.time = time;
		this.timeInterval = timeInterval;
		this.robotTrackWidth = robotTrackWidth;
	}
}
