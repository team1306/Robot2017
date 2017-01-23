package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.Constants;
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
    
    /**
     * Continually spins intake
     */
    protected void execute() {
    	intake.spinIntake(); //TODO Make it toggle on/off
    }

    /**
     * Stops spinning intake when the intake button (constant) is no longer pressed
     */
    protected boolean isFinished() {
    	if(OI.getButtonVal(controller.p,Constants.INTAKE_BUTTON)) {
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
    	
    }
}
