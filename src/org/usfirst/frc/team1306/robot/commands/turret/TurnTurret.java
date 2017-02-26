package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that turns turret to a given position
 * @author Sam Roquitte
 */
public class TurnTurret extends CommandBase {

	int position;
	
	public TurnTurret(int position) {
		requires(turret);
		this.position = position; //Desired position of turret (You can get this value from IE dashboard)
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins turret to correct position
     */
    protected void execute() {
    	if (turret.getEncPos() < position) {
    		turret.setSpeed(Constants.TURRET_TURN_LEFT_SPEED); //If Turret position is below desired position the turret needs to turn left
    	} else if(turret.getEncPos() > position) {
    		turret.setSpeed(Constants.TURRET_TURN_RIGHT_SPEED); //If Turret position is above desired position the turret needs to turn right
    	}
    }

    protected boolean isFinished() {
    	if(Math.abs(position - turret.getEncPos()) < Constants.TURRET_TURN_TOLERANCE) { //Stops turning turret if in tolerance of position
    		turret.stopAll();
    		return true;
    	} else if((turret.getEncPos() > Constants.TURRET_LEFT_LIMIT && turret.getEncPos() < position) || (turret.getEncPos() < Constants.TURRET_RIGHT_LIMIT && turret.getEncPos() > position)) {
    		turret.stopAll(); //Stops turning if turret is outside soft limits
    		return true;
    	} else { //Continues turning turret
    		return false;
    	}
    }
    
    protected void end() {
    	turret.stopAll();
    }
    
    protected void interrupted() {
    	end();
    }
}
