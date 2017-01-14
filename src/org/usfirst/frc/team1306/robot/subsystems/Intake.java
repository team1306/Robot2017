package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.spinIntake;
import org.usfirst.frc.team1306.robot.commands.drivetrain.TankDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**this subsystem controls hood rollers
 * 
 * @author Jackson Goth
 *
 */

public class Intake extends Subsystem{

	private final Talon intakeMotor;

	public Intake() {
		intakeMotor = new Talon(0);
	}
	
	public void SpinIntake() {
		intakeMotor.set(.5);
	}
	
	public void stopMotor(int motor) {
		intakeMotor.set(0.0);
	}
	
	public void stopAll() {
		intakeMotor.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new spinIntake());
	}

}
