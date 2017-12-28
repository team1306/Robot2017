package org.usfirst.frc.team1306.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Subsystem that is the hub for all data coming from the jetson
 * @author Jackson Goth
 */
public class Vision extends Subsystem {

	private NetworkTable table;
	
	public Vision() {
		
		//Sets-up the networktable to connect with jetson
		NetworkTable.setServerMode();
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		NetworkTable.setUpdateRate(0.01);
		table = NetworkTable.getTable("1306");
	}
	
	/**
	 * Returns whether or not the jetson is seeing the target or not
	 */
	public boolean seeTarget() {
		return table.getBoolean("seeTarget",false);
	}
	
	/**
	 * Returns the latest yaw value from the jetson
	 */
	public double getYaw() {
		return table.getNumber("yaw",0);
	}
	
	/**
	 * Returns the latest distance value from the jetson
	 */
	public double getDist() {
		return table.getNumber("dist",0);
	}
	
	/**
	 * Returns the latest angle value from the jetson
	 */
	public double getAngle() {
		return table.getNumber("angle",0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
