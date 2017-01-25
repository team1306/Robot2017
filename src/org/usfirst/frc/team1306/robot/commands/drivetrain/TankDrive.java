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
		 * Applies deadband to trigger values to allow both joystick and trigger driving
		 *  TODO Disabled for PID Testing
		 */
		if(oi.getTriggerVal(controller.p, trigger.l) >= Constants.TRIGGER_DEADBAND || oi.getTriggerVal(controller.p, trigger.r) >= Constants.TRIGGER_DEADBAND) {
			if(oi.getTriggerVal(controller.p, trigger.r) >= Constants.TRIGGER_DEADBAND) {
				drivetrain.tankDrive(oi.getTriggerVal(controller.p, trigger.r), oi.getTriggerVal(controller.p, trigger.r));
			} else if(oi.getTriggerVal(controller.p, trigger.l) >= Constants.TRIGGER_DEADBAND) {
				drivetrain.tankDrive(-oi.getTriggerVal(controller.p, trigger.l), -oi.getTriggerVal(controller.p, trigger.l));
			}
		} else {
			drivetrain.tankDrive(oi.getJoyVal(controller.p, joystick.l, axis.y), oi.getJoyVal(controller.p, joystick.r, axis.y));
		}
		
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