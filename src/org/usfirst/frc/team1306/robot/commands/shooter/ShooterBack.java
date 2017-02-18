package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class ShooterBack extends CommandBase {
	
	public ShooterBack() {
		requires(shooter);
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins up shooter
     */
    protected void execute() {
    	shooter.spinShooterBack();
    	shooter.spinIndexerBack();
    	hopper.spinHopperBack();
    }

    /**
     * Stops spinning shooter when shooter button is no longer pressed
     */
    protected boolean isFinished() {
    	if(OI.getButtonVal(controller.s,Constants.SHOOTER_BACK_BUTTON)) {
    		return false;
    	} else {
    		shooter.stopAll();
    		return true;
    	}
    }
    
    protected void end() {
    	shooter.stopAll();
    	hopper.stopAll();
    }
    
    protected void interrupted() {
    	
    }

}
