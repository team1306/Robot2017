package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that spins the shooter with a bang bang loop
 * @author Sam Roquitte
 *
 */
public class BangSpinShooter extends CommandBase{

	
	public BangSpinShooter() {
		requires(shooter);
	}
	
	protected void initialize() {
    	
    }
    
    /*
     * Spins up shooter
     */
    protected void execute() {
    	if(OI.getButtonVal(controller.p,Constants.BANG_SHOOTER_BUTTON)) {
    		shooter.bangBangSpinShooter();
    		//shooter.spin50();
    	}
    }

    /**
     * Stops spinning shooter when A is no longer pressed
     */
    protected boolean isFinished() {
    	if(OI.getButtonVal(controller.p,Constants.BANG_SHOOTER_BUTTON)) {
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
