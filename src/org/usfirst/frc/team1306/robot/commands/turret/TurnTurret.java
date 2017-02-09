package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that calls to spin shooter, stops when a button is no longer pressed
 * @author Sam Roquitte
 */
public class TurnTurret extends CommandBase {

	public TurnTurret() {
		requires(turret);
	}
	
	protected void initialize() {
    	
    }
    
    /**
     * Spins up hopper
     */
    protected void execute() {
    	SmartDashboard.putNumber("Turret", 1);
    	turret.setPosition(360);
    }

    /**
     * Stops spinning shooter when A is no longer pressed
     */
    protected boolean isFinished() {
    	return false;
    }
    
    protected void end() {
    	turret.stopAll();
    }
    
    protected void interrupted() {
    	
    }
}
