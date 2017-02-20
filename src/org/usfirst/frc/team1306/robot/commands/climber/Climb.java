package org.usfirst.frc.team1306.robot.commands.climber;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.XboxController;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that runs the climber motor and stops when the climber button is released
 * @author Jackson Goth
 */
public class Climb extends CommandBase {
	
	public Climb() {
		requires(climber);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		climber.spinClimber();
	}

	@Override
	protected boolean isFinished() {
    	if(OI.getButtonVal(controller.s,XboxController.START)) {
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
