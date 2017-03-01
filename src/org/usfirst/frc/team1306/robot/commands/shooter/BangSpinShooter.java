package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.turret.ResetTurret;

import edu.wpi.first.wpilibj.Timer;

/**
 * Command that spins the shooter with a bang bang loop
 * @author Sam Roquitte
 */
public class BangSpinShooter extends CommandBase{

	private final Timer timer;
	
	public BangSpinShooter() {
		requires(shooter);
		timer = new Timer();
	}
	
	protected void initialize() {
    	timer.reset();
    	timer.start();
    }
    
    /**
     * Spins up shooter while the bang shooter button is pressed
     */
    protected void execute() {
    	
    	shooter.bangBangSpinShooter();
    	if(timer.hasPeriodPassed(1)) {
    		hopper.spinHopper();
    		shooter.spinIndexer();
    	}
    }

    /**
     * Stops spinning shooter when bang spin shooter is pressed
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
    	hopper.stopAll();
    }

    protected void interrupted() {
    	
    }
}
