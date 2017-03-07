package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command used to for manual turret control
 * @author Sam Roquitte
 */
public class DPadTurret extends CommandBase {
	
	public DPadTurret() {
		requires(turret);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {	//TODO finish this
		int dpadVal = OI.getDPad(controller.s);
//		int dpadval = 0;
//		int step = (int) (4/Constants.MANUAL_TURRET_ROT);
//		if (OI.getDPad(controller.s) <= 3) {
//			dpadval = -OI.getDPad(controller.s);
//		}
//		else {
//			dpadval = 3-OI.getDPad(controller.s);
//		}
//		turret.moveRot(dpadval*step);
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonVal(controller.s,Constants.DPAD_TURRET_BUTTON)) {
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
