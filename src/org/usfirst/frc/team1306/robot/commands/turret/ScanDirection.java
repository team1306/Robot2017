package org.usfirst.frc.team1306.robot.commands.turret;

/**
 * Enum that stores vision scanning directions; left and right.
 * @author Jackson Goth
 */
public enum ScanDirection {

	LEFT(-0.6,"Left"),RIGHT(0.6,"Right"),UNKNOWN(-0.2,"Unknown");
	
	private final double direction;
	private final String name;
	
	private ScanDirection(double direction,String name) {
		this.direction = direction;
		this.name = name;
	}
	
	public double getDir() {
		return direction;
	}
}
