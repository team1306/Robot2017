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
	private int hopperRampI = 1;
    protected void execute() {
    	OI.setRumble(controller.s, side.l, 1);
    	OI.setRumble(controller.s, side.r, 1);
    	OI.setRumble(controller.p, side.l, 1);
    	OI.setRumble(controller.p, side.r, 1);
    	//shooter.spinIndexer();
    	shooter.spinShooter();
		shooter.spinIndexer();
    	if(timer.hasPeriodPassed(Constants.SHOOTER_SPIN_UP_TIME)) {
    		if (timer.hasPeriodPassed(Constants.SHOOTER_SPIN_UP_TIME+(0.5*hopperRampI)) && hopperRampI < 5) {
    			hopper.spinHopper(hopperRampI);
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
    	OI.setRumble(controller.s, side.l, 0);
    	OI.setRumble(controller.s, side.r, 0);
    	OI.setRumble(controller.p, side.l, 0);
    	OI.setRumble(controller.p, side.r, 0);
    }
    
    protected void interrupted() {
    	end();
    }
}
