package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	SmartDashboard.putNumber("Turret", 1);
    	turret.setPosition(360);
    }

    protected boolean isFinished() {
    	if(turret.getPos() == 360) {
    		return true;
    	} else {
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
