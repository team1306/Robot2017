package org.usfirst.frc.team1306.robot.commands.hood;

public enum HoodAngle {

	UP(0,"Up"),DOWN(1,"Down");
	
	private final int direction;
	private final String name;
	
	private HoodAngle(int direction, String name) {
		this.direction = direction;
		this.name = name;
	}
	
	public int getDir() {
		return direction;
	}
}
