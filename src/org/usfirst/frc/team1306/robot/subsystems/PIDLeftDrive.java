package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.LeftStraightDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the left side of drivetrain with PID
 * @author Jackson Goth
 */
public class PIDLeftDrive extends PIDSubsystem {

	private final CANTalon leftmotor1;
	private final CANTalon leftmotor2;
	
	/*
	 * Sets up PID variables and motorcontrollers
	 */
	public PIDLeftDrive() {
		super("PIDLeft",Constants.P,Constants.I,Constants.D);
		
		setAbsoluteTolerance(Constants.PIDTolerance);
		setOutputRange(0,900);
		
		leftmotor1 = new CANTalon(RobotMap.LEFT_TALON_1_PORT);
		leftmotor2 = new CANTalon(RobotMap.LEFT_TALON_2_PORT);
		
		leftmotor1.changeControlMode(TalonControlMode.Speed);
		leftmotor1.set(0.0);
		leftmotor1.enable();
		
		leftmotor2.changeControlMode(TalonControlMode.Follower);
		leftmotor2.set(leftmotor1.getDeviceID());
		leftmotor2.enable();
	}
	
	@Override
	protected double returnPIDInput() {
		
		return leftmotor1.getEncVelocity();
	}

	@Override
	protected void usePIDOutput(double output) {
		
		SmartDashboard.putNumber("leftput",output);
		if(Constants.PID_DRIVETRAIN_ENABLED) {
			leftmotor1.changeControlMode(TalonControlMode.Speed);
			leftmotor1.set(output);
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LeftStraightDrive());
	}

	public void stopAll() {
		leftmotor1.set(0.0);
	}

}
