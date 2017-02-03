package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This controls the intake of the robot; the rate at which the wheels spin.
 * @author Jackson Goth
 */

public class Intake extends Subsystem {

	private final Talon intakeMotor;

	public static double intakeSpeed = Constants.INTAKE_SPEED;
	
	public Intake() {
		intakeMotor = new Talon(RobotMap.INTAKE_TALON_PORT);
	}
	
	/**
	 * Method that continually spins intake
	 */
	public void spinIntake() {
		
		if(Constants.INTAKE_ENABLED) {
			intakeMotor.set(-intakeSpeed);
		}
	}
	
	/**
	 * Stops the intake when command is done
	 */
	public void stopMotor() {
		intakeMotor.set(Constants.SPEED_ZERO);
	}
	
	public void stopAll() {
		intakeMotor.set(Constants.SPEED_ZERO);
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
