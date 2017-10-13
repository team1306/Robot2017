package org.usfirst.frc.team1306.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/**
 * @Gyro
 * 
 * This class holds the gyro object and is meant make accessing data from the navx easier and more organized.
 * @author Jackson Goth
 */
public class Gyro {

	private AHRS navx; //Gyro we use, navX Sensor
	
	public Gyro() {
		try {
			navx = new AHRS(SPI.Port.kMXP);
			
			navx.reset(); //Resets Yaw
			navx.resetDisplacement(); //Resets displacement
		} catch(RuntimeException ex) {
			
		}
	}
	
	/**
	 * Returns total accumulated yaw value in degrees
	 */
	public double getAngle() {
		return navx.getAngle();
	}
	
	/**
	 * Returns current yaw value (-180 to 180 degrees only)
	 */
	public double getYaw() {
		return navx.getYaw();
	}
	
	/**
	 * Returns displacement from navx along a given axis
	 */
	public double getDisplacement(Axis axis) {
		if(axis.equals(Axis.X)) {
			return navx.getDisplacementX();
		} else if(axis.equals(Axis.Y)) {
			return navx.getDisplacementY();
		} else {
			return navx.getDisplacementZ();
		}
	}
	
	public enum Axis {X,Y,Z}; //Enum used to store possible axis for dislacement acquisition
}
