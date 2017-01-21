package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that calls to spin shooter, stops when a button is no longer pressed
 * @author Jackson Goth
 *
 */
public class SpinShooter extends CommandBase{

	public SpinShooter() {
		requires(shooter);
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins up shooter
     */
    protected void execute() {
<<<<<<< HEAD
    	if(OI.getButtonVal(controller.p,1)) {
=======
    	
>>>>>>> origin/master
    		shooter.spinShooter();
    }

    /**
     * Stops spinning shooter when A is no longer pressed
     */
    protected boolean isFinished() {
<<<<<<< HEAD
=======
    	
>>>>>>> origin/master
    	if(OI.getButtonVal(controller.p,1)) {
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
