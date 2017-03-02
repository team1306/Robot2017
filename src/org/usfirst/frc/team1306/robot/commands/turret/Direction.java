package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * Enum that stores directions and speeds that the turret can aim without vision scanning
 * @author Jackson Goth
 */
public enum Direction {

	LEFT(Constants.TURRET_TURN_LEFT_SPEED),RIGHT(Constants.TURRET_TURN_RIGHT_SPEED);
	
	private final double speed;
	
	private Direction(double direction) {
		this.speed = direction;
	}
	
	public double getSpeed() {
		return speed;
	}
}