package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This controls the climber subsystem.
 * @author Jackson Goth
 */
public class Climber extends Subsystem{

	private final Talon climberMotor1;
	private final Talon climberMotor2;
	
	public Climber() {
		climberMotor1 = new Talon(RobotMap.CLIMBER_TALON_1_PORT);
		climberMotor2 = new Talon(RobotMap.CLIMBER_TALON_2_PORT);
	}
	
	/**
	 * Spins the climber forward
	 */
	public void spinClimber() {
		if(Constants.CLIMBER_ENABLED) {
			climberMotor1.set(-Constants.CLIMBER_SPEED);
			climberMotor2.set(-Constants.CLIMBER_SPEED);
		}
	}
	
	/**
	 * Spins the climber backwards
	 */
	public void spinClimberBack() {
		if (Constants.CLIMBER_ENABLED) {
			climberMotor1.set(-Constants.CLIMBER_BACK_SPEED);
			climberMotor2.set(-Constants.CLIMBER_BACK_SPEED);
		}
	}
	
	/**
	 * Stops the climber
	 */
	public void stopAll() {
		climberMotor1.set(0.0);
		climberMotor2.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
