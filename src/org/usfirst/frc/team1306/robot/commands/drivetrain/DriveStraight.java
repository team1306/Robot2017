package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends CommandBase {
	
	private static String side;
	
	public DriveStraight(String input) {
		requires(rightdrive);
		requires(leftdrive);
		side = input;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if(OI.getTriggerVal(controller.p, trigger.r) >= 0.1) {
			SmartDashboard.putString("Made it to Execute", "Yes");
			if(side.equals("left")) {
				leftdrive.setSetpoint(0.5);
				leftdrive.enable();
			} else {
				rightdrive.setSetpoint(0.5);
				rightdrive.enable();
			}
			
		} else {
			SmartDashboard.putString("Isn't getting trigger Val", "yes");
			//piddrivetrain.setSetpoint(OI.getTriggerVal(controller.p, trigger.r));
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		if(side.equals("left")) {
			leftdrive.stopAll();
		} else {
			rightdrive.stopAll();
		}

	}

	@Override
	protected void interrupted() {
		
	}
}
