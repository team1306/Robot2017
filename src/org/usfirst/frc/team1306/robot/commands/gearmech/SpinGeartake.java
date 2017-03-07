package org.usfirst.frc.team1306.robot.commands.gearmech;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class SpinGeartake extends CommandBase {

	public SpinGeartake() {
		requires(gearmech);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		gearmech.spinMotor();
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonVal(controller.s,Constants.GEARTAKE_BUTTON)) {
    		return false;
    	} else {
    		gearmech.stopAll();
    		return true;
    	}
	}

	@Override
	protected void end() {
		gearmech.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
