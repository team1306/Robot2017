package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
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
	
	public final static double shooterSpeed = Constants.SHOOTER_SPEED;
	
	public Shooter() {
		leftShooterMotor = new CANTalon(RobotMap.LEFT_SHOOTER_PORT);
		leftShooterMotor.enable();
		rightShooterMotor = new CANTalon(RobotMap.RIGHT_SHOOTER_PORT);
		rightShooterMotor.enable();
		
		leftShooterMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftShooterMotor.reverseSensor(false);
		leftShooterMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		leftShooterMotor.configPeakOutputVoltage(+12.0f, -12.0f);
//		leftShooterMotor.setF(0);
//		leftShooterMotor.setP(0);
//		leftShooterMotor.setI(0);
//		leftShooterMotor.setD(0);
		
		rightShooterMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightShooterMotor.reverseSensor(true);
		rightShooterMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		rightShooterMotor.configPeakOutputVoltage(+12.0f, -12.0f);
//		rightShooterMotor.setF(0);
//		rightShooterMotor.setP(0);
//		rightShooterMotor.setI(0);
//		rightShooterMotor.setD(0);
		
		indexerMotor = new CANTalon(RobotMap.INDEXER_TALON_PORT);
		indexerMotor.enable();
	}
	
	/**
	 * Spins shooter forward (shooting fuel out)
	 */
	public void spinShooter() {
		if(Constants.SHOOTER_ENABLED) {
//			leftShooterMotor.changeControlMode(TalonControlMode.Speed);
//			rightShooterMotor.changeControlMode(TalonControlMode.Speed);
			SmartDashboard.putNumber("L-Shooter:",Math.abs(leftShooterMotor.getEncVelocity()));
			SmartDashboard.putNumber("R-Shooter:",Math.abs(rightShooterMotor.getEncVelocity()));
			SmartDashboard.putNumber("L-Error",leftShooterMotor.getClosedLoopError());
			SmartDashboard.putNumber("R-Error",rightShooterMotor.getClosedLoopError());
			leftShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
			rightShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
			leftShooterMotor.set(0.95);
			rightShooterMotor.set(0.95);
//			leftShooterMotor.set(1100);
//			rightShooterMotor.set(1100);
		}
	}
	
	public void setRPM(int rpm) {
		if (Constants.SHOOTER_ENABLED) {
			leftShooterMotor.changeControlMode(TalonControlMode.Speed);
			SmartDashboard.putNumber("L Shooter RPM: ", rpm);
			leftShooterMotor.set(rpm);
		}
	}
	
	/**
	 * Spins shooter backward (pulling fuel back in)
	 */
	public void spinShooterBack() {
		if (Constants.SHOOTER_ENABLED) {
			leftShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
			rightShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
			leftShooterMotor.set(-shooterSpeed);
			rightShooterMotor.set(-shooterSpeed);
		}
	}
	
	/**
	 * Returns the velocity of the shooter
	 * @param shooter
	 * 		Which shooter to read velocity from (0=left, 1=right, 2=index)
	 * @return
	 * 		Returns getEncVelocity() from talonSRX
	 */
	public double getVel(int shooter) {
		if(shooter == 0) {
			return leftShooterMotor.getEncVelocity();
		} else if(shooter == 1) {
			return rightShooterMotor.getEncVelocity();
		} else {
			return indexerMotor.getEncVelocity();
		}
	}
	
	/**
	 * Spins the shooters with a bang bang loop
	 */
	public void bangBangSpinShooter() {
		if(Constants.SHOOTER_ENABLED) {
			
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
	
	/**
	 * Spins the indexer forward (push fuel to shooters)
	 */
	public void spinIndexer() {
		if(Constants.INDEXER_ENABLED) {
			indexerMotor.changeControlMode(TalonControlMode.PercentVbus);
			SmartDashboard.putNumber("Indexers:",indexerMotor.getEncVelocity());
			indexerMotor.set(-Constants.INDEXER_SPEED);
		}
	}
	
	/**
	 * Spins indexer backward (pull fuel away from shooters)
	 */
	public void spinIndexerBack() {
		if (Constants.INDEXER_ENABLED) {
			indexerMotor.changeControlMode(TalonControlMode.PercentVbus);
			indexerMotor.set(Constants.INDEXER_SPEED);
		}
	}
	
	/**
	 * Stops the shooter when done shooting
	 * @deprecated 
	 * 		Switch to stopAll()
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
