package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that turns the drivetrain to a give degree
 * @author Jackson Goth
 */
public class AngledTurn extends CommandBase {

	private double initDegree;
	private double degree;
	private final double tolerance = 1;

	
	public AngledTurn(double degree) {
		requires(drivetrain);
		this.degree = degree;
	}

	@Override
	protected void initialize() {
		initDegree = drivetrain.initAngle;
	}

	@Override
	protected void execute() {
		
		if(drivetrain.getGyro() < initDegree + degree) {
			drivetrain.tankDrive(0.2, -0.2);
		} else {
			drivetrain.tankDrive(-0.2, 0.2);
		}
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(drivetrain.getGyro() - (initDegree + degree)) < tolerance) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
