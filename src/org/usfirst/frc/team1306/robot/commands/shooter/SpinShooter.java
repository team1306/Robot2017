package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Command that calls to spin shooter, stops when shooter button is no longer pressed
 * @author Jackson Goth
 */
public class SpinShooter extends CommandBase{

	private final Timer timer;
	
	public SpinShooter() {
		requires(shooter);
		timer = new Timer();
	}
	
	protected void initialize() {
    	timer.reset();
    	timer.start();
    }
    
    /**
     * Spins up shooter
     */
    protected void execute() {
    	shooter.spinShooter();
    	shooter.spinIndexer();
    	hopper.spinHopper();
//    	if(timer.hasPeriodPassed(1)) {
//    		hopper.spinHopper();
//    	} else {
//    		hopper.spinHopperBack();
//    	}
    }

    /**
     * Stops spinning shooter when shooter button is no longer pressed
     */
    protected boolean isFinished() {
    	if(OI.getButtonVal(controller.s,Constants.SHOOTER_BUTTON)) {
    		return false;
    	} else {
    		shooter.stopAll();
    		hopper.stopAll();
    		return true;
    	}
    }
    
    protected void end() {
    	shooter.stopAll();
    	hopper.stopAll();
    }
    
    protected void interrupted() {
    	end();
    }
}
