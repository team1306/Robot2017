package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command used to adjust and aim turrets with a controller instead of vision
 * @author Jackson Goth
 */
public class Aim extends CommandBase {
	
	private double turnSpeed;
	
	public Aim(Direction direction) {
		requires(turret);
		this.turnSpeed = direction.getSpeed();
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if(turret.getEncPos() < Constants.TURRET_LEFT_LIMIT && turret.getEncPos() > Constants.TURRET_RIGHT_LIMIT) {
			turret.setSpeed(turnSpeed);
		}
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonVal(controller.s,Constants.AIM_LEFT_BUTTON)) {
    		return false;
    	} else {
    		turret.stopAll();
    		return true;
    	}
	}

	@Override
	protected void end() {
		turret.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
