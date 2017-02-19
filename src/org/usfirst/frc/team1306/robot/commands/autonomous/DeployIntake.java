package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.XboxController;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class DeployIntake extends CommandBase {

	Timer timer;
	
	public DeployIntake() {
		requires(climber);
		timer = new Timer();
	}

	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		climber.spinClimberBack();
	}

	@Override
	protected boolean isFinished() {
    	if(timer.hasPeriodPassed(1)) {
    		return true;
    	} else {
    		return false;
    	}
	}

	@Override
	protected void end() {
		climber.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
