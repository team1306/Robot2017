package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.axis;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.joystick;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Allows the robot drivetrain to be controlled with tank drive
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
		* Basic drive commands called from here,
		* 
		* 	If trigger pressed it will figure out which one and either go forward or backward based on that
		* 	Otherwise it assumes it is being controlled by joysticks and will drive robot based on their respective inputs
		* 
		*/
		
		if(OI.getTriggerVal(controller.p, trigger.l) >= Constants.DEADBAND || OI.getTriggerVal(controller.p, trigger.r) >= Constants.DEADBAND) {
			if(OI.getTriggerVal(controller.p, trigger.r) >= Constants.DEADBAND) {
				drivetrain.tankDrive(OI.getTriggerVal(controller.p, trigger.r), OI.getTriggerVal(controller.p, trigger.r));
			} else if(OI.getTriggerVal(controller.p, trigger.l) >= Constants.DEADBAND) {
				drivetrain.tankDrive(-OI.getTriggerVal(controller.p, trigger.l), -OI.getTriggerVal(controller.p, trigger.l));
			}
		} else {
			drivetrain.tankDrive(oi.getJoyVal(controller.p, joystick.l, axis.y), oi.getJoyVal(controller.p, joystick.r, axis.y));
		}
		
		/**
		 * The following is driver input code for PID testing
		 */
		
//		if(oi.getTriggerVal(controller.p, trigger.l) >= Constants.TRIGGER_DEADBAND || oi.getTriggerVal(controller.p, trigger.r) >= Constants.TRIGGER_DEADBAND) {
//			if(oi.getTriggerVal(controller.p, trigger.r) >= Constants.TRIGGER_DEADBAND) {
//				drivetrain.drivePID(Constants.PID_SPEED);
//			} else if(oi.getTriggerVal(controller.p, trigger.l) >= Constants.TRIGGER_DEADBAND) {
//				drivetrain.drivePID(-Constants.PID_SPEED);
//			}
//		} else {
//			drivetrain.tankDrive(oi.getJoyVal(controller.p, joystick.l, axis.y), oi.getJoyVal(controller.p, joystick.r, axis.y));
//		}

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
