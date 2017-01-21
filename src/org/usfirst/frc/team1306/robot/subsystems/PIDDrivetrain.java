package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveStraight;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PIDDrivetrain extends PIDSubsystem {

	private final CANTalon leftmotor1;
	private final CANTalon rightmotor1;
	
	public PIDDrivetrain() {
		super(Constants.P, Constants.I, Constants.D);
		
		setAbsoluteTolerance(Constants.PIDTolerance);
		setOutputRange(-1.0,1.0);
		
		leftmotor1 = new CANTalon(RobotMap.LEFT_TALON_1_PORT);
		rightmotor1 = new CANTalon(RobotMap.RIGHT_TALON_1_PORT);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return rightmotor1.getEncVelocity() - leftmotor1.getEncVelocity();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		double input = .5/*OI.getTriggerVal(controller.p, trigger.r)*/;
		double leftput = 0;
		
		if(Constants.PID_DRIVETRAIN_ENABLED) {
			if(output + input > 1) {
				leftput = 1;
			} else if(output + input < -1) {
				leftput = -1;
			} else {
				leftput = output + input;
			}
			leftmotor1.set(leftput);
			rightmotor1.set(input);
		}
	}
	
	/**
	 * Don't need this command
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveStraight());
		
	}

	public void stopAll() {
		leftmotor1.set(0.0);
		rightmotor1.set(0.0);
	}

}
