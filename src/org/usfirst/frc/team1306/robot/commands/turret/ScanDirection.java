package org.usfirst.frc.team1306.robot.commands.turret;

public enum ScanDirection {

	LEFT(0,"Left"),RIGHT(1,"Right");
	
	private final int direction;
	private final String name;
	
	private ScanDirection(int direction,String name) {
		this.direction = direction;
		this.name = name;
	}
}
