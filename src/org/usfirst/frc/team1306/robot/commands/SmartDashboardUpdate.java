package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Periodically updates the SmartDashboard with new information
 * @author Jackson Goth
 */
public class SmartDashboardUpdate extends CommandBase {

	PowerDistributionPanel panel;
	NetworkTable table;
	
	public SmartDashboardUpdate() {
		requires(hood);
		requires(gyro);
		
		setRunWhenDisabled(true);
		
		panel = new PowerDistributionPanel();
		
		NetworkTable.setServerMode();
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		table = NetworkTable.getTable("1306");
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		//Subsystem Positions
		SmartDashboard.putString("Hood Position",hood.getName());
		SmartDashboard.putNumber("SD-Turret Position",turret.getEncPos());
		SmartDashboard.putNumber("SD-DLeftPosition",drivetrain.getLeftPosition());
		SmartDashboard.putNumber("SD-DRightPosition",drivetrain.getRightPosition());
		SmartDashboard.putNumber("SD-GyroAngle",gyro.getAngle());
		SmartDashboard.putNumber("SD-temp",gyro.getTemp());
		SmartDashboard.putNumber("SD-sampleTime",gyro.getLastSampleTime());
		
		SmartDashboard.putNumber("Yaw",table.getNumber("yaw",0));
		
		//Subsystem Current Draws
//		SmartDashboard.putNumber("Hopper Draw",panel.getCurrent(2));
//		SmartDashboard.putNumber("Indexers Draw",panel.getCurrent(4));
//		SmartDashboard.putNumber("Shooter L Draw",panel.getCurrent(7));
//		SmartDashboard.putNumber("Shooter R Draw",panel.getCurrent(5));
//		SmartDashboard.putNumber("Intake Draw",panel.getCurrent(1));
//		SmartDashboard.putNumber("Turret Draw",panel.getCurrent(6));
		SmartDashboard.putNumber("Climber Draw",panel.getCurrent(0) + panel.getCurrent(3));

		SmartDashboard.putNumber("Drive Draw12", panel.getCurrent(12));	//these are drive motors
		SmartDashboard.putNumber("Drive Draw13", panel.getCurrent(13));
		SmartDashboard.putNumber("Drive Draw14", panel.getCurrent(14));
		SmartDashboard.putNumber("Drive Draw15", panel.getCurrent(15));

		SmartDashboard.putBoolean("Browning Out?: ",HAL.getBrownedOut());
		
		SmartDashboard.putNumber("Drive Left Speed",drivetrain.getLeftVel());
		SmartDashboard.putNumber("Drive Right Speed",drivetrain.getRightVel());
		
		//Shooting Velocities
		SmartDashboard.putNumber("SD-LShooterVel",Math.abs(shooter.getVel(0)));
		SmartDashboard.putNumber("SD-RShooterVel",Math.abs(shooter.getVel(1)));
		SmartDashboard.putNumber("SD-IndexerVel",Math.abs(shooter.getVel(2)));
//		SmartDashboard.putNumber("Index error",Math.abs(shooter.getIndexError()));
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
