package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.lib.util.DriveSide;
import org.usfirst.frc.team1306.lib.util.Settings;
import org.usfirst.frc.team1306.lib.util.Settings.DriveMode;
import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.drivetrain.ArcadeDrive;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Drivetrain
 * 
 * This subsystem is initialzed with a set of Settings provided from the CommandBase.
 * It uses that configuration to set-up sensors and the control mode for the driver, as well as putting each
 * side of the drivetrain into a seperate DriveSide object with the appropriate amount of Talons SRXs to control.
 * 
 * @author Jackson Goth
 */
public class Drivetrain extends Subsystem {

	public DriveSide leftMotors, rightMotors; //Sides of the drivetrain (each acts like a Talon SRX)
	public DriveMode mode; //Initial control mode for getting input from controllers
	public Gyro gyro; //Main gyro object other classes with reference
	
	public Drivetrain(Settings settings) {
		
		leftMotors = new DriveSide(settings.leftSide);
		rightMotors = new DriveSide(settings.rightSide);
		
		mode = settings.driveMode; //Drivetrain configuration (talons, gyro, encoders)
		
		/* If gyro is present, makes it accessible */
		if(settings.gyro != null) {
			gyro = settings.gyro;
		}
		
		/* If encoders are present, initialized them in appropriate driveside */
		if(settings.encodersPresent) {
			leftMotors.initEncoders();
			rightMotors.initEncoders();
		}
	}

	/**
	 * Powers each DriveSide with a left and right PercentVbus speed
	 * @param leftVal - Speed for left motors
	 * @param rightVal - Speed for right motors
	 */
	public void driveVBus(double leftVal, double rightVal) {
		
		leftMotors.changeControlMode(TalonControlMode.PercentVbus);
		rightMotors.changeControlMode(TalonControlMode.PercentVbus);

		SmartDashboard.putNumber("leftVbus",leftVal);
		SmartDashboard.putNumber("rightVbus",rightVal);
		
		if(Constants.DRIVETRAIN_ENABLED) {
			leftMotors.set(leftVal*0.3);
			rightMotors.set(-rightVal*0.3); 
		}
	}
	
	/**
	 * Powers each DriveSide with a left and right speed
	 * @param leftVal - Speed for left motors
	 * @param rightVal - Speed for right motors
	 */
	public void driveSpeed(double leftVal, double rightVal) {
		
		leftMotors.changeControlMode(TalonControlMode.Speed);
		rightMotors.changeControlMode(TalonControlMode.Speed);
		
		SmartDashboard.putNumber("leftSpeed",leftVal);
		SmartDashboard.putNumber("rightSpeed",rightVal);
		
		if(Constants.DRIVETRAIN_ENABLED) {
			leftMotors.set(leftVal);
			rightMotors.set(-rightVal); 
		}
	}
	
	/**
	 * Forces all drive-motors to stop turning
	 */
	public void stop() {
		leftMotors.changeControlMode(TalonControlMode.PercentVbus);
		rightMotors.changeControlMode(TalonControlMode.PercentVbus);
		leftMotors.set(0.0);
		rightMotors.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}
}
