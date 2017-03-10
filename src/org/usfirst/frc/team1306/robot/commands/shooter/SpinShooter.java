package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.side;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Command that calls to spin shooter, stops when shooter button is no longer pressed
 * @author Jackson Goth and Sam Roquitte
 */
public class SpinShooter extends CommandBase {

	private double time;
	private boolean timedSpin = false;
	private final Timer timer;
	private final Timer hopperTimer;
	
	public SpinShooter(double time) {
		requires(shooter);
		requires(hopper);
		requires(intake);
		this.timedSpin = true;
		this.time = time;
		
		timer = new Timer();
		hopperTimer = new Timer();
	}
	
	public SpinShooter() {
		requires(shooter);
		requires(hopper);
		requires(intake);
		this.timedSpin = false;
		
		timer = new Timer();
		hopperTimer = new Timer();
	}
	
	protected void initialize() {
    	OI.setRumble(controller.p, side.l, 0.2);
    	
    	hopperTimer.reset();
    	hopperTimer.start();
    	
    	if(timedSpin) {
    		timer.reset();
    		timer.start();
    	}
    }
	
	/**
     * Spins up shooter
     */
    protected void execute() {
    	
    	//Starts ramping up shooters and indexers
    	shooter.spinShooter();
		shooter.spinIndexer();
		
		//This timer is to give the shooters and indexers enough time to get up to speed before shooting
    	if(hopperTimer.hasPeriodPassed(Constants.SHOOTER_SPIN_UP_TIME)) {
    		if (hopperTimer.hasPeriodPassed(Constants.SHOOTER_SPIN_UP_TIME+(0.5*Constants.HOPPER_RAMP_I)) && Constants.HOPPER_RAMP_I < 5) {
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
    	} else if(timedSpin && timer.hasPeriodPassed(time)) {
    		shooter.stopAll();
    		hopper.stopAll();
    		intake.stopAll();
    		return true;
    	} else {
    		shooter.stopAll();
    		hopper.stopAll();
    		intake.stopAll();
    		return true;
    	}
    }
    
    protected void end() {
    	OI.resetRumble(controller.p);
    	shooter.stopAll();
    	hopper.stopAll();
    	intake.stopAll();
    }
    
    protected void interrupted() {
    	end();
    }
}
