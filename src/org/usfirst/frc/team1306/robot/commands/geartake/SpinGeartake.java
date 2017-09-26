package org.usfirst.frc.team1306.robot.commands.geartake;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class SpinGeartake extends CommandBase {

	private double speed;
	private double time;
	private boolean timedSpin = false;
	private Timer timer;
	
	public SpinGeartake(double speed, double time) {
		requires(gearmech);
		this.speed = speed;
		this.time = time;
		this.timedSpin = true;
		
		timer = new Timer();
	}
	
	public SpinGeartake(double speed) {
		requires(gearmech);
		this.speed = speed;
		this.timedSpin = false;
	}
	
	@Override
	protected void initialize() {
		if(timedSpin) {
			timer.reset();
			timer.start();
		}
	}

	@Override
	protected void execute() {
		gearmech.spinMotor(speed);
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonStatus(Controller.S,Constants.GEARTAKE_FORWARD_BUTTON) || OI.getButtonStatus(Controller.S,Constants.GEARTAKE_REVERSE_BUTTON)) {
    		return false;
    	} else if(timedSpin && timer.hasPeriodPassed(time)) {
    		gearmech.stopAll();
    		return true;
    	} else if(timedSpin){
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
