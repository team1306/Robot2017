package org.usfirst.frc.team1306.robot.commands.climber;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

/**
 * Command that runs the climber motor backwards and stops when the buttoon is released
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
    	if(OI.getButtonStatus(Controller.S,ControllerButton.BACK)) {
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
