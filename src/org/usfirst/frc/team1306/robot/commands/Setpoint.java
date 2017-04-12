package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.hood.HoodAngle;

public enum Setpoint {

	BOILER(Constants.SHOOTER_BOILER_RPM,Constants.INDEXER_BOILER_RPM,Constants.TURRET_BOILER_POS,HoodAngle.UP),
	PEG(Constants.SHOOTER_PEG_RPM,Constants.INDEXER_PEG_RPM,Constants.TURRET_PEG_POS,HoodAngle.DOWN),
	HOPPER(Constants.SHOOTER_HOPPER_RPM,Constants.INDEXER_HOPPER_RPM,Constants.TURRET_HOPPER_POS,HoodAngle.DOWN),
	AUTO_CLOSE(Constants.SHOOTER_AUTO_CLOSE_RPM,Constants.INDEXER_AUTO_CLOSE_RPM,Constants.TURRET_AUTO_CLOSE_POS,HoodAngle.DOWN),
	AUTO_FAR(Constants.SHOOTER_AUTO_FAR_RPM,Constants.INDEXER_AUTO_FAR_RPM,Constants.TURRET_AUTO_FAR_POS,HoodAngle.DOWN);
	
	private final double shooterSpeed;
	private final double indexerSpeed;
	private final double turretPosition;
	private final HoodAngle angle;
	
	private Setpoint(double shooterSpeed, double indexerSpeed, double turretPosition, HoodAngle angle) {
		this.shooterSpeed = shooterSpeed;
		this.indexerSpeed = indexerSpeed;
		this.turretPosition = turretPosition;
		this.angle = angle;
	}
	
	public double getShooterRPM() {
		return shooterSpeed;
	}
	
	public double getIndexerRPM() {
		return indexerSpeed;
	}
	
	public double getTurretPos() {
		return turretPosition * Constants.TURRET_GEAR_CONVERSION;
	}
	
	public HoodAngle getHoodAngle() {
		return angle;
	}
}
