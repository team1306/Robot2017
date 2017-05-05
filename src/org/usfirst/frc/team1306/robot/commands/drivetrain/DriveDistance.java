package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistance extends CommandBase {

	private double distance, time;
	Timer timer;
	
	public DriveDistance(double distance, double time) {
		requires(drivetrain);
		this.distance = distance;
		this.time = time;
		
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		
		SmartDashboard.putNumber("distance",distance);
		
		drivetrain.driveDistance(distance);
		
//		if(distance == 5) {
//			drivetrain.driveDistance(3);
//		} else if(distance == 4) {
//			drivetrain.driveDistance(4);
//		} else {
//			drivetrain.driveDistance(distance);
//		}
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		if(timer.hasPeriodPassed(time)) {
			drivetrain.resetEncoders();
			drivetrain.stopAll();
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {	
		
	}
}
