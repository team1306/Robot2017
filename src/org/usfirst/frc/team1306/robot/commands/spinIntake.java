package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1306.robot.OI.*;

/**
 *
 */
public class spinIntake extends CommandBase {

    public spinIntake() {
        requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.SpinIntake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if(secondaryController.getRawButton())
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
