package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.turret.ScanDirection;

/**
 * Enum that contains commands for different alliance stations
 * @author Jackson Goth
 */
public enum Station {
	
	RED_ONE(AutoConstants.MP_ONE,ScanDirection.LEFT,ScanDirection.LEFT),
	RED_TWO(AutoConstants.MP_TWO,ScanDirection.LEFT,ScanDirection.LEFT),
	RED_THREE(AutoConstants.MP_THREE,ScanDirection.LEFT,ScanDirection.RIGHT),
	BLUE_ONE(AutoConstants.MP_FOUR,ScanDirection.RIGHT,ScanDirection.RIGHT),
	BLUE_TWO(AutoConstants.MP_FIVE,ScanDirection.RIGHT,ScanDirection.RIGHT),
	BLUE_THREE(AutoConstants.MP_SIX,ScanDirection.RIGHT,ScanDirection.LEFT);
		
	private final int motionProfile;
	private final ScanDirection dir1;
	private final ScanDirection dir2;
	private final double speed = AutoConstants.AUTO_SPEED;
	
	private Station(int motionProfile,ScanDirection dir1,ScanDirection dir2) {
		this.motionProfile = motionProfile;
		this.dir1 = dir1;
		this.dir2 = dir2;
	}
	
	public ScanDirection getScanDir(int shot) {
		return dir1;
		
	}
}
