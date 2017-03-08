package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.side;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Spins up the intake while a given button is pressed
 * @author Jackson Goth
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
    	
    	OI.setRumble(controller.s, side.l, 0.5);
    	OI.setRumble(controller.s, side.r, 0.5);
    	
    	intake.spinIntake();
    }

    /**
     * Stops spinning intake when the intake button (constant) is no longer pressed
     */
    protected boolean isFinished() {
    	
    	if(OI.getButtonVal(controller.s,Constants.INTAKE_BUTTON)) {
    		return false;
    	} else {
    		intake.stopAll();
    		return true;
    	}
    }

    protected void end() {
    	intake.stopAll();
    	OI.setRumble(controller.s, side.l, 0);
    	OI.setRumble(controller.s, side.r, 0);
    }

    protected void interrupted() {
    	end();
    }
}
