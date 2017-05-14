package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.axis;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.joystick;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		
		if (OI.getTriggerVal(controller.p, trigger.r) >= Constants.DEADBAND || OI.getTriggerVal(controller.p, trigger.l) >= Constants.DEADBAND) {
			double triggerVal = OI.getTriggerVal(controller.p, trigger.r) - OI.getTriggerVal(controller.p, trigger.l);
			SmartDashboard.putNumber("Trigger Val", triggerVal);
			drivetrain.tankDrive(triggerVal+OI.getJoyVal(controller.p, joystick.l, axis.x), triggerVal-OI.getJoyVal(controller.p, joystick.l, axis.x));
		}
		else {
			if (OI.getJoyVal(controller.p, joystick.l, axis.x) != 0) {		//Turn right
				drivetrain.tankDrive(OI.getJoyVal(controller.p, joystick.l, axis.x), -OI.getJoyVal(controller.p, joystick.l, axis.x));
			}
			else {
				drivetrain.stopAll();
			}
		}
//		if (OI.getTriggerVal(controller.p, trigger.r) >= Constants.TRIGGER_DEADBAND) {		//Forward trigger pressed
//			drivetrain.tankDrive(OI.getTriggerVal(controller.p, trigger.r) - OI.getJoyVal(controller.p, joystick.l, axis.x), OI.getTriggerVal(controller.p, trigger.r) + OI.getJoyVal(controller.p, joystick.l, axis.x));
////			left+= (OI.getTriggerVal(controller.p, trigger.r) * 0.5) + (OI.getJoyVal(controller.p, joystick.l, axis.x) * 0.5);
////			right+=(OI.getTriggerVal(controller.p, trigger.r) * 0.5) + (OI.getJoyVal(controller.p, joystick.l, axis.x) * 0.5);
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
