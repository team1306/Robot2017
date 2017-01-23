package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.rightStraight;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDRightDrive extends PIDSubsystem {

	private final CANTalon rightmotor;
	
	public PIDRightDrive() {
		super("PIDRight",Constants.P,Constants.I,Constants.D);
		
		setAbsoluteTolerance(Constants.PIDTolerance);
		setOutputRange(0,1);
		
		rightmotor = new CANTalon(RobotMap.RIGHT_TALON_1_PORT);
	}
	
	@Override
	protected double returnPIDInput() {
		
		return rightmotor.getEncVelocity();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("rightput",output);
		rightmotor.changeControlMode(TalonControlMode.PercentVbus);
		rightmotor.set(output);
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new rightStraight());
	}

	public void stopAll() {
		rightmotor.set(0.0);
	}

}