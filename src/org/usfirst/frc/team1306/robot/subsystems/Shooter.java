package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveStraight;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinShooter;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This controls the shooter and rate at which balls are shot
 * @author Jackson Goth
 *
 */
public class Shooter extends Subsystem {

	//private final CANTalon shooterMotor;
	private final Talon shooterMotor;

	public final static double shooterSpeed = Constants.SHOOTER_SPEED;
	
	public Shooter() {
		shooterMotor = new Talon(1);
		//shooterMotor = new CANTalon(RobotMap.FLYWHEEL_TALON_PORT);
		//shooterMotor.enable();
		//shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	/**
	 * Method that spins up shooter
	 */
	public void spinShooter() {
		//SmartDashboard.putNumber("ENC Vel NOBANG", shooterMotor.getEncVelocity());
		if(Constants.SHOOTER_ENABLED) {
			//shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
			shooterMotor.set(SmartDashboard.getNumber("Shooter Speed"));
		}
	}
	
	/**
	 * Spins the shooter with a bang bang loop
	 */
	public void bangBangSpinShooter() {
		//SmartDashboard.putNumber("ENC Vel BANG", shooterMotor.getEncVelocity());
		/*if (shooterMotor.getEncVelocity() < Constants.SHOOTER_BANG_SPEED) {
			shooterMotor.set(1.0);
			SmartDashboard.putNumber("BANG", 1);
		} else {
			shooterMotor.set(Constants.SHOOTER_SPEED);
			SmartDashboard.putNumber("BANG", 0);
		}*/
		shooterMotor.set(shooterSpeed);
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
