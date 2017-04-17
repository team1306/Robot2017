package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.hood.HoodAngle;

/**
 * Enum that stores all the values necessary for each setpoint.
 * @author Jackson Goth
 */
public enum Setpoint {

	BOILER(Constants.SHOOTER_BOILER_RPM,Constants.INDEXER_BOILER_RPM,Constants.TURRET_BOILER_POS,HoodAngle.UP), //Pushed up against the boiler
	PEG(Constants.SHOOTER_PEG_RPM,Constants.INDEXER_PEG_RPM,Constants.TURRET_PEG_POS,HoodAngle.DOWN), //Right in front of the closest peg
	HOPPER(Constants.SHOOTER_HOPPER_RPM,Constants.INDEXER_HOPPER_RPM,Constants.TURRET_HOPPER_POS,HoodAngle.DOWN), //Pushed up against the close hopper
	AUTO_CLOSE(Constants.SHOOTER_AUTO_CLOSE_RPM,Constants.INDEXER_AUTO_CLOSE_RPM,Constants.TURRET_AUTO_CLOSE_POS,HoodAngle.DOWN), //Auto from boiler tape line
	AUTO_FAR(Constants.SHOOTER_AUTO_FAR_RPM,Constants.INDEXER_AUTO_FAR_RPM,Constants.TURRET_AUTO_FAR_POS,HoodAngle.DOWN); //Auto from in front of center peg
	
	private final double shooterSpeed;
	private final double indexerSpeed;
	private final double turretPosition;
	private final HoodAngle angle;
	
	private Setpoint(double shooterSpeed, double indexerSpeed, double turretPosition, HoodAngle angle) {
		this.shooterSpeed = shooterSpeed; //New shooter speed
		this.indexerSpeed = indexerSpeed; //New indexer speed
		this.turretPosition = turretPosition; //New turret position
		this.angle = angle; //New hood angle
	}
	
	/**
	 * Returns the necessary shooter speed of a certain setpoint.
	 */
	public double getShooterRPM() {
		return shooterSpeed;
	}
	
	/**
	 * Returns the necessary indexer speed of a certain setpoint.
	 */
	public double getIndexerRPM() {
		return indexerSpeed;
	}
	
	/**
	 * Returns the necessary turret position of a certain setpoint.
	 */
	public double getTurretPos() {
		return turretPosition * Constants.TURRET_GEAR_CONVERSION;
	}
	
	/**
	 * Returns the necessary hood angle of a certain setpoint.
	 */
	public HoodAngle getHoodAngle() {
		return angle;
	}
}
