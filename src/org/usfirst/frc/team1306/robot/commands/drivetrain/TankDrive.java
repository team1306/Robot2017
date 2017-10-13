package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Axis;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.OI.Joystick;
import org.usfirst.frc.team1306.robot.OI.PullTrigger;
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
		if(OI.getTriggerVal(Controller.P, PullTrigger.L) >= Constants.DEADBAND || OI.getTriggerVal(Controller.P, PullTrigger.R) >= Constants.DEADBAND) {
			if(OI.getTriggerVal(Controller.P, PullTrigger.R) >= Constants.DEADBAND) {
				drivetrain.driveVBus(OI.getTriggerVal(Controller.P, PullTrigger.R), OI.getTriggerVal(Controller.P, PullTrigger.R));
			} else if(OI.getTriggerVal(Controller.P, PullTrigger.L) >= Constants.DEADBAND) {
				drivetrain.driveVBus(-OI.getTriggerVal(Controller.P, PullTrigger.L), -OI.getTriggerVal(Controller.P, PullTrigger.L));
			}
		} else {
			drivetrain.driveVBus(OI.getJoyVal(Controller.P, Joystick.L, Axis.Y), OI.getJoyVal(Controller.P, Joystick.R, Axis.Y));
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
