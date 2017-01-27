package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class RightStraightDrive extends CommandBase {
	
	public RightStraightDrive() {
		requires(rightdrive);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		rightdrive.setSetpoint(600);
		rightdrive.enable();
	}
	
	@Override
	protected boolean isFinished() {
		if(OI.getButtonVal(controller.p,2)) {
			return false;
		} else {
			rightdrive.stopAll();
			rightdrive.disable();
			return true;
		}
	}
	
	@Override
	protected void end() {
		rightdrive.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}
