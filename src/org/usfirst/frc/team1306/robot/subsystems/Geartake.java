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
//	private Talon geartalon;
	
	public Geartake() {
		gearSolenoid = new DoubleSolenoid(1,2);
		timer = new Timer();
		gearMotor = new Spark(RobotMap.GEAR_SPARK_PORT);
//		geartalon = new Talon(RobotMap.GEAR_SPARK_PORT);
	}
	
	/**
	 * Sets the solenoid to true and pushes out the gear
	 */
	public void deployGear() {
		if(Constants.GEARTAKE_ENABLED) {
			gearSolenoid.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	/**
	 * Sets the solenoid to false and pulls gear back
	 */
	public void reverseGear() {
		if(Constants.GEARTAKE_ENABLED) {
			gearSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	public void spinMotor(double speed) {
		if(Constants.GEARTAKE_ENABLED) {
			gearMotor.set(speed);
		}
	}
	
	public void stopAll() {
		gearMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
