package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class leftStraight extends CommandBase {
	
	public leftStraight() {
		requires(leftdrive);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		if(OI.getButtonVal(controller.p,2)) {
			leftdrive.setSetpoint(6000);
			leftdrive.enable();
		} else {
			leftdrive.disable();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		leftdrive.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}
