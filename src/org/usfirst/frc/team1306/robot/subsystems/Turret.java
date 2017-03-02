package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that controls the turret and sets it to differen't positions
 * @author Jackson Goth
 */
public class Turret extends Subsystem {

	private final CANTalon turretMotor;
	
	public Turret() {
		turretMotor = new CANTalon(RobotMap.TURRET_TALON_PORT);
		turretMotor.enable();
	}
	
	/**
	 * Returns the encoder position (abs mode)
	 * @return
	 * 		Encoder position
	 */
	public double getEncPos() {
		return turretMotor.getPulseWidthPosition();
		//return turretMotor.get;
	}
	
	/**
	 * Resets the encoder
	 */
	public void resetEncoder() {
		turretMotor.setPosition(0);
	}
	
	/**
	 * Turns the turret with a given speed
	 * @param speed
	 */
	public void setSpeed(double speed) {
		if(Constants.TURRET_ENABLED) {
			SmartDashboard.putBoolean("Setting Turret Speed: ",true);
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(speed);
		}
	}
	
	/**
	 * Stops the turret
	 */
	public void stopAll() {
		turretMotor.set(Constants.SPEED_ZERO);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
