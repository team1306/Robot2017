package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that controls the turret and sets it to differen't positions
 * @author Jackson Goth
 */
public class Turret extends Subsystem {

	private final CANTalon turretMotor;
	
	public Turret() {
		//super("Turret PID",Constants.TURRET_P,Constants.TURRET_I,Constants.TURRET_D);
		
		//setAbsoluteTolerance(2);
		//setOutputRange(-0.1,0.1);
		
		turretMotor = new CANTalon(RobotMap.TURRET_TALON_PORT);
		turretMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		/*turretMotor.setProfile(0);
		turretMotor.configNominalOutputVoltage(+0.0f,-0.0f);
		turretMotor.configPeakOutputVoltage(+12.0f,-12.0f);
		turretMotor.setF(0.0);
		turretMotor.setP(0.0);
		turretMotor.setI(0.0);
		turretMotor.setD(0.0);
		turretMotor.setMotionMagicAcceleration(0.0);
		turretMotor.setMotionMagicCruiseVelocity(0.0);*/
		turretMotor.changeControlMode(TalonControlMode.PercentVbus);
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
	 * Turns the turret to a given set-point
	 * @param setpoint
	 * 		Point to set turret to
	 */
//	public void setPosition(double setpoint) {
//		if(Constants.TURRET_ENABLED) {
//			//getPIDController().reset();
//			SmartDashboard.putNumber("Position",getEncPos());
//			turretMotor.changeControlMode(TalonControlMode.Position);
//			turretMotor.set(setpoint);
//		}
//	}
	
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
	
	/*@Override
	protected double returnPIDInput() {

		return getEncPos();
	}

	@Override
	protected void usePIDOutput(double output) {
		if(Constants.TURRET_ENABLED) {
			SmartDashboard.putNumber("PID-T Position",getEncPos());
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(output);
		}
	}*/
	
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
