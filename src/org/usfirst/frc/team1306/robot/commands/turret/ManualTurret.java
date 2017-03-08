package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.axis;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.joystick;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command used to for manual turret control
 * @author Sam Roquitte
 */
public class ManualTurret extends CommandBase {
	
	public ManualTurret() {
		requires(turret);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		turret.moveRot(-(OI.getJoyVal(controller.s, joystick.r, axis.x) * Constants.MANUAL_TURRET_ROT));
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonVal(controller.s,Constants.MANUAL_TURRET_BUTTON)) {
    		return false;
    	} else {
    		return true;
    	}
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		end();
	}
}
