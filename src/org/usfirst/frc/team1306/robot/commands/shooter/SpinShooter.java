package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that calls to spin shooter, stops when a button is no longer pressed
 * @author Jackson Goth
 *
 */
public class SpinShooter extends CommandBase{

	private static boolean on = false;
	
	public SpinShooter() {
		requires(shooter);
	}
	
	protected void initialize() {
    	
    }
    
    /*
     * Spins up shooter
     */
    protected void execute() {
    	
    	if(OI.getButtonVal(controller.p,1)) {
    		if(on) {
    			on = false;
    		} else {
    			on = true;
    		}
    	}
    	
    	if(on) {
    		shooter.spinShooter();
    	}
    }

    /**
     * Stops spinning shooter when A is no longer pressed
     */
    protected boolean isFinished() {
    	
    	/*if(OI.getButtonVal(controller.s,1)) {
    		return false;
    	} else {
    		shooter.stopAll();
    		return true;
    	}*/
    	return false;
    }

    protected void end() {
    	shooter.stopAll();
    }

    protected void interrupted() {
    	
    }

}
