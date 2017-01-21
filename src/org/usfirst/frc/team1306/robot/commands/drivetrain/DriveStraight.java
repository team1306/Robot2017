package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class DriveStraight extends CommandBase {
	
	public DriveStraight() {
		requires(piddrivetrain);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if(OI.getTriggerVal(controller.p, trigger.r) >= 0.1) {
			piddrivetrain.setSetpoint(OI.getTriggerVal(controller.p, trigger.r));
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		piddrivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}
