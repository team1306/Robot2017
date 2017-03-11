package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.turret.ScanDirection;

/**
 * Enum that contains what motion profiles should be run for a specified alliance station
 * @author Jackson Goth
 */
public enum Station {
	
	RED_ONE(Constants.MP_GEAR_RED_ONE,-1),
	RED_TWO(Constants.MP_GEAR_RED_TWO,-1),
	RED_THREE(Constants.MP_GEAR_RED_THREE,Constants.MP_HOPPER_RED),
	BLUE_ONE(Constants.MP_GEAR_BLUE_ONE,Constants.MP_HOPPER_BLUE),
	BLUE_TWO(Constants.MP_GEAR_BLUE_TWO,-1),
	BLUE_THREE(Constants.MP_GEAR_BLUE_THREE,-1);
	
	private final int gearProfile;
	private final int hopperProfile;
	
	private Station(int gearProfile,int hopperProfile) {
		this.gearProfile = gearProfile;
		this.hopperProfile = hopperProfile;
	}
	
	public int getGearProfile() {
		return gearProfile;
	}
	
	public int getHopperProfile() {
		return hopperProfile;
	}
}
