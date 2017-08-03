package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.axis;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.joystick;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Drives the robot in "tank-drive" mode with each trigger and joystick controlling it's respective half of the drivetrain.
 * @author Sam Roquitte and Jackson Goth
 */
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
		 * If trigger pressed it will figure out which one and either go forward or backward based on the values from them.
		 * Otherwise it assumes it is being controlled by joysticks and will drive robot based on their respective inputs.
		 */
		if(OI.getTriggerVal(controller.p, trigger.l) >= Constants.DEADBAND || OI.getTriggerVal(controller.p, trigger.r) >= Constants.DEADBAND) {
			if(OI.getTriggerVal(controller.p, trigger.r) >= Constants.DEADBAND) {
				drivetrain.driveVBus(OI.getTriggerVal(controller.p, trigger.r), OI.getTriggerVal(controller.p, trigger.r));
			} else if(OI.getTriggerVal(controller.p, trigger.l) >= Constants.DEADBAND) {
				drivetrain.driveVBus(-OI.getTriggerVal(controller.p, trigger.l), -OI.getTriggerVal(controller.p, trigger.l));
			}
		} else {
			drivetrain.driveVBus(OI.getJoyVal(controller.p, joystick.l, axis.y), OI.getJoyVal(controller.p, joystick.r, axis.y));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		
	}
}
