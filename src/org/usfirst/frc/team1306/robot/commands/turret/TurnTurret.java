package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that turns turret to a given position
 * @author Sam Roquitte
 */
public class TurnTurret extends CommandBase {

	int position;
	
	public TurnTurret(int position) {
		requires(turret);
		this.position = position;
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins turret to correct position
     */
    protected void execute() {
    	if(turret.getEncPos() / 17.11 < position) {
    		turret.set(0.05);
    	} else if(turret.getEncPos() / 17.11 > position) {
    		turret.set(-0.05);
    	}
    }

    protected boolean isFinished() {
    	if(Math.abs(position - turret.getEncPos() / 17.11) < 17.11) {
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
