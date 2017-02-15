package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This controls the shooter and rate at which balls are shot
 * @author Sam Roquitte and Jackson Goth
 */
public class Hopper extends Subsystem {

	private final Talon hopperMotor;
	
	public final static double hopperSpeed = Constants.HOPPER_SPEED;
	
	public Hopper() {
		hopperMotor = new Talon(RobotMap.HOPPER_TALON_PORT);
		//hopperMotor.enable();
	}
	
	/**
	 * Method that spins hopper
	 */
	public void spinHopper() {
		if(Constants.HOPPER_ENABLED) {
			hopperMotor.set(hopperSpeed);
		}
	}
	
	/**
	 * Stops all hopper motors
	 */
	public void stopAll() {
		hopperMotor.set(Constants.SPEED_ZERO);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
