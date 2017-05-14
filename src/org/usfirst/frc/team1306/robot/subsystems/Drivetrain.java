package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls drivetrain motors with the joysticks from OI.java, and can limit intake speed
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
//	}
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
		master.set(0.0);
		master.enable();
		
		slave.changeControlMode(TalonControlMode.Follower);
		slave.set(master.getDeviceID());
		slave.enable();
		
		//Setting up Encoder for Left Drivetrain
		leftmotor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftmotor1.reverseSensor(false);
		leftmotor1.reverseOutput(false);
		leftmotor1.configEncoderCodesPerRev(256);
		leftmotor1.configNominalOutputVoltage(+0.0f, -0.0f);
		leftmotor1.configPeakOutputVoltage(+12.0f, -12.0f);
		
		//Setting up Encoder for Right Drivetrain
		rightmotor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightmotor1.reverseSensor(true);
		rightmotor1.reverseOutput(true);
		rightmotor1.configEncoderCodesPerRev(256);
		rightmotor1.configNominalOutputVoltage(+0.0f, -0.0f);
		rightmotor1.configPeakOutputVoltage(+12.0f, -12.0f);

		leftmotor1.setMotionMagicCruiseVelocity(730*0.75);
		leftmotor1.setMotionMagicAcceleration(730*0.75);
		rightmotor1.setMotionMagicCruiseVelocity(730*0.75);
		rightmotor1.setMotionMagicAcceleration(730*0.75);
		
		//Setting up PIDF for Left Drivetrain
		leftmotor1.setF(Constants.F);	
		leftmotor1.setP(0.85); //0.11	
		leftmotor1.setI(Constants.I);	
		leftmotor1.setD(0);
		
      //Setting up PIDF for Right Drivetrain
		rightmotor1.setF(Constants.F);	
		rightmotor1.setP(0.85);	
		rightmotor1.setI(Constants.I);	
		rightmotor1.setD(0);
	}
	
	/**
	 * Takes 2 desired speeds to run the drive motors at
	 * 
	 * @param leftVal
	 * 		Speed for the left side (from -1 to 1)
	 * @param rightVal
	 * 		Speed for the right side (from -1 to 1)
	 */
	public void tankDrive(double leftVal, double rightVal) {
		leftmotor1.changeControlMode(TalonControlMode.PercentVbus);
		rightmotor1.changeControlMode(TalonControlMode.PercentVbus);

		if(Constants.DRIVETRAIN_ENABLED) {
			leftmotor1.set(leftVal*Constants.SPEED_MODIFIER);
			rightmotor1.set(-rightVal*Constants.SPEED_MODIFIER);
		}
	}
	
	/**
	 * Takes a desired speed to run both drive motors at and tries to maintain that with PID
	 * @param initVel
	 * 		Speed for both sides to match
	 */
	public void drivePID(double leftVel, double rightVel) {
		leftmotor1.changeControlMode(TalonControlMode.Speed);
		rightmotor1.changeControlMode(TalonControlMode.Speed);
		
		if(Constants.DRIVETRAIN_ENABLED) {
			leftmotor1.set(leftVel);
			rightmotor1.set(-rightVel);
		}
	}

	public void driveDistance(double feet) {
		leftmotor1.changeControlMode(TalonControlMode.MotionMagic);
		rightmotor1.changeControlMode(TalonControlMode.MotionMagic);
		
		if(Constants.DRIVETRAIN_ENABLED) {
//			leftmotor1.set((feet*12)/(4*Math.PI));
//			rightmotor1.set(-((feet*12)/(4*Math.PI)));a
			resetEncoders();
			SmartDashboard.putString("moving robot", "teset");
			
			if(feet == 3) {
//				if(alliance.equals(Alliance.Red)) {
//					leftmotor1.set(1.58); //1.68
//					rightmotor1.set(-1.58); //1.68
//				} else {
//					leftmotor1.set(-1.6); //1.68
//					rightmotor1.set(1.6); //1.68
//				}
				
				
				leftmotor1.set(1.58);
				rightmotor1.set(-1.58);
				
//				leftmotor1.set(-1.58);
//				rightmotor1.set(1.58);
				
			} else if(feet == 4) {
				leftmotor1.set(5.5);
				rightmotor1.set(5.5);
			} else {
				leftmotor1.set(feet);
				rightmotor1.set(feet);
			}
		}
	}
	
	public double inError() {
		return leftmotor1.getClosedLoopError();
	}
	
	public void resetGyro() {
		//ahrs.reset();
		//initAngle = ahrs.getYaw();
	}
	
	public double getGyro() {
		return 0;//ahrs.getYaw();
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
	public int getLeftPosition() {
		return leftmotor1.getEncPosition();
	}
	
	/**
	 * Returns the position of the right encoder
	 */
	public int getRightPosition() {
		return rightmotor1.getEncPosition();
	}
	
	public double getLeftVel() {
		return leftmotor1.getEncVelocity();
	}
	
	public double getRightVel() {
		return rightmotor1.getEncVelocity();
	}
	
	/**
	 * Stops all of the drive motors
	 */
//	public void stopAll() {
//		for (int i = 0; i < motors.length; i++) {
//			motors[i].set(0.0);
//		}
//	}

	public void stopAll() {
		leftmotor1.changeControlMode(TalonControlMode.PercentVbus);
		rightmotor1.changeControlMode(TalonControlMode.PercentVbus);
		leftmotor1.set(0.0);
		rightmotor1.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		if (Constants.DRIVE_MODE == DriveMode.TANK) {				//If mode is tank, set default command to tankdrive
			setDefaultCommand(new TankDrive());
		} else if (Constants.DRIVE_MODE == DriveMode.ARCADE) {		//If mode is arcade, set default command to arcadedrive
			setDefaultCommand(new ArcadeDrive());
		} else {
			setDefaultCommand(new TankDrive()); 					//Defaults to tankdrive
		}
	}
}