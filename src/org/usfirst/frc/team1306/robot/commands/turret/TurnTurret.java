package org.usfirst.frc.team1306.robot.commands.turret;

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
		this.position = position;
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins turret to correct position
     */
    protected void execute() {
    	if (turret.getEncPos() < position) {
    		turret.setSpeed(0.1);
    	}
    	else {
    		turret.setSpeed(-0.1);
    	}
    }

    protected boolean isFinished() {
    	SmartDashboard.putNumber("Turret Pos: ", position);
    	SmartDashboard.putNumber("Turret EncPos: ", turret.getEncPos());
    	SmartDashboard.putNumber("Turret Pos-EncPos", Math.abs(position - turret.getEncPos()));
    	if(Math.abs(position - turret.getEncPos()) < 50 || turret.getEncPos() > 2800 || turret.getEncPos() < 1150) {
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
