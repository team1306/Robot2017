package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * 
 * Turns the robot left and right with one button
 * 
 * @author Jackson Goth
 *
 */

public class QuickTurn extends CommandBase {
	
	private final double speed;

	/**
	 * Makes the robot turn while LB or RB is pressed
	 * @param left
	 * 		if it should turn left or right. (T or F)
	 */
	
	public QuickTurn(boolean left) {
		requires(drivetrain);
		if(left == true) {
			speed = -Constants.TURN_SPEED;
		} else {
			speed = Constants.TURN_SPEED;
		}
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		drivetrain.tankDrive(speed, -speed);
	}

	/**
	 * Stops turning when RB or LB is released
	 */
	@Override
	protected boolean isFinished() {
		
		if(OI.getButtonVal(controller.p,5) || OI.getButtonVal(controller.p,6)) {
			return false;
		} else {
			drivetrain.stopAll();
			return true;
		}
	}

	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
