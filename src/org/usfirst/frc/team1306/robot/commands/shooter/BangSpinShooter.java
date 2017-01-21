package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	if(OI.getButtonVal(controller.p,4)) {
    		shooter.bangBangSpinShooter();
    	}
    }

    /**
     * Stops spinning shooter when A is no longer pressed
     */
    protected boolean isFinished() {
    	if(OI.getButtonVal(controller.p,4)) {
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
