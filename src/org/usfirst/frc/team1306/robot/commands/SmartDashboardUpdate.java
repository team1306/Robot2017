package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Periodically updates the SmartDashboard with new information
 * @author Jackson Goth
 */
public class SmartDashboardUpdate extends CommandBase {

	PowerDistributionPanel panel;
	
	public SmartDashboardUpdate() {
		setRunWhenDisabled(true);
		
		panel = new PowerDistributionPanel(); //Initializes PDP
	}
	
	@Override
	protected void execute() {
		
		SmartDashboard.putNumber("TurretPos",turret.getEncPos());
		SmartDashboard.putNumber("VisionYaw",vision.getYaw());
		SmartDashboard.putNumber("VisionDist",vision.getDist());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
