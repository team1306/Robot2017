package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.TankDrive;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the motors with the joysticks from oi
 * @author Sam Roquitte
 */

public class Drivetrain extends Subsystem {
	private final CANTalon[] motors;	
	private final CANTalon leftmotor1;
	private final CANTalon rightmotor1;
	
	private static double initialLeft;
	private static double initialRight;
	private static double rightCompensation;
	
	public Drivetrain() {
		leftmotor1 = new CANTalon(RobotMap.LEFT_TALON_1_PORT);
		leftmotor1.setPosition(0);
		rightmotor1 = new CANTalon(RobotMap.RIGHT_TALON_1_PORT);
		rightmotor1.setPosition(0);
		
		motors = new CANTalon[] {leftmotor1, rightmotor1};
		
		/*initialLeft = leftmotor1.getEncPosition();
		initialRight = rightmotor1.getEncPosition();
		if(initialLeft > initialRight){
			rightCompensation = initialRight - initialLeft;
		} else if(initialRight > initialLeft) {
			rightCompensation = initialLeft - initialRight;
		} else {
			rightCompensation = 0;
		}*/
		
	}
	
	/**
	 * Takes 2 params to control the motors
	 * 
	 * @param leftVal
	 * 		Speed for the left side (from -1 to 1)
	 * @param rightVal
	 * 		Speed for the right side (from -1 to 1)
	 */
	
	public void tankDrive(double leftVal, double rightVal) {
		
		leftmotor1.changeControlMode(TalonControlMode.PercentVbus);
		rightmotor1.changeControlMode(TalonControlMode.PercentVbus);
		
		//double leftError = Math.abs(leftmotor1.getEncPosition() - (rightmotor1.getEncPosition() + rightCompensation));
		//double rightError = Math.abs(rightmotor1.getEncPosition() + rightCompensation - leftmotor1.getEncPosition());
		leftmotor1.set(-leftVal*Constants.SPEED_MODIFIER/**(.5*leftError)*/);
		rightmotor1.set(rightVal*Constants.SPEED_MODIFIER/**(.5*rightError)*/);
		
		SmartDashboard.putNumber("Drivetrain Speed",rightVal*Constants.SPEED_MODIFIER);
		SmartDashboard.putNumber("Intake Speed",Intake.intakeSpeed);
		
		/*
		 * Currently lowers speed of intake motor when drivetrain speed is above 0.5
		 */
		if(rightVal*Constants.SPEED_MODIFIER > .5) {
			Intake.lowerSpeed();
		} else {
			Intake.raiseSpeed();
		}
		
		//SmartDashboard.putNumber("Left Encoder",leftmotor1.getEncPosition());
		//SmartDashboard.putNumber("Right Encoder",rightmotor1.getEncPosition()/* + rightCompensation*/);
		//SmartDashboard.putNumber("Left Vel: ",leftmotor1.getEncVelocity());
		//SmartDashboard.putNumber("Right Vel: ",rightmotor1.getEncVelocity());
	}
	
	public void DrivePID() {
		leftmotor1.changeControlMode(TalonControlMode.Speed);
		rightmotor1.changeControlMode(TalonControlMode.Speed);
		leftmotor1.setPID(Constants.LEFT_P, Constants.LEFT_I, Constants.LEFT_D);
		rightmotor1.setPID(Constants.RIGHT_P, Constants.RIGHT_I, Constants.RIGHT_D);
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