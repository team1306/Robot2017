package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.axis;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.joystick;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Drives the robot with arcade drive (rocket league controls)
 * @author Sam Roquitte
 */
public class ArcadeDrive extends CommandBase {
	
	public ArcadeDrive() {
		requires(drivetrain);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		if (OI.getTriggerVal(controller.p, trigger.r) >= Constants.TRIGGER_DEADBAND) {		//Forward trigger pressed
			if (OI.getTriggerVal(controller.p, trigger.r) + OI.getTriggerVal(controller.p, trigger.r) <= Constants.TRIGGER_DEADBAND*2) {
				drivetrain.tankDrive(0,0);
			}
			else {
				drivetrain.tankDrive(OI.getTriggerVal(controller.p, trigger.r) - OI.getJoyVal(controller.p, joystick.l, axis.x), OI.getTriggerVal(controller.p, trigger.r) + OI.getJoyVal(controller.p, joystick.l, axis.x));
			}
//			left+= (OI.getTriggerVal(controller.p, trigger.r) * 0.5) + (OI.getJoyVal(controller.p, joystick.l, axis.x) * 0.5);
//			right+=(OI.getTriggerVal(controller.p, trigger.r) * 0.5) + (OI.getJoyVal(controller.p, joystick.l, axis.x) * 0.5);
		}
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
