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
		setOutputRange(-0.6,0.6);
		
		turretMotor = new CANTalon(RobotMap.TURRET_TALON_PORT);
		turretMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		turretMotor.enableBrakeMode(true);
		turretMotor.changeControlMode(TalonControlMode.PercentVbus);
		//turretMotor.enable();
		
		setSetpoint(0.0);
		//turretMotor.reset();
	}
	
	public double getEncPos() {
		return turretMotor.getEncPosition();
	}
	
	public double getPos() {
		return turretMotor.getPosition();
	}
	
	public double getAnalogPos() {
		return turretMotor.getAnalogInPosition();
	}
	
	public void setPosition(double setpoint) {
		if(Constants.TURRET_ENABLED) {
			getPIDController().reset();
			SmartDashboard.putNumber("Position",getPos());
			turretMotor.changeControlMode(TalonControlMode.Position);
			turretMotor.set(setpoint);
			turretMotor.enable();
		}
	}
	
	public void set(double speed) {
		if(Constants.TURRET_ENABLED) {
			//turretMotor.enable();
			SmartDashboard.putNumber("Position",getEncPos());
			SmartDashboard.putNumber("Speed",speed);
			turretMotor.changeControlMode(TalonControlMode.PercentVbus);
			turretMotor.set(speed);
		}
	}
	
	@Override
	protected double returnPIDInput() {
		
		return turretMotor.getPosition();
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
