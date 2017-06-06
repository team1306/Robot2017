package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is the geartake subsystem that controls the two pneumatic cylinders actuating the 
 * mechanism and the motor that moves gears.
 * @author Jackson Goth
 */
public class Geartake extends Subsystem {
	
	private final DoubleSolenoid gearSolenoid;
	private Spark gearMotor;
	
	public Geartake() {
		gearSolenoid = new DoubleSolenoid(1,2);
		gearMotor = new Spark(RobotMap.GEAR_SPARK_PORT);
	}
	
	/**
	 * Sets the solenoid on, so the pneumatic cylinders push the geartake to the ground.
	 */
	public void deployGear() {
		if(Constants.GEARTAKE_ENABLED) {
			gearSolenoid.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	/**
	 * Sets the solenoid off, so the pneumatic cylinders pull the geartake back into the robot.
	 */
	public void reverseGear() {
		if(Constants.GEARTAKE_ENABLED) {
			gearSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	/**
	 * Spins the geartake motor at a given speed.
	 */
	public void spinMotor(double speed) {
		if(Constants.GEARTAKE_ENABLED) {
			gearMotor.set(speed);
		}
	}
	
	/**
	 * Stops the geartake motor
	 */
	public void stopAll() {
		gearMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
