package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

/**
 * Command that turns turret to a given rotations
 * @author Sam Roquitte
 */
public class TurnTurretPID extends CommandBase {
	
	public TurnTurretPID() {
		requires(turret);
	}
	
	protected void initialize() {
    }
    
    /**
     * Spins turret to correct rotations
     */
    protected void execute() {
    	turret.movePIDTune();
    }

    protected boolean isFinished() {
		if (OI.getButtonStatus(Controller.P,ControllerButton.Y)) {
			return false;
		} else {
			return true;
		}
    	//return true;
    }
    
    protected void end() {
    	turret.stopAll();
    }
    
    protected void interrupted() {
    	end();
    }
}
