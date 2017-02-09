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

	private final CANTalon shooterMotor1;
	private final CANTalon shooterMotor2;
	
	public final static double shooterSpeed = Constants.SHOOTER_SPEED;
	
	public Shooter() {

		shooterMotor1 = new CANTalon(RobotMap.FLYWHEEL_TALON_1_PORT);
		shooterMotor1.enable();
		shooterMotor2 = new CANTalon(RobotMap.FLYWHEEL_TALON_2_PORT);
		shooterMotor2.enable();
	}
	
	/**
	 * Method that spins up shooter
	 */
	public void spinShooter() {
		
		if(Constants.SHOOTER_ENABLED) {
			shooterMotor1.set(shooterSpeed);
			shooterMotor2.set(shooterSpeed);
		}
	}
	
	/**
	 * Spins the shooter with a bang bang loop
	 */
	public void bangBangSpinShooter() {

		SmartDashboard.putNumber("Shooter 1 Velocity", shooterMotor1.getEncVelocity());
		SmartDashboard.putNumber("Shooter 2 Velocity", shooterMotor2.getEncVelocity());
		
		shooterMotor1.changeControlMode(TalonControlMode.PercentVbus);
		if (shooterMotor1.getEncVelocity() > Constants.SHOOTER_BANG_RANGE) {
			shooterMotor1.set(Constants.SHOOTER_SPEED);
		}
		else {
			shooterMotor1.set(Constants.SHOOTER_BANG_CEILING);
		}

		shooterMotor2.changeControlMode(TalonControlMode.PercentVbus);
		if (shooterMotor2.getEncVelocity() > Constants.SHOOTER_BANG_RANGE) {
			shooterMotor2.set(Constants.SHOOTER_SPEED);
		}
		else {
			shooterMotor2.set(Constants.SHOOTER_BANG_CEILING);
		}
	}
	
	/**
	 * Stops the shooter when done shooting
	 * @deprecated Switch to stopAll()
	 */
	public void stopMotor() {
		shooterMotor1.set(Constants.SPEED_ZERO);
		shooterMotor2.set(Constants.SPEED_ZERO);
	}
	
	/**
	 * Stops the shooter motors
	 */
	public void stopAll() {
		shooterMotor1.set(Constants.SPEED_ZERO);
		shooterMotor2.set(Constants.SPEED_ZERO);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
