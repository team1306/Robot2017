package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.Constants.ControlMode;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.ArcadeDrive;
import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveMode;
import org.usfirst.frc.team1306.robot.commands.drivetrain.TankDrive;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is the drivetrain subsystem that controls two motors on each side of the robot. One of which
 * is in a follower mode that mimics the adjacent motor. This subsytem also gets information from an encoder
 * on each side of the robot as well as a gyro to allow for more complex manuevers.
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public class Drivetrain extends Subsystem {
	
	private final CANTalon leftmotor1;
	private final CANTalon rightmotor1;
	private final CANTalon leftmotor2;
	private final CANTalon rightmotor2;
	public double initAngle;
	Alliance alliance;
//	AHRS ahrs; //Navx Gyro
	
	public Drivetrain() {
		
		leftmotor1 = new CANTalon(RobotMap.LEFT_TALON_1_PORT);
		rightmotor1 = new CANTalon(RobotMap.RIGHT_TALON_1_PORT);
		leftmotor2 = new CANTalon(RobotMap.LEFT_TALON_2_PORT);
		rightmotor2 = new CANTalon(RobotMap.RIGHT_TALON_2_PORT);
		
		setupMotors(leftmotor1,leftmotor2);
		setupMotors(rightmotor1,rightmotor2);
		
		alliance = DriverStation.getInstance().getAlliance();
		
//		try {
//			ahrs = new AHRS(SPI.Port.kMXP); //Attempting to Initialize Gyro
//			SmartDashboard.putBoolean("Gyro Connected",true);
//		} catch(RuntimeException ex) {
//			SmartDashboard.putBoolean("Gyro Connected",false);
//		}
//		
//		ahrs.reset();
	}
	/**
	 * Sets up the motors, master in pvb mode and sets the slave motor as a follower
	 * @param master
	 * 		The talon that will be the master, follower will mimic this talon
	 * @param slave
	 * 		The talon that will be the follower
	 */
	private void setupMotors(CANTalon master, CANTalon slave) {
		
		master.changeControlMode(TalonControlMode.PercentVbus);
		master.set(0.0);
		master.enable();
		
		slave.changeControlMode(TalonControlMode.Follower);
		slave.set(master.getDeviceID());
		slave.enable();
		
		//Sets up the encoder for the left side of the drivetrain with the correct settings
		leftmotor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftmotor1.reverseSensor(false); //Encoder output doesn't need to be reversed
		leftmotor1.reverseOutput(false); //Throttle output doesn't need to be reversed
		leftmotor1.configEncoderCodesPerRev(256);
		leftmotor1.configNominalOutputVoltage(+0.0f, -0.0f);
		leftmotor1.configPeakOutputVoltage(+12.0f, -12.0f);
		
		//Sets up the encoder for the right side of the drivetrain with the correct settings
		rightmotor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightmotor1.reverseSensor(true); //Reverses output of encoder signals going into talon
		rightmotor1.reverseOutput(true); //Reverses throttle output getting sent to the talon
		rightmotor1.configEncoderCodesPerRev(256);
		rightmotor1.configNominalOutputVoltage(+0.0f, -0.0f);
		rightmotor1.configPeakOutputVoltage(+12.0f, -12.0f);

		leftmotor1.setMotionMagicCruiseVelocity(730*0.75); //TODO Re-tune all of these
		leftmotor1.setMotionMagicAcceleration(730*0.75);
		rightmotor1.setMotionMagicCruiseVelocity(730*0.75);
		rightmotor1.setMotionMagicAcceleration(730*0.75);
		
		//Giving the left talons the PIDF parameters to use for the control loop
		leftmotor1.setF(Constants.F);	
		leftmotor1.setP(Constants.P);
		leftmotor1.setI(Constants.I);	
		leftmotor1.setD(Constants.D);
		
		//Giving the right talons the PIDF parameters to use for the control loop
		rightmotor1.setF(Constants.F);	
		rightmotor1.setP(Constants.P);	
		rightmotor1.setI(Constants.I);	
		rightmotor1.setD(Constants.D);
	}
	
	/**
	 * General driving command that takes an input of -1 to 1 for each side of the drivetrain.
	 * @param leftVal
	 * 		Speed for the left side (from -1 to 1)
	 * @param rightVal
	 * 		Speed for the right side (from -1 to 1)
	 */
	public void tankDrive(double leftVal, double rightVal) {
		leftmotor1.changeControlMode(TalonControlMode.PercentVbus);
		rightmotor1.changeControlMode(TalonControlMode.PercentVbus);

		if(Constants.DRIVETRAIN_ENABLED) {
			if(!(Constants.CONTROL_MODE.equals(ControlMode.OUTREACH))) {
				leftmotor1.set(leftVal*Constants.SPEED_MODIFIER);
				rightmotor1.set(-rightVal*Constants.SPEED_MODIFIER); 
			} else {
				leftmotor1.set(leftVal*Constants.SPEED_MODIFIER*Constants.OUTREACH_MODIFIER);
				rightmotor1.set(-rightVal*Constants.SPEED_MODIFIER*Constants.OUTREACH_MODIFIER);
			}
			
		}
	}
	
	/**
	 * Autonomous driving command that takes an input in feet,
	 * and drives the robot to that distance using motion profiles.
	 * @param feet
	 * 		Distance to which the robot should travel
	 */
	public void driveDistance(double feet) {
		leftmotor1.changeControlMode(TalonControlMode.MotionMagic);
		rightmotor1.changeControlMode(TalonControlMode.MotionMagic);
		
		if(Constants.DRIVETRAIN_ENABLED) {
			resetEncoders();
			
			//TODO Write this command so it works correctly
			if(feet == 3) {	
				leftmotor1.set(1.58);
				rightmotor1.set(-1.58);	
			} else if(feet == 4) {
				leftmotor1.set(5.5);
				rightmotor1.set(5.5);
			} else {
				leftmotor1.set(feet);
				rightmotor1.set(feet);
			}
		}
	}
	
	/**
	 * Function that resets the gyro.
	 */
	public void resetGyro() {
		//TODO Write this for new gyro
	}
	
	/**
	 * Function that returns the current heading value of gyro.
	 */
	public double getHeading() {
		return 0; //TODO Make this return actual values from new Gyro
	}
	
	/**
	 * Resets the position of both drivetrain encoders.
	 */
	public void resetEncoders() {
		leftmotor1.setPosition(0);
		rightmotor1.setPosition(0);
	}
	
	/**
	 * Returns the position of the left encoder.
	 */
	public int getLeftPosition() {
		return leftmotor1.getEncPosition();
	}
	
	/**
	 * Returns the position of the right encoder.
	 */
	public int getRightPosition() {
		return rightmotor1.getEncPosition();
	}
	
	/**
	 * Returns the velocity being read by the left encoder.
	 */
	public double getLeftVel() {
		return leftmotor1.getEncVelocity();
	}
	
	/**
	 * Returns the velocity being read by the right encoder.
	 */
	public double getRightVel() {
		return rightmotor1.getEncVelocity();
	}
	
	/**
	 * Stops all of the drive motors
	 */
	public void stopAll() {
		leftmotor1.changeControlMode(TalonControlMode.PercentVbus);
		rightmotor1.changeControlMode(TalonControlMode.PercentVbus);
		leftmotor1.set(0.0);
		rightmotor1.set(0.0);
	}
	
	/**
	 * Starts a command that looks for control input based on a given controls mode (tank or arcade).
	 */
	@Override
	protected void initDefaultCommand() {
		if(Constants.DRIVE_MODE == DriveMode.ARCADE) {
			setDefaultCommand(new ArcadeDrive());
		} else {
			setDefaultCommand(new TankDrive());
		}
	}
}