package org.usfirst.frc.team1306.robot.commands.turret;

public enum ScanDirection {

	LEFT(-0.2,"Left"),RIGHT(0.2,"Right");
	
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
