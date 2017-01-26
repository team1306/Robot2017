package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the left side of drivetrain with PID
 * @author Jackson Goth
 */
public class PIDRightDrive extends PIDSubsystem {

	private final CANTalon rightmotor1;
	private final CANTalon rightmotor2;
	
	/*
	 * Sets up PID variables and motorcontrollers
	 */
	public PIDRightDrive() {
		super("PIDRight",Constants.P,Constants.I,Constants.D);
		
		setAbsoluteTolerance(Constants.PIDTolerance);
		setOutputRange(0,1);
		
		rightmotor1 = new CANTalon(RobotMap.RIGHT_TALON_1_PORT);
		rightmotor2 = new CANTalon(RobotMap.RIGHT_TALON_2_PORT);
		
		rightmotor1.changeControlMode(TalonControlMode.Speed);
		rightmotor1.set(0.0);
		rightmotor1.enable();
		
		rightmotor2.changeControlMode(TalonControlMode.Follower);
		rightmotor2.set(rightmotor1.getDeviceID());
		rightmotor2.enable();
	}
	
	@Override
	protected double returnPIDInput() {
		
		return rightmotor1.getEncVelocity();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("rightput",output);
		rightmotor1.changeControlMode(TalonControlMode.Speed);
		rightmotor1.set(output);
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new RightStraightDrive());
	}

	public void stopAll() {
		rightmotor1.set(0.0);
	}

}