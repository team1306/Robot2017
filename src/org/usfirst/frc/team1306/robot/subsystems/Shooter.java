package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.shooter.BangSpinShooter;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
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
	public void bangBangSpinShooter() {

		SmartDashboard.putNumber("Enc Velocity", shooterMotor.getEncVelocity());
		
		shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		if (shooterMotor.getEncVelocity() > Constants.SHOOTER_BANG_RANGE) { //Calculated Value
			shooterMotor.set(Constants.SHOOTER_BANG_RANGE);
		}
		else {
			shooterMotor.set(Constants.SHOOTER_BANG_CEILING);
		}

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
		setDefaultCommand(new BangSpinShooter());
	}
}
