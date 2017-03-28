package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.dpaddirection;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command used to for manual turret control with dpad
 * @author Sam Roquitte
 */
public class DPadTurret extends CommandBase {
	
	public DPadTurret() {
		requires(turret);
	}
	
	@Override
	protected void initialize() {
		turret.moveRot(0);
	}

	@Override
	protected void execute() {
//		dpaddirection dpadDirection = OI.getDPad(controller.s);
//		double currentRot = 0;
//		switch (dpadDirection) {
//			case u:		//Dpad up
//				//hood up
//			break;
//			case ur:	//Dpad upper right
//				
//			break;
//			case r:		//Dpad right
//				turret.moveRot(currentRot+Constants.DPAD_TURRET_STEP);
//			break;
//			case dr:	//Dpad down right
//				
//			break;
//			case d:		//Dpad down
//				//hood down
//			break;
//			case dl:	//Dpad down left
//				
//			break;
//			case l:		//Dpad left
//				turret.moveRot(currentRot-Constants.DPAD_TURRET_STEP);
//			break;
//			case ul:	//Dpad uppper left
//				
//			break;
//			case no:
//				
//			break;
//		}
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
//		if(OI.getButtonVal(controller.s,Constants.DPAD_TURRET_BUTTON)) {
//    		return false;
//    	} else {
//    		return true;
//    	}
		return false;
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
