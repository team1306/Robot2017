package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.intake.SpinIntake;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This controls the intake of the robot.
 * @author Jackson Goth
 */
public class Intake extends Subsystem {

	private final Talon intakeMotor;

	public static double intakeSpeed = Constants.INTAKE_SPEED;
	
	public Intake() {
		intakeMotor = new Talon(RobotMap.INTAKE_TALON_PORT);
	}
	
	/**
	 * Spins the intake forward
	 */
	public void spinIntake() {
		if(Constants.INTAKE_ENABLED) {
			intakeMotor.set(intakeSpeed);
		}
	}
	
	/**
	 * Stops the intake motor
	 */
	public void stopAll() {
		intakeMotor.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new SpinIntake());
	}
}
