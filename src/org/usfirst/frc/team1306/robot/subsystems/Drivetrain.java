package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.TankDrive;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
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
	//private final CANTalon leftmotor3;
	//private final CANTalon rightmotor3;
	
	public Drivetrain() {
		leftmotor1 = new CANTalon(RobotMap.LEFT_TALON_1_PORT);
		rightmotor1 = new CANTalon(RobotMap.RIGHT_TALON_1_PORT);
		leftmotor2 = new CANTalon(RobotMap.LEFT_TALON_2_PORT);
		rightmotor2 = new CANTalon(RobotMap.RIGHT_TALON_2_PORT);
		//leftmotor3 = new CANTalon(RobotMap.LEFT_TALON_3_PORT);
		//rightmotor3 = new CANTalon(RobotMap.RIGHT_TALON_3_PORT);
		
		motors = new CANTalon[] {leftmotor1, rightmotor1};
		setupMotors(leftmotor1,leftmotor2);
		setupMotors(rightmotor1,rightmotor2);
		
		//PID Drivetrain Code
		leftmotor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftmotor1.reverseSensor(false);
		leftmotor1.configEncoderCodesPerRev(256);
		leftmotor1.configNominalOutputVoltage(+0.0f, -0.0f);
		leftmotor1.configPeakOutputVoltage(+12.0f, -12.0f);
		
		rightmotor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightmotor1.reverseSensor(false);
		rightmotor1.configEncoderCodesPerRev(256);
		rightmotor1.configNominalOutputVoltage(+0.0f, -0.0f);
		rightmotor1.configPeakOutputVoltage(+12.0f, -12.0f);

		leftmotor1.setProfile(0);
		leftmotor1.setF(Constants.F);	//Calculated constant
		leftmotor1.setP(Constants.P);		//Calculated constant
		leftmotor1.setI(Constants.I);	//Calculated constant 1/100 of P-gain
		leftmotor1.setD(Constants.D);
		
		rightmotor1.setProfile(0);
		rightmotor1.setF(Constants.F);	//Calculated constant
		rightmotor1.setP(Constants.P);		//Calculated constant
		rightmotor1.setI(Constants.I);	//Calculated constant 1/100 P-gain
		rightmotor1.setD(Constants.D);
	}
	
	/**
	 * Sets up the motors, master in pvb mode and sets the slave motor to a follower
	 * @param master
	 * 		The talon that will be the master, folower will follow this talon
	 * @param slave
	 * 		The follower talon
	 */
	private void setupMotors(CANTalon master, CANTalon slave) {
		master.changeControlMode(TalonControlMode.PercentVbus);
		master.set(Constants.SPEED_ZERO);
		master.enable();
		
		slave.changeControlMode(TalonControlMode.Follower);
		slave.set(master.getDeviceID());
		slave.enable();
	}
	
	/**
	 * Sets up motors, master in pvb mode and sets 2 slaves as followers
	 * @param master
	 * 		The talon that will be the master, folower will follow this talon
	 * @param slave1
	 * 		First follower talon
	 * @param slave2
	 * 		Second follower talon
	 */
	private void setupMotors(CANTalon master, CANTalon slave1, CANTalon slave2) {
		master.changeControlMode(TalonControlMode.PercentVbus);
		master.set(Constants.SPEED_ZERO);
		master.enable();
		
		slave1.changeControlMode(TalonControlMode.Follower);
		slave1.set(master.getDeviceID());
		slave1.enable();
		
		slave2.changeControlMode(TalonControlMode.Follower);
		slave2.set(master.getDeviceID());
		slave2.enable();
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
		SmartDashboard.putNumber("leftSpeed",leftmotor1.getEncVelocity());
		SmartDashboard.putNumber("rightSpeed",rightmotor1.getEncVelocity());
		
		if(Constants.DRIVETRAIN_ENABLED) {
			leftmotor1.set(-leftVal/**Constants.SPEED_MODIFIER*/);
			rightmotor1.set(rightVal/**Constants.SPEED_MODIFIER*/);
		}
	}
	
	/**
	 * Takes a desired speed to run both motors at
	 * @param initVel
	 * 		Speed for both sides to match
	 */
	public void drivePID(double initVel) {
		leftmotor1.changeControlMode(TalonControlMode.Speed);
		rightmotor1.changeControlMode(TalonControlMode.Speed);
		
		if(Constants.PID_DRIVETRAIN_ENABLED) {
			SmartDashboard.putNumber("leftVal",leftmotor1.getEncVelocity());
			SmartDashboard.putNumber("rightVal",rightmotor1.getEncVelocity());
			SmartDashboard.putNumber("left.err", initVel - leftmotor1.getEncVelocity());
			SmartDashboard.putNumber("right.err", -initVel - rightmotor1.getEncVelocity());
			
			leftmotor1.set(-initVel);
			rightmotor1.set(initVel);
		}
	}
	
	/**
	 * Resets the position of both encoders back to zero
	 */
	public void resetEncoders() {
		leftmotor1.setPosition(0);
		rightmotor1.setPosition(0);
	}
	
	/**
	 * Returns the position of the left encoder
	 */
	public double getLeftPosition() {
		return leftmotor1.getEncPosition();
	}
	
	/**
	 * Returns the position of the right encoder
	 */
	public double getRightPosition() {
		return rightmotor1.getEncPosition();
	}
	
	/**
	 * Sets the motor speed to 0
	 * 
	 * @param motor
	 * 		The motor that you would like to stop 0,1:(left1, right1), also stopps follower motors
	 */
	
	public void stopMotor(int motor) {
		motors[motor].set(Constants.SPEED_ZERO);
	}
	
	public void stopAll() {
		for (int i = 0; i < motors.length; i++) {
			motors[i].set(Constants.SPEED_ZERO);
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}
}