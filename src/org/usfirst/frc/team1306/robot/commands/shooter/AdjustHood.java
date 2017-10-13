package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Sets the hood angle Up or Down from a given angle
 * @author Jackson Goth
 */
public class AdjustHood extends CommandBase{

	public enum HoodAngle {UP,DOWN};
	
	private HoodAngle direction;
	
	public AdjustHood(HoodAngle d) {
		direction = d;
	}
	
	@Override
	protected void initialize() {
		shooter.setHoodAngle(direction);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
