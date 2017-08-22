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

	private boolean toggled;
	
    public SpinIntake(boolean toggled) {
        requires(intake);
        this.toggled = toggled;
    }
    
    protected void initialize() {
    	OI.setRumble(controller.s, side.l, 0.5);
    	OI.setRumble(controller.s, side.r, 0.5);
    	OI.setRumble(controller.p, side.r, 0.2);
    }
    
    /**
     * Continually spins intake
     */
    protected void execute() {
    	intake.spinIntake();
    }

    /**
     * Stops spinning intake when the intake button (constant) is no longer pressed
     */
    protected boolean isFinished() {
    	if(toggled) {
    		return false;
    	} else if(OI.getButtonVal(controller.p,Constants.INTAKE_BUTTON)) {
    		return false;
    	} else {
    		return true;
    	}
    }

    protected void end() {
    	intake.stop();
    	OI.resetRumble(controller.p);
    	OI.resetRumble(controller.s);
    }

    protected void interrupted() {
    	end();
    }
}
