package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that turns turret to a given rotations
 * @author Sam Roquitte and Jackson Goth
 */
public class TurnTurret extends CommandBase {

	double rotations;
	
	public TurnTurret(double rotations) {
		requires(turret);
		this.rotations = rotations;
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins turret to correct rotations
     */
    protected void execute() {
//    	if (turret.getEncPos() < rotations) {
//    		turret.setSpeed(Constants.TURRET_TURN_LEFT_SPEED); //If Turret rotations is below desired rotations the turret needs to turn left
//    	} else if(turret.getEncPos() > rotations) {
//    		turret.setSpeed(Constants.TURRET_TURN_RIGHT_SPEED); //If Turret rotations is above desired rotations the turret needs to turn right
//    	}
    	turret.moveRot(rotations);
    }

    protected boolean isFinished() {
//    	if(Math.abs(rotations - turret.getEncPos()) < Constants.TURRET_TURN_TOLERANCE) { //Stops turning turret if in tolerance of rotations
//    		turret.stopAll();
//    		return true;
//    	} else if((turret.getEncPos() > Constants.TURRET_LEFT_LIMIT && turret.getEncPos() < rotations) || (turret.getEncPos() < Constants.TURRET_RIGHT_LIMIT && turret.getEncPos() > rotations)) {
//    		turret.stopAll(); //Stops turning if turret is outside soft limits
//    		return true;
//    	} else { //Continues turning turret
//    		return false;
//    	}
    	
    	return true;
    }
    
    protected void end() {
    	turret.stopAll();
    }
    
    protected void interrupted() {
    	end();
    }
}
