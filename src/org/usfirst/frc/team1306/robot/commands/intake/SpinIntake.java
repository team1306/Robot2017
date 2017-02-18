package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Spins up the intake while a given button is pressed
 * @author Jackson Goth
 */
public class SpinIntake extends CommandBase {

	//private final Timer timer;
	//private static boolean running = true;
	
    public SpinIntake() {
        requires(intake);
        //timer = new Timer();
    }
    
    protected void initialize() {
    	
//    	if(running) {
//    		if(timer.hasPeriodPassed(0.5)) {
//    			running = false;
//    		} else {
//    			running = true;
//    		}
//    		
//    	} else {
//    		running = true;
//    	}
//    	
//    	timer.reset();
//    	timer.start();
    }
    
    /**
     * Continually spins intake
     */
    protected void execute() {
    	
    	intake.spinIntake();
    	
//    	if(running) {
//    		intake.spinIntake();
//    	}
    }

    /**
     * Stops spinning intake when the intake button (constant) is no longer pressed
     */
    protected boolean isFinished() {
    	
//    	if(running) {
//    		return false;
//    	} else {
//    		intake.stopAll();
//    		return true;
//    	}
    	
    	if(OI.getButtonVal(controller.s,Constants.INTAKE_BUTTON)) {
    		return false;
    	} else {
    		intake.stopAll();
    		return true;
    	}
    }

    protected void end() {
    	intake.stopAll();
    }

    protected void interrupted() {
    	end();
    }
}
