package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Geartake extends Subsystem {
	
	private final DoubleSolenoid gearSolenoid;
	private final Timer timer;
	private Spark gearMotor;
	
	public Geartake() {
		gearSolenoid = new DoubleSolenoid(2,3);
		timer = new Timer();
		gearMotor = new Spark(RobotMap.GEAR_SPARK_PORT);
		
		reverseGear();
	}
	
	/**
	 * Sets the solenoid to true and pushes out the gear
	 */
	public void deployGear() {
		gearSolenoid.set(DoubleSolenoid.Value.kForward);
		//gearSolenoid.set(true);
		//timer.delay(1);
//		timer.reset();
//		timer.start();
//		while(!timer.hasPeriodPassed(Constants.GEAR_DEPLOY_TIME)) {
//		}
		//gearSolenoid.set(false);
	}
	
	/**
	 * Sets the solenoid to false and pulls gear back
	 */
	public void reverseGear() {
		gearSolenoid.set(DoubleSolenoid.Value.kReverse);
		//gearSolenoid.set(false);
		//timer.delay(1);
	}
	
	public void spinMotor(double speed) {
		gearMotor.set(speed);
	}
	
	public void stopAll() {
		gearMotor.set(Constants.SPEED_ZERO);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
