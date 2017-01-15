package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Spins intake when x is pressed
 * @author Jackson Goth
 *
 */
public class spinIntake extends CommandBase {

    public spinIntake() {
        requires(intake);
    }

    private static boolean isFinished;
    
    protected void initialize() {
    	isFinished = false;
    }
    
    /*
     * Continually spins intake
     */
    protected void execute() {
    	intake.SpinIntake();
    }

    /**
     * Stops spinning intake when x is no longer pressed
     */
    protected boolean isFinished() {
    	
    	if(OI.getButtonVal(controller.s,3)) {
    		isFinished = false;
    	} else {
    		intake.stopMotor();
    		isFinished = true;
    	}
    	
    	return isFinished;
    }

    protected void end() {
    	intake.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
