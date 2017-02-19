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
	private final CANTalon indexerMotor;
	
	public final static double shooterSpeed = -Constants.SHOOTER_SPEED;
	
	public Shooter() {
		leftShooterMotor = new CANTalon(RobotMap.LEFT_SHOOTER_PORT);
		leftShooterMotor.configEncoderCodesPerRev(12);
		leftShooterMotor.enable();
		rightShooterMotor = new CANTalon(RobotMap.RIGHT_SHOOTER_PORT);
		rightShooterMotor.enable();
		rightShooterMotor.configEncoderCodesPerRev(12);
		indexerMotor = new CANTalon(RobotMap.INDEXER_TALON_PORT);
	}
	
	/**
	 * Method that spins up shooter
	 */
	public void spinShooter() {
		if(Constants.SHOOTER_ENABLED) {
			leftShooterMotor.set(shooterSpeed);
			rightShooterMotor.set(shooterSpeed);
			SmartDashboard.putNumber("LeftShooter",Math.abs(leftShooterMotor.getEncVelocity()));
			SmartDashboard.putNumber("RightShooter",Math.abs(rightShooterMotor.getEncVelocity()));
		}
	}
	
	public void spinShooterBack() {
		if (Constants.SHOOTER_ENABLED) {
			leftShooterMotor.set(-shooterSpeed);
			rightShooterMotor.set(-shooterSpeed);
		}
	}
	
	public double getVel(int motor) {
		if(motor == 0) {
			return leftShooterMotor.getEncVelocity();
		} else if(motor == 1) {
			return rightShooterMotor.getEncVelocity();
		} else {
			return indexerMotor.getEncVelocity();
		}
	}
	
	/**
	 * Spins the shooter with a bang bang loop
	 */
	public void bangBangSpinShooter() {
		if(Constants.SHOOTER_ENABLED) {
			leftShooterMotor.enableBrakeMode(false);
			rightShooterMotor.enableBrakeMode(false);

			SmartDashboard.putNumber("LeftShooterBang", Math.abs(leftShooterMotor.getEncVelocity()));
			SmartDashboard.putNumber("RightShooterBang", Math.abs(rightShooterMotor.getEncVelocity()));
			
			leftShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
			if (Math.abs(leftShooterMotor.getEncVelocity()) > Constants.SHOOTER_BANG_RANGE) {
				leftShooterMotor.set(-Constants.SHOOTER_SPEED);
			}
			else {
				leftShooterMotor.set(-Constants.SHOOTER_BANG_CEILING);
			}

			rightShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
			if (Math.abs(rightShooterMotor.getEncVelocity()) > Constants.SHOOTER_BANG_RANGE) {
				rightShooterMotor.set(-Constants.SHOOTER_SPEED);
			}
			else {
				rightShooterMotor.set(-Constants.SHOOTER_BANG_CEILING);
			}
		}
	}
	
	public void spinIndexer() {
		if(Constants.INDEXER_ENABLED) {
			indexerMotor.changeControlMode(TalonControlMode.PercentVbus);
			indexerMotor.set(-Constants.INDEXER_SPEED);
		}
	}
	
	public void spinIndexerBack() {
		if (Constants.INDEXER_ENABLED) {
			indexerMotor.changeControlMode(TalonControlMode.PercentVbus);
			indexerMotor.set(Constants.INDEXER_SPEED);
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
		indexerMotor.set(Constants.SPEED_ZERO);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
