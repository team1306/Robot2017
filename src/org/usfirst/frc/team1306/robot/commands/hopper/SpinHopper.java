package org.usfirst.frc.team1306.robot.commands.hopper;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that calls to spin shooter, stops when a button is no longer pressed
 * @author Sam Roquitte
 */
public class SpinHopper extends CommandBase {

	public SpinHopper() {
		requires(hopper);
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins up hopper
     */
    protected void execute() {
    	hopper.spinHopper();
    }

    /**
     * Stops spinning shooter when A is no longer pressed
     */
    protected boolean isFinished() {
    	if(OI.getButtonVal(controller.p,Constants.HOPPER_BUTTON)) {
    		return false;
    	} else {
    		hopper.stopAll();
    		return true;
    	}
    }
    
    protected void end() {
    	hopper.stopAll();
    }
    
    protected void interrupted() {
    	
    }
}
