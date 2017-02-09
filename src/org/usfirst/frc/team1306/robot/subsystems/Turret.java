package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.shooter.BangSpinShooter;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the shooter turrets
 * @author Sam Roquitte
 */
public class Turret {
	private final CANTalon turretMotor;
	
	public final static double turretTurnSpeed = Constants.TURRET_TURN_SPEED;
	
	public Turret() {
		turretMotor = new CANTalon(RobotMap.TURRET_TALON_PORT);
		turretMotor.enable();
	}
	
	/**
	 * Method that spins up shooter
	 */
	public void set(double speed) {
		if(Constants.TURRET_ENABLED) {
			turretMotor.set(speed);
		}
	}
	
	/**
	 * Stops the shooter motors
	 */
	public void stopAll() {
		turretMotor.set(0.0);
	}
	
	protected void initDefaultCommand() {
		//setDefaultCommand(new BangSpinShooter());
	}
}
