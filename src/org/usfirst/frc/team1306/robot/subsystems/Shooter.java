package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinShooter;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This controls the shooter and rate at which balls are shot
 * @author Jackson Goth and Sam Roquitte
 */
public class Shooter extends Subsystem {

	private final CANTalon shooterMotor;
	
	public final static double shooterSpeed = Constants.SHOOTER_SPEED;
	
	public Shooter() {

		shooterMotor = new CANTalon(RobotMap.FLYWHEEL_TALON_PORT);
		shooterMotor.enable();
		shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterMotor.reverseSensor(true);
		shooterMotor.configEncoderCodesPerRev(256);
		shooterMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		shooterMotor.configPeakOutputVoltage(+12.0f, -12.0f);

		shooterMotor.setProfile(0);
		shooterMotor.setF(0.1116076294277929);	//Calculated constant
		shooterMotor.setP(Double.MAX_VALUE);
		shooterMotor.setI(0);
		shooterMotor.setD(0);
	}
	
	/**
	 * Method that spins up shooter
	 */
	public void spinShooter() {
		
		if(Constants.SHOOTER_ENABLED) {
			shooterMotor.set(shooterSpeed);
		}
	}
	
	/**
	 * Spins the shooter with a bang bang loop
	 */
	
	//private static double shooterMotorPVB = 0.80;
	
	public void bangBangSpinShooter() {

		SmartDashboard.putNumber("Enc Velocity", shooterMotor.getEncVelocity());
		
		//Working Bang Bang Loop as of 1/28 11PM
		shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		/*if(shooterMotor.getEncVelocity() > 6000) {
			shooterMotor.set(0.60);
		} else */if (shooterMotor.getEncVelocity() > 4369) {
			shooterMotor.set(0.50);
		}
		else {
			shooterMotor.set(1.0);
		}
		
		
		//TODO Stepper code that hasn't been properly tested or finished
		/*final double stepValue = 0.01;
		
		if (((shooterMotor.getEncVelocity()*600)/1024) < 4000) {
			//shooterMotorPVB+=stepValue;
			double difference = 4000 - ((shooterMotor.getEncVelocity()*600)/1024);
			double P = difference/4000;
			shooterMotorPVB = shooterMotorPVB+P;
		}
		/*else {
			shooterMotorPVB-=stepValue;
		}*/
		
		//shooterMotor.set(shooterMotorPVB);
		
		//TODO PIDF code that hasn't been properly tested
		/*shooterMotor.changeControlMode(TalonControlMode.Speed);
		shooterMotor.set(1000);*/
	}
	
	/**
	 * Stops the shooter when done shooting
	 * @deprecated Switch to stopAll()
	 */
	public void stopMotor() {
		shooterMotor.set(0.0);
	}
	
	/**
	 * Stops the shooter motors
	 */
	public void stopAll() {
		shooterMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SpinShooter());
	}
}
