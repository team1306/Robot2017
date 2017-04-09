package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.turret.FindTarget;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that controls movements of turret with motion magic to rotations or degrees.
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
		//turretMotor.reverseSensor(false);
		turretMotor.setProfile(0);
		turretMotor.setF(0.494);		//Max velocity=291.2RPM 1023/1988 (1988 mag encoder native units in web interface), new is 2071
		turretMotor.setP(0.210);		//0.341 was starting P value, doubled to get to current value  5.456
		turretMotor.setI(0);
		turretMotor.setD(0);
		turretMotor.setMotionMagicCruiseVelocity(303*0.75);	// 218 75% of max velocity, may need to be ajusted later
		turretMotor.setMotionMagicAcceleration((303*2)*0.75);		// 218 1 sec speed up time
		
		turretMotor.configNominalOutputVoltage(2.75, -2.75);
		turretMotor.setAllowableClosedLoopErr(10);
	}
	
	/**
	 * Returns the encoder position (abs mode)
	 * @return
	 * 		Encoder position
	 */
	public double getEncPos() {
		return turretMotor.getPulseWidthPosition();
	}
	
	public double getEncVel() {
		return turretMotor.getEncVelocity();
	}
	
	/**
	 * Returns the degree position
	 * @return
	 * 		Degree measurement
	 */
	public double getPosition() {	
		return ((Constants.TURRET_START_POS - getEncPos()) / 4096) / Constants.TURRET_GEAR_CONVERSION; //TODO Needs Testing
	}
	
	/**
	 * Returns the degree position of the turret
	 * @return
	 * 		Non motor, actual thingamabob that you want probably dood
	 */
	public double getDegPosition() {
		return (getPosition()*(0.08789))/Constants.TURRET_GEAR_CONVERSION;	// 360/4096
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
	 * 		Percent voltage speed
	 */
	public void setSpeed(double speed) {
		if(Constants.TURRET_ENABLED) {
			SmartDashboard.putBoolean("Setting Turret Speed: ",true);
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(speed);
		}
	}
	
	/**
	 * Turns the turret to a certain rotation measurement
	 * @param rotation
	 * 		Rotation measurement to turn to
	 */
	public void moveRot(double rotation) {
		if (Constants.TURRET_ENABLED) {
			turretMotor.changeControlMode(TalonControlMode.MotionMagic);
			SmartDashboard.putNumber("Turret Rotation: ",rotation);
			turretMotor.set(rotation);
		}
	}
	
	public double getOutputVoltage() {
		return turretMotor.getOutputVoltage();
	}
	
	public double getError() {
		return turretMotor.getError();
	}
	
	/**
	 * Turns the turret to a certain degree measurement
	 * @param degrees
	 * 		Degree measurement to turn to
	 */
	public void moveDeg(double degrees) {
		if (Constants.TURRET_ENABLED) {
			turretMotor.changeControlMode(TalonControlMode.MotionMagic);
			SmartDashboard.putNumber("Turret Rotation: ",((degrees/360)*Constants.TURRET_GEAR_CONVERSION));
			turretMotor.set((degrees/360)*Constants.TURRET_GEAR_CONVERSION);
		}
	}
	
	public void movePIDTune() {
		if (Constants.TURRET_ENABLED) {
			turretMotor.changeControlMode(TalonControlMode.MotionMagic);
			turretMotor.set(0.016*(Constants.TURRET_GEAR_CONVERSION));
		}
	}
	
	/**
	 * Stops the turret
	 */
	public void stopAll() {
		turretMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new FindTarget());
	}
}
