package org.usfirst.frc.team1306.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem that controls hood
 * @author Jackson Goth
 */
public class Hood extends Subsystem {

	private static int pos = 0;
	private static String name = "Up";
	
	public Hood() {
		
	}
	
	public void setPos(int newPos, String newName) {
		pos = newPos;
		name = newName;
		//TODO Set Hood Position
	}
	
	public String getPos() {
		return name;
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
