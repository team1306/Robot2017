package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.VelocityMeasurementPeriod;

import edu.wpi.first.wpilibj.command.Subsystem;

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
		indexerMotor = new CANTalon(RobotMap.INDEXER_TALON_PORT);
		indexerMotor.enable();
		
//		leftShooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		leftShooterMotor.configEncoderCodesPerRev(12);
//		leftShooterMotor.reverseSensor(true);
//		leftShooterMotor.configNominalOutputVoltage(+0.0f, -0.0f);
//		leftShooterMotor.configPeakOutputVoltage(+12.0f, 0.0f);
//		leftShooterMotor.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_10Ms);
//		leftShooterMotor.SetVelocityMeasurementWindow(20);
//		leftShooterMotor.setF(Constants.SHOOTER_F);
//		leftShooterMotor.setP(Constants.SHOOTER_P);
//		leftShooterMotor.setI(Constants.SHOOTER_I);
//		leftShooterMotor.setD(Constants.SHOOTER_D);
//		
//		rightShooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		rightShooterMotor.configEncoderCodesPerRev(12);
//		rightShooterMotor.reverseSensor(false);
//		rightShooterMotor.configNominalOutputVoltage(+0.0f, -0.0f);
//		rightShooterMotor.configPeakOutputVoltage(+12.0f, 0.0f);
//		rightShooterMotor.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_10Ms);
//		rightShooterMotor.SetVelocityMeasurementWindow(20);
//		rightShooterMotor.setF(Constants.SHOOTER_F);
//		rightShooterMotor.setP(Constants.SHOOTER_P);
//		rightShooterMotor.setI(Constants.SHOOTER_I);
//		rightShooterMotor.setD(Constants.SHOOTER_D);
//		
//		indexerMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//		indexerMotor.reverseSensor(false);
//		indexerMotor.configNominalOutputVoltage(+0.0f, -0.0f);
//		indexerMotor.configPeakOutputVoltage(+12.0f, -12.0f);
//		rightShooterMotor.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_10Ms);
//		rightShooterMotor.SetVelocityMeasurementWindow(20);
//		indexerMotor.setF(Constants.INDEXER_F);
//		indexerMotor.setP(Constants.INDEXER_P);
//		indexerMotor.setI(Constants.INDEXER_I);
//		indexerMotor.setD(Constants.INDEXER_D);
	}
	
	/**
	 * Spins shooter forward (shooting fuel out)
	 */
	public void spinShooter() {
		if(Constants.SHOOTER_ENABLED) {
			leftShooterMotor.changeControlMode(TalonControlMode.Voltage);
			rightShooterMotor.changeControlMode(TalonControlMode.Voltage);
			leftShooterMotor.setVoltageCompensationRampRate(24);
			rightShooterMotor.setVoltageCompensationRampRate(24);
			leftShooterMotor.set(8.00); //0.78
			rightShooterMotor.set(8.00); //0.78
			
//			leftShooterMotor.changeControlMode(TalonControlMode.Speed);
//			rightShooterMotor.changeControlMode(TalonControlMode.Speed);
//			
//			leftShooterMotor.set(Constants.SHOOTER_RPM_SPEED);
//			rightShooterMotor.set(Constants.SHOOTER_RPM_SPEED);
		}
	}

	public void spinShooterAlt() {
		if(Constants.SHOOTER_ENABLED) {
//			leftShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
//			rightShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
//			leftShooterMotor.set(shooterSpeed);
//			rightShooterMotor.set(shooterSpeed);
			
			leftShooterMotor.changeControlMode(TalonControlMode.Speed);
			rightShooterMotor.changeControlMode(TalonControlMode.Speed);
			
			leftShooterMotor.set(Constants.SHOOTER_RPM_SPEED_ALT);
			rightShooterMotor.set(Constants.SHOOTER_RPM_SPEED_ALT);
		}
	}
	
	double getIndexError() {
		return indexerMotor.getError();
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
	 * Spins the indexer forward (push fuel to shooters)
	 */
	public void spinIndexer() {
		if(Constants.INDEXER_ENABLED) {
			indexerMotor.changeControlMode(TalonControlMode.PercentVbus);
			indexerMotor.set(Constants.INDEXER_SPEED);
//			indexerMotor.changeControlMode(TalonControlMode.Speed);
//			indexerMotor.set(Constants.INDEXER_RPM_SPEED * (24/18));	//*(24/18)
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
	 * Returns the velocity of the shooter
	 * @param shooter
	 * 		Which motor to read velocity from (0=left, 1=right, 2=index)
	 * @return
	 * 		Returns RPM from the talon
	 */
	public double getVel(int shooter) {
		if(shooter == 0) {
			return leftShooterMotor.getSpeed();
		} else if(shooter == 1) {
			return rightShooterMotor.getSpeed();
		} else {
			return indexerMotor.getSpeed();
		}
	}
	
	/**
	 * Stops all shooter-related motors
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
