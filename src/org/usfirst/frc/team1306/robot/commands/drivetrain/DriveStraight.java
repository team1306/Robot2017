package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
			SmartDashboard.putString("Made it to Execute", "Yes");
			piddrivetrain.setSetpoint(OI.getTriggerVal(controller.p, trigger.r));
		} else {
			SmartDashboard.putString("Isn't getting trigger Val", "yes");
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
