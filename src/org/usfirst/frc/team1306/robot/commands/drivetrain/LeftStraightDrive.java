package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class LeftStraightDrive extends CommandBase {
	
	public LeftStraightDrive() {
		requires(leftdrive);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		leftdrive.setSetpoint(600);
		leftdrive.enable();
	}
	
	@Override
	protected boolean isFinished() {
		if(OI.getButtonVal(controller.p,2)) {
			return false;
		} else {
			leftdrive.stopAll();
			leftdrive.disable();
			return true;
		}
	}
	
	@Override
	protected void end() {
		leftdrive.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}
