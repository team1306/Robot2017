package org.usfirst.frc.team1306.robot.commands.gearmech;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.XboxController;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class SpinGeartake extends CommandBase {

	private double speed;
	private double time;
	private Timer timer;
	
	public SpinGeartake(double speed, double time) {
		requires(gearmech);
		this.speed = speed;
		this.time = time;
		
		timer = new Timer();
	}
	
	public SpinGeartake(double speed) {
		requires(gearmech);
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		gearmech.spinMotor(speed);
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonVal(controller.s,Constants.GEARTAKE_BUTTON) || OI.getButtonVal(controller.s,XboxController.B)) {
    		return false;
    	} else {
    		gearmech.stopAll();
    		return true;
    	}
	}

	@Override
	protected void end() {
		gearmech.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
