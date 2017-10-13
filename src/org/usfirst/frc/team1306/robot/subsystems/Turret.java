package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.turret.AutoTurret;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @Turret
 * 
 * TODO Write better description...
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public class Turret extends Subsystem {

	private final CANTalon turretMotor;
	
	public Turret() {
		turretMotor = new CANTalon(RobotMap.TURRET_TALON_PORT);
		turretMotor.enable();
		
		turretMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		turretMotor.reverseSensor(false);
		turretMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		turretMotor.configPeakOutputVoltage(+12.0f, -12.0f);

		turretMotor.setF(0.515); //Max Velocity = 291.2RPM 1023/1988 (1988 mag encoder native units in web interface)
		turretMotor.setP(5.456); //0.341 was starting P value
		turretMotor.setI(0.01);
		turretMotor.setD(0);
		turretMotor.setMotionMagicCruiseVelocity(218.4);	// 218 75% of max velocity, may need to be adjusted later
		turretMotor.setMotionMagicAcceleration(218.4);		// 218 1 sec speed up time
	}
	
	/**
	 * Returns encoder position
	 */
	public double getEncPos() {
		return turretMotor.getPulseWidthPosition();
	}
	
	/**
	 * Returns current position as a fraction (-1 to 1, -1 being one negative rotation and 1 being one positive rotation)
	 */
	public double getPosition() {	
		return ((Constants.TURRET_START_POS - getEncPos()) / 4096) / Constants.TURRET_GEAR_CONVERSION;
	}
	
	/**
	 * Returns current position in degrees
	 */
	public double getDegPosition() {
		return (getPosition()*(0.08789))/Constants.TURRET_GEAR_CONVERSION;	// 360/4096
	}
	
	/**
	 * Sets speed to a PercentVbus speed
	 */
	public void setSpeed(double speed) {
		if(Constants.TURRET_ENABLED) {
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(speed);
		}
	}
	
	/**
	 * Sets speed to a certain RPM
	 */
	public void setRPM(double rpm) {
		if(Constants.TURRET_ENABLED) {
			turretMotor.changeControlMode(TalonControlMode.Speed);
			turretMotor.set(rpm);
		}
	}
	
	
	/**
	 * Turns turret to a given rotation
	 */
	public void moveRot(double rotation) {
		if (Constants.TURRET_ENABLED) {
			turretMotor.changeControlMode(TalonControlMode.MotionMagic);
			turretMotor.set(rotation);
		}
	}
	
	/**
	 * Turns turret to a given degree
	 */
	public void moveDeg(double degrees) {
		if (Constants.TURRET_ENABLED) {
			turretMotor.changeControlMode(TalonControlMode.MotionMagic);
			turretMotor.set((degrees/360)*Constants.TURRET_GEAR_CONVERSION);
		}
	}
	
	public void stop() {
		turretMotor.changeControlMode(TalonControlMode.PercentVbus);
		turretMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AutoTurret());
	}
}
