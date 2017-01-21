package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This controls the intake of the robot; the rate at which the rollers spin.
 * @author Jackson Goth
 *
 */

public class Intake extends Subsystem {

	private final Talon intakeMotor;

	public static double intakeSpeed = Constants.INTAKE_DEFAULT_SPEED;
	
	public Intake() {
		intakeMotor = new Talon(1);
	}
	
	/*
	 * Method that continually spins intake
	 */
	public void spinIntake() {
		
		if(Constants.INTAKE_ENABLED) {
			intakeMotor.set(-intakeSpeed);
		}
	}
	
	/*
	 * Lowers speed of intake for specific situations
	 * See tankDrive(); for usage
	 */
	public static void lowerSpeed() {
		
		intakeSpeed = Constants.INTAKE_DEFAULT_SPEED - Constants.INTAKE_SPEED_CHANGE;
	}
	
	/*
	 * Raises speed of intake back to Default for specific situations
	 *  See tankDrive(); for usage
	 */
	public static void raiseSpeed() {
		
		intakeSpeed = Constants.INTAKE_DEFAULT_SPEED;
	}
	
	/*
	 * Stops the intake when command is done
	 */
	public void stopMotor() {
		intakeMotor.set(0.0);
	}
	
	public void stopAll() {
		intakeMotor.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
