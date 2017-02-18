package org.usfirst.frc.team1306.robot.commands.climber;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.XboxController;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that runs the climber motor and stops when a button is pressed or reaches top
 * @author Jackson Goth
 */
public class ClimbBack extends CommandBase {
	
	public ClimbBack() {
		requires(climber);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		climber.spinClimberBack();
	}

	@Override
	protected boolean isFinished() {
    	if(OI.getButtonVal(controller.s,XboxController.BACK)) {
    		return false;
    	} else {
    		climber.stopAll();
    		return true;
    	}
	}

	@Override
	protected void end() {
		climber.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
