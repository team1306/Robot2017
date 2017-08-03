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

	/**
	 * Each time this runs it checks to see if either trigger is pulled past the deadband.
	 * It subtracts the inputs from the left side of controller from the right to find whether the driver wants to go forward or backward.
	 * Then it adds the x-axis of the left joystick to the trigger inputs which allow the fine adjustments needed for turning.
	 * If the joystick is being used without trigger input it will turn the robot it's place.
	 */
	@Override
	protected void execute() {
		
		if (OI.getTriggerVal(controller.p, trigger.r) >= Constants.DEADBAND || OI.getTriggerVal(controller.p, trigger.l) >= Constants.DEADBAND) {
			double triggerVal = OI.getTriggerVal(controller.p, trigger.r) - OI.getTriggerVal(controller.p, trigger.l);
			drivetrain.driveVBus(triggerVal+OI.getJoyVal(controller.p, joystick.l, axis.x), triggerVal-OI.getJoyVal(controller.p, joystick.l, axis.x));
		} else {
			if (OI.getJoyVal(controller.p, joystick.l, axis.x) >= Constants.DEADBAND || OI.getJoyVal(controller.p, joystick.l, axis.x) <= -Constants.DEADBAND) {
				drivetrain.driveVBus(OI.getJoyVal(controller.p, joystick.l, axis.x), -OI.getJoyVal(controller.p, joystick.l, axis.x));
			} else {
				drivetrain.stop();
			}
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
