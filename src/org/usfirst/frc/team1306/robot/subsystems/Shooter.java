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
 * @author Jackson Goth
 */
public class Shooter extends Subsystem {

	private final CANTalon shooterMotor;
	//private final Talon shooterMotorsr;
	//private final Talon shooterMotor;
	
	public final static double shooterSpeed = Constants.SHOOTER_SPEED;
	
	public Shooter() {
		//shooterMotorsr = new Talon(1);
		shooterMotor = new CANTalon(RobotMap.FLYWHEEL_TALON_PORT);
		shooterMotor.enable();
		/*shooterMotor.changeControlMode(TalonControlMode.PercentVbus);*/
		//shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		/*shooterMotor.reverseSensor(true);
		shooterMotor.configEncoderCodesPerRev(256);
		shooterMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		shooterMotor.configPeakOutputVoltage(+12.0f, -12.0f);

		shooterMotor.setProfile(0);
		shooterMotor.setF(0.1116076294277929);	//Calculated constant
		shooterMotor.setP(0.1);
		shooterMotor.setI(0);
		shooterMotor.setD(0);*/
	}
	
	public void spin50() {
		shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		shooterMotor.set(0.8);
	}
	
	/**
	 * Method that spins up shooter
	 */
	public void spinShooter() {
		
		if(Constants.SHOOTER_ENABLED) {
			SmartDashboard.putNumber("Enc VEL No Bang",shooterMotor.getEncVelocity());
			shooterMotor.set(shooterSpeed);
		}
	}
	
	/**
	 * Method that will control shooter with PID
	 * TODO Finish this when using Talon and Encoder again
	 */
	public void pidShooter() {
		//shooterMotor.changeControlMode(TalonControlMode.Speed);
		//shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
	}
	
	/**
	 * Spins the shooter with a bang bang loop
	 */
	static double shooterMotorPVB = 0.8;
	int i = 1;
	public void bangBangSpinShooter() {
		SmartDashboard.putNumber("IS HERE", i);
		i++;
		//shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		SmartDashboard.putNumber("ENC Vel BANG", shooterMotor.getEncVelocity());
		shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		if (shooterMotor.getEncVelocity() > 7000) {
			shooterMotor.set(0.80);
		}
		else {
			shooterMotor.set(1.0);
		}
		
		/*shooterMotor.set(shooterMotorPVB);
		
		double stepValue = 0.01;
		
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
		
		/*if (shooterMotor.getEncVelocity() < 4000) {
			shooterMotor.set(1023);
			SmartDashboard.putNumber("BANG", 1);
		} else {
			shooterMotor.set(0);
			SmartDashboard.putNumber("BANG", 0);
		}*/
		//shooterMotor.set(shooterSpeed);
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
