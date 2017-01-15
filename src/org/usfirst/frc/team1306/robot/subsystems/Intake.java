package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**this subsystem controls hood rollers
 * 
 * @author Jackson Goth
 *
 */

public class Intake extends Subsystem {

	private final Talon intakeMotor;

	public static double intakeSpeed = Constants.INTAKE_DEFAULT_SPEED;
	public static double shooterSpeed = Constants.SHOOTER_SPEED;
	
	public Intake() {
		intakeMotor = new Talon(0);
	}
	
	/*
	 * Method that continually spins intake
	 * 
	 */
	public void SpinIntake() {
		//double input = 1.0;
		//input = SmartDashboard.getNumber("Motor Power: ",input);
		
		/*
		 * For time being shooter speed in positive and intake speed is negative
		 */
		intakeMotor.set(shooterSpeed); //Change positive or negative when switching between shooter and intake
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
	 * 
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
