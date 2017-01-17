package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Spins up the intake while a given button is pressed
 * @author Jackson Goth
 *
 */
public class SpinIntake extends CommandBase {

    public SpinIntake() {
        requires(intake);
    }
    
    protected void initialize() {
    	
    }
    
    /*
     * Continually spins intake
     */
    protected void execute() {
    	intake.spinIntake(); //TODO Make it toggle on/off
    }

    /**
     * Stops spinning intake when X is no longer pressed
     */
    protected boolean isFinished() {
    	
    	if(OI.getButtonVal(controller.s,3)) {
    		return false;
    	} else {
    		intake.stopAll();
    		return true;
    	}

    }

    protected void end() {
    	intake.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
