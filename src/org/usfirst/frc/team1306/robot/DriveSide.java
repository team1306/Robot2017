package org.usfirst.frc.team1306.robot;

import java.util.ArrayList;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/**
 * @DriveSide
 * 
 * This object contains one side of the drivetrain (either left or right), and it's purpose is to make adding/removing motor controllers
 * from the drivetrain subsystem much faster/easier in code. To adjust the number of motor controllers all you have to do is change a few lines of code
 * in CommandBase.java where the drivetrain settings are initialized. To make code in the drivetrain subsystem simpler, the methods of this class
 * are very similar to that of a motor controller with methods like 'set(motorSpeed)' and 'changeControlMode(controlMode)'.
 * 
 * @author Jackson Goth
 */
public class DriveSide {

	public CANTalon master; //Talon that is adjusted, and from which other talons imitate
	
	/**
	 * Makes a new DriveSide with a given array filled with the side's corresponding talons
	 */
	public DriveSide(ArrayList<CANTalon> t) {
		
		ArrayList<CANTalon> talons = new ArrayList<CANTalon>();
		talons = t;  //All talons for this side of the drivetrain
		
		if(talons.size() > 0) { //If there are any talons at all...
			
			master = talons.get(0); //First talon in array is the master talon
			
			master.changeControlMode(TalonControlMode.PercentVbus);
			master.set(0.0);
			master.enable();
			
			for(int i = 1; i < talons.size(); i++) { //Sets every other talon as a follower of the master talon
				talons.get(i).changeControlMode(TalonControlMode.Follower);
				talons.get(i).set(master.getDeviceID());
				talons.get(i).enable();
			}
		}
	}
	
	/**
	 * Changes the control mode of the master talon
	 */
	public void changeControlMode(TalonControlMode mode) {
		master.changeControlMode(mode);
	}
	
	/**
	 * Set's the output of the talons to a given speed
	 */
	public void set(double speed) {
		master.set(speed);
	}

	/**
	 * Initializes the drive encoders (Currently set-up to initialize Grayhill encoders)
	 */
	public void initEncoders() {
		
		master.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		master.configEncoderCodesPerRev(256);
		master.configNominalOutputVoltage(+0.0f, -0.0f);
		master.configPeakOutputVoltage(+12.0f, -12.0f);
		
		master.setEncPosition(0);
	}
	
	/**
	 * Sets up the PIDF control values
	 */
	public void setPIDParams(PIDParameters params) {
		master.setF(params.f);
		master.setP(params.p);
		master.setI(params.i);
		master.setD(params.d);
	}
	
	/**
	 * Sets up the Motion Magic control values
	 */
	public void setMotionMagicParams(double cruiseVelocity, double acceleration) {
		master.setMotionMagicCruiseVelocity(cruiseVelocity);
		master.setMotionMagicAcceleration(acceleration);
	}
	
	/**
	 * Reverses motor output from the loop output, (ex. if true a loop output of 1 would turn the motor at -1)
	 */
	public void flipLoopOutput(boolean flipped) {
		master.reverseOutput(flipped);
	}
	
	/**
	 * Reverses encoder output (ex. if true an encoder output of 200 would read -200)
	 */
	public void flipEncoderOutput(boolean flipped) {
		master.reverseSensor(flipped);
	}
	
	/**
	 * Returns the current position from the encoder
	 */
	public double getEncPos() {
		return master.getEncPosition();
	}
	
	/**
	 * Sets the encoder position to a given position (Generally used to reset the encoder output)
	 */
	public void setEncPos(int pos) {
		master.setEncPosition(pos);
	}
	
	/**
	 * Returns the current velocity from the encoder
	 */
	public double getEncVel() {
		return master.getEncVelocity();
	}
}
