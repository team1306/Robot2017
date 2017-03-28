package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This controls the shooter and rate at which balls are shot
 * @author Sam Roquitte and Jackson Goth
 */
public class Hopper extends Subsystem {

	private final Talon hopperMotor;
	
	public final static double hopperSpeed = Constants.HOPPER_SPEED;
	public final static double hopperStartSpeed = 0.0;
	
	public Hopper() {
		hopperMotor = new Talon(RobotMap.HOPPER_TALON_PORT);
	}
	
	/**
	 * Spins hopper forward (fuel up to shooters)
	 */
	public void spinHopper() {
		if(Constants.HOPPER_ENABLED) {
			hopperMotor.set(-hopperSpeed);
		}
	}
	
	/**
	 * Spins hopper forward (fuel up to shooters) with steps (increasing speed)
	 * @param step
	 * 		Step of speed (1-5)
	 */
	public void spinHopper(int step) {
		if (Constants.HOPPER_ENABLED) {
			hopperMotor.set(0.1*step + 0.5);
		}
	}
	
	/**
	 * Spins hopper backwards (fuel down away from shooters)
	 */
	public void spinHopperBack() {
		if (Constants.HOPPER_ENABLED) {
			hopperMotor.set(-0.2);
		}
	}
	
	/**
	 * Stops all hopper motors
	 */
	public void stopAll() {
		hopperMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
