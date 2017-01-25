package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.TankDrive;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls drivetrain motors with the joysticks from OI.java, and can limit intake speed
 * @author Jackson Goth and Sam Roquitte
 */

public class Drivetrain extends Subsystem {
	
	private final CANTalon[] motors;	
	private final CANTalon leftmotor1;
	private final CANTalon rightmotor1;
	private final CANTalon leftmotor2;
	private final CANTalon rightmotor2;
	
	public Drivetrain() {
		leftmotor1 = new CANTalon(RobotMap.LEFT_TALON_1_PORT);
		rightmotor1 = new CANTalon(RobotMap.RIGHT_TALON_1_PORT);
		leftmotor2 = new CANTalon(RobotMap.LEFT_TALON_2_PORT);
		rightmotor2 = new CANTalon(RobotMap.RIGHT_TALON_2_PORT);
		
		motors = new CANTalon[] {leftmotor1, rightmotor1};
		setupMotors(leftmotor1,leftmotor2);
		setupMotors(rightmotor1,rightmotor2);
	}
	
	/**
	 * Takes two motors and sets one to mimic the other
	 * @param master
	 * 		MotorController to be followed
	 * @param slave
	 * 		MotorController to mimic the master
	 */
	private void setupMotors(CANTalon master, CANTalon slave) {
		
		master.changeControlMode(TalonControlMode.PercentVbus);
		master.set(0.0);
		master.enable();
		
		slave.changeControlMode(TalonControlMode.Follower);
		slave.set(master.getDeviceID());
		slave.enable();
	}
	
	/**
	 * Takes 2 params to control the motors
	 * 
	 * @param leftVal
	 * 		Speed for the left side (from -1 to 1)
	 * @param rightVaL
	 * 		Speed for the right side (from -1 to 1)
	 */
	public void tankDrive(double leftVal, double rightVal) {
		
		leftmotor1.changeControlMode(TalonControlMode.PercentVbus);
		rightmotor1.changeControlMode(TalonControlMode.PercentVbus);
	
		if(Constants.DRIVETRAIN_ENABLED) {
			leftmotor1.set(leftVal*Constants.SPEED_MODIFIER);
			rightmotor1.set(-rightVal*Constants.SPEED_MODIFIER);
		}
			
		/*
		 * Currently lowers speed of intake motor when drivetrain speed is above 0.5
		 */
		if(rightVal*Constants.SPEED_MODIFIER >= 0.5 && leftVal*Constants.SPEED_MODIFIER >= 0.5) {
			Intake.lowerSpeed();
		} else {
			Intake.raiseSpeed();
		}
		
	}
	
	/**
	 * Sets the motor speed to 0
	 * 
	 * @param motor
	 * 		The motor that you would like to stop 0,1,2,3:(left1, left2, right1, right2)
	 */
	public void stopMotor(int motor) {
		motors[motor].set(0.0);
	}
	
	public void stopAll() {
		for (int i = 0; i < motors.length; i++) {
			motors[i].set(0.0);
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}
}