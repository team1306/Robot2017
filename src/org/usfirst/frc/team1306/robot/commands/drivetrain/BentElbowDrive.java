package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.axis;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.joystick;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class BentElbowDrive extends CommandBase {

	public BentElbowDrive() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		double triggerVal = OI.getTriggerVal(controller.p, trigger.r);
		if(OI.getTriggerVal(controller.p, trigger.r) >= Constants.TRIGGER_DEADBAND) {
			drivetrain.tankDrive(OI.getJoyVal(controller.p, joystick.l, axis.y)*triggerVal,OI.getJoyVal(controller.p,joystick.r,axis.y)*triggerVal);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
