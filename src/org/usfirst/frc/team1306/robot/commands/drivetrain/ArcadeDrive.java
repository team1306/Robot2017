package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Axis;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.OI.Joystick;
import org.usfirst.frc.team1306.robot.OI.PullTrigger;
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
		
		if (OI.getTriggerVal(Controller.P, PullTrigger.R) >= Constants.DEADBAND || OI.getTriggerVal(Controller.P, PullTrigger.L) >= Constants.DEADBAND) {
			double triggerVal = OI.getTriggerVal(Controller.P, PullTrigger.R) - OI.getTriggerVal(Controller.P, PullTrigger.L);
			drivetrain.driveVBus(triggerVal+OI.getJoyVal(Controller.P, Joystick.L, Axis.X), triggerVal-OI.getJoyVal(Controller.P, Joystick.L, Axis.X));
		} else {
			if (OI.getJoyVal(Controller.P, Joystick.L, Axis.X) >= Constants.DEADBAND || OI.getJoyVal(Controller.P, Joystick.L, Axis.X) <= -Constants.DEADBAND) {
				drivetrain.driveVBus(OI.getJoyVal(Controller.P, Joystick.L, Axis.X), -OI.getJoyVal(Controller.P, Joystick.L, Axis.X));
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
