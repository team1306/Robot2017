package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Command that calls to spin shooter, stops when shooter button is no longer pressed
 * @author Jackson Goth and Sam Roquitte
 */
public class SpinShooter extends CommandBase{

	private final Timer timer;
	
	public SpinShooter() {
		requires(shooter);
		requires(hopper);
		requires(intake);
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

    	//Starts ramping up shooters and indexers
    	shooter.spinShooter();
		shooter.spinIndexer();
		
		//This timer is to give the shooters and indexers enough time to get up to speed before shooting
    	if(timer.hasPeriodPassed(Constants.SHOOTER_SPIN_UP_TIME)) {
    		if (timer.hasPeriodPassed(Constants.SHOOTER_SPIN_UP_TIME+(0.5*Constants.HOPPER_RAMP_I)) && Constants.HOPPER_RAMP_I < 5) {
    			hopper.spinHopper(Constants.HOPPER_RAMP_I);
    			intake.spinIntake();
    		}
    		else {
    			hopper.spinHopper();
    			intake.spinIntake();
    		}
    	}
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
    	intake.stopAll();
    }
    
    protected void interrupted() {
    	end();
    }
}
