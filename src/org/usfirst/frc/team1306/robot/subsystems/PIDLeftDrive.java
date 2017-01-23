package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.leftStraight;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDLeftDrive extends PIDSubsystem {

	private final CANTalon leftmotor;
	
	public PIDLeftDrive() {
		super("PIDLeft",Constants.P,Constants.I,Constants.D);
		
		setAbsoluteTolerance(Constants.PIDTolerance);
		setOutputRange(0,1);
		
		leftmotor = new CANTalon(RobotMap.LEFT_TALON_1_PORT);
	}
	
	@Override
	protected double returnPIDInput() {
		
		return leftmotor.getEncVelocity();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("leftput",output);
		leftmotor.changeControlMode(TalonControlMode.PercentVbus);
		leftmotor.set(output);
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new leftStraight());
	}

	public void stopAll() {
		leftmotor.set(0.0);
	}

}
