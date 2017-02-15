package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that turns turret to a given position
 * @author Sam Roquitte
 */
public class TurnTurret extends CommandBase {

	public TurnTurret() {
		requires(turret);
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins turret to correct position
     */
    protected void execute() {
    	turret.setPosition(turret.getPos() + 1);
    	//new Wait(1);
    }

    protected boolean isFinished() {
    	if(turret.getPos() == 40) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    protected void end() {
    	//turret.stopAll();
    }
    
    protected void interrupted() {
    	//end();
    }
}
