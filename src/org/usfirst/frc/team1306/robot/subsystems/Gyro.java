package org.usfirst.frc.team1306.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Gyro extends Subsystem {

	AnalogDevicesGyro imu;
	
	public Gyro() {
		imu = new AnalogDevicesGyro();
//		imu.reset();
	}

	public void reset() {
		imu.reset();
	}
	
	public double getAngle() {
		return imu.getAngle();
	}
	
	public double getTemp() {
		return imu.getTemperature();
	}
	
	public double getLastSampleTime() {
		return imu.getLastSampleTime();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
