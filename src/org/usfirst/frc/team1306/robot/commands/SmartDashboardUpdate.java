package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.vision.GetData;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Periodically updates the SmartDashboard with new information
 * 
 * @author Jackson Goth
 */
public class SmartDashboardUpdate extends CommandBase {

	PowerDistributionPanel panel;
	NetworkTable table;
	
	public SmartDashboardUpdate() {
		requires(hood);
		setRunWhenDisabled(true);
		panel = new PowerDistributionPanel();
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		SmartDashboard.putString("Hood Position",hood.getPos());
		
		SmartDashboard.putNumber("Turret Position",turret.getPos());
		
		SmartDashboard.putNumber("Hopper Draw",panel.getCurrent(2));
		SmartDashboard.putNumber("Indexers Draw",panel.getCurrent(4));
		SmartDashboard.putNumber("Shooter L Draw",panel.getCurrent(7));
		SmartDashboard.putNumber("Shooter R Draw",panel.getCurrent(5));
		SmartDashboard.putNumber("Intake Draw",panel.getCurrent(1));
		SmartDashboard.putNumber("Turret Draw",panel.getCurrent(6));
		SmartDashboard.putNumber("Climber Draw",panel.getCurrent(0));
		
		SmartDashboard.putNumber("LShooterVel",shooter.getVel(0));
		SmartDashboard.putNumber("RShooterVel",shooter.getVel(1));
		SmartDashboard.putNumber("IndexerVel",shooter.getVel(2));
		
		SmartDashboard.putNumber("TURRET GET POS", turret.getPos());
		SmartDashboard.putNumber("TURRET GET ENCPOS", turret.getEncPos());
		SmartDashboard.putNumber("TURRET GET ANALOGPOS", turret.getAnalogPos());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
