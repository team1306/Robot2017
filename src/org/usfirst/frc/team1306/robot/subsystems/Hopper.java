package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.hopper.SpinHopper;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This controls the shooter and rate at which balls are shot
 * @author Sam Roquitte and Jackson Goth
 */
public class Hopper extends Subsystem {

	private final CANTalon hopperMotor;
	
	public final static double hopperSpeed = Constants.HOPPER_SPEED;
	
	public Hopper() {
		hopperMotor = new CANTalon(RobotMap.HOPPER_TALON_PORT);
		hopperMotor.enable();
	}
	
	/**
	 * Method that spins up shooter
	 */
	public void spinHopper() {
		if(Constants.HOPPER_ENABLED) {
			hopperMotor.set(hopperSpeed);
		}
	}
	
	/**
	 * Stops the shooter motors
	 */
	public void stopAll() {
		hopperMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SpinHopper());
	}
}
