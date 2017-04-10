package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.hood.HoodAngle;

public enum Setpoint {

	BOILER(Constants.SHOOTER_BOILER_RPM,Constants.INDEXER_BOILER_RPM,Constants.TURRET_BOILER_POS,HoodAngle.UP),
	PEG(Constants.SHOOTER_PEG_RPM,Constants.INDEXER_PEG_RPM,Constants.TURRET_PEG_POS,HoodAngle.DOWN),
	HOPPER(Constants.SHOOTER_HOPPER_RPM,Constants.INDEXER_HOPPER_RPM,Constants.TURRET_HOPPER_POS,HoodAngle.DOWN);
	
	private final double shooterSpeed;
	private final double indexerSpeed;
	private final double turretPosition;
	private final HoodAngle angle;
	
	private Setpoint(double shooterSpeed, double indexerSpeed, double turretPosition, HoodAngle angle) {
		this.shooterSpeed = shooterSpeed;
		this.indexerSpeed = shooterSpeed;
		this.turretPosition = shooterSpeed;
		this.angle = angle;
	}
	
}
