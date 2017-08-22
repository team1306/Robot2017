package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.OI.Side;
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
    	OI.setRumble(Controller.S, Side.L, 0.5);
    	OI.setRumble(Controller.S, Side.R, 0.5);
    	OI.setRumble(Controller.P, Side.R, 0.2);
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
    	} else if(OI.getButtonStatus(Controller.P,Constants.INTAKE_BUTTON)) {
    		return false;
    	} else {
    		return true;
    	}
    }

    protected void end() {
    	intake.stop();
    	OI.resetRumble(Controller.S,Side.L);
    	OI.resetRumble(Controller.S,Side.R);
    	OI.resetRumble(Controller.P,Side.R);
    }

    protected void interrupted() {
    	end();
    }
}
