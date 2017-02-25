package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * Enum that stores vision scanning directions; left and right.
 * @author Jackson Goth
 */
public enum ScanDirection {

	LEFT(Constants.TURRET_TURN_LEFT_SPEED,"Left"),RIGHT(Constants.TURRET_TURN_RIGHT_SPEED,"Right"),UNKNOWN(-0.2,"Unknown");
	
	private final double direction;
	//private final String name;
	
	private ScanDirection(double direction,String name) {
		this.direction = direction;
		//this.name = name;
	}
	
	public double getDir() {
		return direction;
	}
}
