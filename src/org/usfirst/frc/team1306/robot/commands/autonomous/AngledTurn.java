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

	private double degree;
	private final double tolerance = 2;
	AHRS ahrs;
	
	public AngledTurn(double degree) {
		requires(drivetrain);
		this.degree = degree;
		try {
			ahrs = new AHRS(SPI.Port.kMXP); 
		} catch(RuntimeException ex) {
			
		}
	}

	@Override
	protected void initialize() {
		ahrs.reset();
	}

	@Override
	protected void execute() {
		
		SmartDashboard.putNumber("Gyro Direction",ahrs.getAngle());
		
		if(ahrs.getAngle() < degree) {
			drivetrain.tankDrive(0.2, -0.2);
		} else {
			drivetrain.tankDrive(-0.2, 0.2);
		}
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(degree - ahrs.getAngle()) < tolerance) {
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
