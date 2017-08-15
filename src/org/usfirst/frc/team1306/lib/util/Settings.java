package org.usfirst.frc.team1306.lib.util;

import java.util.ArrayList;

import org.usfirst.frc.team1306.robot.subsystems.Gyro;

import com.ctre.CANTalon;

/**
 * @Settings
 * 
 * This class manages and stores all the drivetrain settings. It's purpose is so you can modularly add the 
 * gyro, encoders, and new talons with ease. This makes changing out code/electrical components a lot faster 
 * on the fly and keeps everything organized.
 * 
 * @author Jackson Goth
 */
public class Settings {

	public Gyro gyro;
	public ArrayList<CANTalon> leftSide, rightSide; //Talons on each side of drivetrain
	public boolean encodersPresent = false; //If we need to initialize encoders
	public DriveMode driveMode; //ArcadeDrive, TankDrive, OutreachDrive?
	
	public Settings() {
		leftSide = new ArrayList<CANTalon>();
		rightSide = new ArrayList<CANTalon>();
		
		driveMode = DriveMode.ARCADE; //Default control mode is arcade.
	}
	
	/**
	 * Adds a new talon to a specified driveside with a specified type (master or slave)
	 */
	public void add(CANTalon talon, TalonType type) {
		if(type.equals(TalonType.LEFT_MASTER)) {
			leftSide.add(0,talon);
		} else if(type.equals(TalonType.LEFT_SLAVE)) {
			leftSide.add(talon);
		} else if(type.equals(TalonType.RIGHT_MASTER)) {
			rightSide.add(0,talon);
		} else if(type.equals(TalonType.RIGHT_SLAVE)) {
			rightSide.add(talon);
		}
	}
	
	/**
	 * Adds a new device to the drivetrain (gyro or encoders)
	 */
	public void add(Device device) {
		if(device.equals(Device.GYRO)) {
			gyro = new Gyro();
		} else if(device.equals(Device.ENCODER)) {
			encodersPresent = true;
		}
	}
	
	/**
	 * Sets the drivemode to a given drivemode (Arcade, Tank, Outreach)
	 */
	public void setDriveMode(DriveMode mode) {
		driveMode = mode;
	}
	
	public enum Device {GYRO, ENCODER}; //Device types
	
	public enum TalonType {LEFT_MASTER, RIGHT_MASTER, LEFT_SLAVE, RIGHT_SLAVE};
	
	public enum DriveMode {ARCADE, TANK_DRIVE, OUTREACH};
}
