package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * @FireFuel
 * 
 * Command that either spins the shooters for a specified time period or when toggled via secondary controller.
 * Waits for a small chuck of time for shooters and indexers to reach optimal speed before moving balls to them with hopper.
 * 
 * @author Jackson Goth
 */
public class FireFuel extends CommandBase {

	private Timer fireTimer; //Timer for determing if shooters have fired long enough (if not toggled)
	private Timer hopperTimer; //Timer for determing when to rev up hopper (once shooters/indexer reaches speed)
	private double fireTime; //How long to fire for (if not toggled)
	private boolean timed; //If firing for specified time (not toggled)
	
	public FireFuel() {
		timed = false;
		
		fireTimer = new Timer();
		hopperTimer = new Timer();
	}
	
	public FireFuel(double time) {
		fireTime = time;
		timed = true;
		
		fireTimer = new Timer();
		hopperTimer = new Timer();
	}
	
	@Override
	protected void initialize() {
		
		/* Restarting timers so all time checking is accurate */
		if(timed) {
			fireTimer.reset();
			fireTimer.start();
		}
		hopperTimer.reset();
		hopperTimer.start();
	}

	@Override
	protected void execute() {
		
		//Starts ramping up shooters and indexers
    	shooter.spinShooter();
		shooter.spinIndexer();
		
		//This timer is to give the shooters and indexers enough time to get up to speed before shooting
    	if(hopperTimer.hasPeriodPassed(Constants.SHOOTER_SPIN_UP_TIME)) {
			shooter.spinHopper();
			intake.spinIntake();
    	}
	}

	
	@Override
	protected boolean isFinished() {
		if(timed && fireTimer.hasPeriodPassed(fireTime)) {
			shooter.stop();
			intake.stop();
			return true;
		} else return false;
	}

	@Override
	protected void end() {
		shooter.stop();
		intake.stop();
	}

	@Override
	protected void interrupted() {
		end(); //Needs to end if un-toggled
	}
}
