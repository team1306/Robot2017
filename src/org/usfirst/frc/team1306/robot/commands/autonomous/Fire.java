package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * Command that fires balls into the boiler during autonomous
 * @author Jackson Goth
 */
public class Fire extends CommandBase {

	private final Timer timer;
	private final double time = Constants.SHOOT_TIME;
	
	public Fire() {
		requires(shooter);
		requires(hopper);
		timer = new Timer();
	}

	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	/**
	 * Spins up shooter, indexers, and hopper
	 */
	@Override
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
	 * Stops shooting after a certain amount of time
	 */
	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}

	@Override
	protected void end() {
		hopper.stopAll();
		shooter.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}
