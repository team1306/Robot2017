package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class rightStraight extends CommandBase {
	
	public rightStraight() {
		requires(rightdrive);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		SmartDashboard.putBoolean("bbutton",OI.getButtonVal(controller.p,2));
		if(OI.getButtonVal(controller.p,2)) {
			rightdrive.setSetpoint(6000);
			rightdrive.enable();
		} else {
			rightdrive.disable();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		rightdrive.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}
