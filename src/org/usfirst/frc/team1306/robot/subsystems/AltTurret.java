package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.TankDrive;
import org.usfirst.frc.team1306.robot.vision.GetData;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AltTurret extends PIDSubsystem {

	private final CANTalon turretMotor;
	
	public AltTurret() {
		super("Turret PID",Constants.TURRET_P,Constants.TURRET_I,Constants.TURRET_D);
		
		setAbsoluteTolerance(2);
		setOutputRange(-0.05,0.05);
		
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
		turretMotor.enableBrakeMode(true);
		turretMotor.changeControlMode(TalonControlMode.PercentVbus);
		turretMotor.enable();
		
		setSetpoint(0.0);
		
	}//-1140 -320 460
	
	public double getEncPos() {
		return turretMotor.getEncPosition();
	}
	
	public void resetEncoder() {
		SmartDashboard.putString("resetting encoder","true");
		turretMotor.reset();
		turretMotor.enable();
	}
	
	public double getPos() {
		return turretMotor.getPosition();
	}
	
	public double getAnalogPos() {
		return turretMotor.getAnalogInPosition();
	}
	
	//-840
	//-43
	public void setPosition(double setpoint) {
		if(Constants.TURRET_ENABLED) {
			getPIDController().reset();
			SmartDashboard.putNumber("Position",getPos());
			turretMotor.changeControlMode(TalonControlMode.MotionMagic);
			turretMotor.set(setpoint);
			turretMotor.enable();
		}
	}
	
	public void set(double speed) {
		if(Constants.TURRET_ENABLED) {
			//turretMotor.enable();
			SmartDashboard.putNumber("Position",getEncPos());
			SmartDashboard.putNumber("SetSpeed",speed);
			SmartDashboard.putNumber("ActualSpeed",turretMotor.getEncVelocity());
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(speed);
		}
	}
	
	@Override
	protected double returnPIDInput() {
		SmartDashboard.putNumber("setpoint error",turretMotor.getSetpoint() - turretMotor.getEncPosition());
		//return turretMotor.getSetpoint() - turretMotor.getEncPosition();
		return turretMotor.getEncPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		if(Constants.TURRET_ENABLED) {
			SmartDashboard.putNumber("Position",getPos());
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(output);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new GetData());
	}
}
