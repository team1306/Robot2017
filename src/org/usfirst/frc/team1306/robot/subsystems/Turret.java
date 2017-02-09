package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the shooter turrets
 * @author Sam Roquitte
 */
public class Turret extends Subsystem {
	private final CANTalon turretMotor;
	
	private int initPos = 0;		//Initial encoder position (centered turret)
	private int currentPos = 0;		//Current encoder position
	
	public Turret() {
		turretMotor = new CANTalon(RobotMap.TURRET_TALON_PORT);
		turretMotor.enable();
		turretMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		turretMotor.reverseSensor(false);
		turretMotor.configEncoderCodesPerRev(1024);
		turretMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		turretMotor.configPeakOutputVoltage(+12.0f, -12.0f);
		turretMotor.setProfile(0);
		
		//initPos = turretMotor.getEncPosition();
	}
	
	/**
	 * Sets the speed of the turret turn to the passed speed
	 * @param speed
	 * 		Speed to turn (-1.0 to 1.0)  1=right -1=left
	 */
	public void set(double speed) {
		if(Constants.TURRET_ENABLED) {
			SmartDashboard.putNumber("Turret Pos",getPos());
			
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(speed);
		}
	}
	
	/**
	 * Uses PID to turn the turret to a certain position
	 * @param position
	 * 		The position to go to
	 */
	public void setPosition(int position) {
		if(Constants.TURRET_ENABLED) {
			SmartDashboard.putNumber("Turret Pos",getPos());
			
			turretMotor.changeControlMode(TalonControlMode.Position);
			turretMotor.enableBrakeMode(true);
			turretMotor.setPosition(position * 35);
		}
	}
	
	public double getPos() {
		return turretMotor.getEncPosition();
	}
	/**
	 * Stops the turret turn motor
	 */
	public void stopAll() {
		turretMotor.set(0.0);
	}
	
	protected void initDefaultCommand() {
		
	}
}
