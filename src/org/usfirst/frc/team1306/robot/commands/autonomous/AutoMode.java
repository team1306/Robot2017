package org.usfirst.frc.team1306.robot.commands.autonomous;

/**
 * Enum that contains different autonomous routines
 * @author Jackson Goth
 */
public enum AutoMode {
	
	HOPPER_GEAR(0), //Places a gear, and then if close to the boiler it will empty the nearest hopper into it
	GEAR(1), //Will place a gear in all six positions
	TEN_KPA(2), //Will only shoot the 10 balls
	BLANK(3); //Will do nothing
	
	private final int routine;
	
	private AutoMode(int routine) {
		this.routine = routine;
	}
	
	public int getRoutine() {
		return routine;
	}
}
