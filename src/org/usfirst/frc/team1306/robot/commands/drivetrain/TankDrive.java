package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.*;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class TankDrive extends CommandBase {
	
	public TankDrive() {
		requires(drivetrain);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		
		/**
		* Basic drive commands called from here,
		* 
		* 	If trigger pressed it will figure out which one and either go forward or backward based on that
		* 	Otherwise it assumes it is being controlled by joysticks and will drive robot based on their respective inputs
		* 
		*/
		if(OI.getTriggerVal(controller.p, trigger.l) >= Constants.TRIGGER_DEADBAND || OI.getTriggerVal(controller.p, trigger.r) >= Constants.TRIGGER_DEADBAND) {
			if(OI.getTriggerVal(controller.p, trigger.r) >= Constants.TRIGGER_DEADBAND) {
				drivetrain.tankDrive(OI.getTriggerVal(controller.p, trigger.r), OI.getTriggerVal(controller.p, trigger.r));
			} else if(OI.getTriggerVal(controller.p, trigger.l) >= Constants.TRIGGER_DEADBAND) {
				drivetrain.tankDrive(-OI.getTriggerVal(controller.p, trigger.l), -OI.getTriggerVal(controller.p, trigger.l));
			}
		} else {
			drivetrain.tankDrive(oi.getJoyVal(controller.p, joystick.l, axis.y), oi.getJoyVal(controller.p, joystick.r, axis.y));
		}
		
		//TODO This is used for PID testing when triggers are disabled
		//drivetrain.tankDrive(oi.getJoyVal(controller.p, joystick.r, axis.y), oi.getJoyVal(controller.p, joystick.l, axis.y));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}