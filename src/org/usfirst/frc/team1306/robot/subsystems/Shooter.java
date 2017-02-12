package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This controls the shooter and rate at which balls are shot
 * @author Jackson Goth and Sam Roquitte
 */
public class Shooter extends Subsystem {

	private final CANTalon leftShooterMotor;
	private final CANTalon rightShooterMotor;
	
	public final static double shooterSpeed = Constants.SHOOTER_SPEED;
	
	public Shooter() {
		leftShooterMotor = new CANTalon(RobotMap.LEFT_SHOOTER_PORT);
		leftShooterMotor.enable();
		rightShooterMotor = new CANTalon(RobotMap.RIGHT_SHOOTER_PORT);
		rightShooterMotor.enable();
	}
	
	/**
	 * Method that spins up shooter
	 */
	public void spinShooter() {
		if(Constants.SHOOTER_ENABLED) {
			leftShooterMotor.set(shooterSpeed);
			rightShooterMotor.set(shooterSpeed);
		}
	}
	
	/**
	 * Spins the shooter with a bang bang loop
	 */
	public void bangBangSpinShooter() {
		
		leftShooterMotor.enableBrakeMode(false);
		rightShooterMotor.enableBrakeMode(false);

		SmartDashboard.putNumber("Shooter 1 Velocity", leftShooterMotor.getEncVelocity());
		SmartDashboard.putNumber("Shooter 2 Velocity", rightShooterMotor.getEncVelocity());
		
		leftShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		if (leftShooterMotor.getEncVelocity() > Constants.SHOOTER_BANG_RANGE) {
			leftShooterMotor.set(Constants.SHOOTER_SPEED);
		}
		else {
			leftShooterMotor.set(Constants.SHOOTER_BANG_CEILING);
		}

		rightShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		if (rightShooterMotor.getEncVelocity() > Constants.SHOOTER_BANG_RANGE) {
			rightShooterMotor.set(Constants.SHOOTER_SPEED);
		}
		else {
			rightShooterMotor.set(Constants.SHOOTER_BANG_CEILING);
		}
	}
	
	/**
	 * Stops the shooter when done shooting
	 * @deprecated Switch to stopAll()
	 */
	public void stopMotor() {
		leftShooterMotor.set(Constants.SPEED_ZERO);
		rightShooterMotor.set(Constants.SPEED_ZERO);
	}
	
	/**
	 * Stops the shooter motors
	 */
	public void stopAll() {
		leftShooterMotor.set(Constants.SPEED_ZERO);
		rightShooterMotor.set(Constants.SPEED_ZERO);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
