package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command used to debug shooter and spins all shooter-related motors in reverse
 * @author Sam Roquitte
 */
public class SpinShooterBack extends CommandBase {
	
	public SpinShooterBack() {
		requires(shooter);
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins both shooters, hopper, and indexers backwards
     */
    protected void execute() {
    	shooter.spinShooterBack();
    	shooter.spinIndexerBack();
    	hopper.spinHopperBack();
    }

    /**
     * Stops spinning shooters, hopper, and indexers backwards
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
