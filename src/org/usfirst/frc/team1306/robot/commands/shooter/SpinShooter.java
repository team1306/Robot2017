package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that calls to spin shooter, stops when a button is no longer pressed
 * @author Jackson Goth
 *
 */
public class SpinShooter extends CommandBase{

	public SpinShooter() {
		requires(shooter);
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins up shooter
     */
    protected void execute() {

    	if(OI.getButtonVal(controller.p,Constants.SHOOTER_BUTTON)) {
    		shooter.spinShooter();
    	}
    }

    /**
     * Stops spinning shooter when A is no longer pressed
     */
    protected boolean isFinished() {
    	if(OI.getButtonVal(controller.p,Constants.SHOOTER_BUTTON)) {
    		return false;
    	} else {
    		shooter.stopAll();
    		return true;
    	}
    }
    
    protected void end() {
    	shooter.stopAll();
    }
    
    protected void interrupted() {
    	
    }
}
