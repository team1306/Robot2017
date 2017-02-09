package org.usfirst.frc.team1306.robot.commands.gearmech;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that drives robot to correct distance from wall to receive gear
 * @author Valued Customer
 */
public class ReverseDrive extends CommandBase {

	public ReverseDrive() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		drivetrain.resetEncoders();
	}

	@Override
	protected void execute() {
		drivetrain.tankDrive(-0.5, -0.5);
		SmartDashboard.putNumber("Left Encoder Pos",drivetrain.getLeftPosition());
		SmartDashboard.putNumber("Right Encoder Pos",drivetrain.getRightPosition());
	}
	
	@Override
	protected boolean isFinished() {
		if(drivetrain.getLeftPosition() < 652.8) { //TODO Make include right pos as well
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}

}
