package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.shooter.AdjustHood.HoodAngle;

/**
 * @Setpoint
 * 
 * Enum that stores all the information about each setpoint. Shooter/indexer speeds, rpm adjustment for differing shooting distances (2 shooters),
 * rotation the turret should turn to, and if the hood should be actuated or not
 * 
 * @author Jackson Goth
 */
public enum Setpoint {

	BOILER(Constants.SHOOTER_BOILER_RPM,Constants.INDEXER_BOILER_RPM,Constants.SHOOTER_BOILER_ADJ,Constants.TURRET_BOILER_POS,HoodAngle.UP), //Pushed up against the boiler
	PEG(Constants.SHOOTER_PEG_RPM,Constants.INDEXER_PEG_RPM,Constants.SHOOTER_PEG_ADJ,Constants.TURRET_PEG_POS,HoodAngle.DOWN), //Right in front of the closest peg
	AUTO_HOPPER(Constants.SHOOTER_AUTO_HOPPER_RPM,Constants.INDEXER_AUTO_HOPPER_RPM,Constants.SHOOTER_AUTO_HOPPER_ADJ,Constants.TURRET_AUTO_HOPPER_POS,HoodAngle.DOWN); //Auto from in front of center peg
	
	public final double shooterSpeed;
	public final double indexerSpeed;
	public final double shooterAdj;
	private final double turretPosition;
	public final HoodAngle angle;
	
	private Setpoint(double shooterSpeed, double indexerSpeed, double shooterAdj, double turretPosition, HoodAngle angle) {
		this.shooterSpeed = shooterSpeed; 
		this.indexerSpeed = indexerSpeed; 
		this.shooterAdj = shooterAdj;
		this.turretPosition = turretPosition; 
		this.angle = angle; 
	}
	
	/**
	 * The turret rotation you give setpoint is in terms of the shooter gears which are larger,
	 * so a conversion must be applied to get rotation in terms of middle gear.
	 */
	public double getTurretRot() {
		return turretPosition * Constants.TURRET_GEAR_CONVERSION;
	}
}
