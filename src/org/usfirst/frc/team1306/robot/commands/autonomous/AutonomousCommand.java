package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.gearmech.SpinGeartake;
import org.usfirst.frc.team1306.robot.commands.turret.FindTarget;
import org.usfirst.frc.team1306.robot.commands.turret.ScanDirection;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The autonomous command station
 * @author Jackson Goth
 */
public class AutonomousCommand extends CommandGroup {
	
	/**
	 * This autonomous routine will do nothing
	 */
	public AutonomousCommand() {
		
	}
	
	/**
	 * This autonomous routine will score the 10 kpa in auto
	 * @param alliance
	 * 		Which side of the field is the robot on (Red or Blue?)
	 */
	public AutonomousCommand(Alliance alliance) {
		
		if(alliance.equals(Alliance.Red)) {
			addSequential(new FindTarget(ScanDirection.RIGHT));
		} else {
			addSequential(new FindTarget(ScanDirection.LEFT));
		}
		
		addSequential(new Fire());
		
		//TODO Cross Baseline
	}
	
	/**
	 * This is the advanced autonomous routine that will score gears or shoot a full hopper
	 * @param Alliance
	 * 		Which side of the field is the robot on (Red or Blue?)
	 * @param Station
	 * 		Which field position is robot located
	 */
	public AutonomousCommand(Alliance alliance, int station) {
		
		addSequential(new MotionProfile()); //TODO Give appropriate Station
		
		if(getStation(alliance, station).equals(Station.RED_THREE) || getStation(alliance, station).equals(Station.BLUE_THREE)) {
			
		} else {
			//addSequential(new SpinGeartake(-Constants.GEARTAKE_SPEED,Constants.GEAR_DEPLOY_TIME));
		}
		
		//addSequential(new Scan(getLocation(alliance,station).getScanDir()));
		//addParallel(new DeployIntake());
		
		//addSequential(new Scan(getLocation(alliance,station).getScanDir()));
		//addSequential(new Fire());
	}
	
	private Station getStation(Alliance alliance, int station) {
		
		if(alliance.equals(Alliance.Red)) {
			if(station == 1) {
				SmartDashboard.putString("Station:","RED_ONE");
				return Station.RED_ONE;
			} else if(station == 2) {
				SmartDashboard.putString("Station:","RED_TWO");
				return Station.RED_TWO;
			} else {
				SmartDashboard.putString("Station:","RED_THREE");
				return Station.RED_THREE;
			}
		} else if(alliance.equals(Alliance.Blue)) {
			if(station == 1) {
				SmartDashboard.putString("Station:","BLUE_ONE");
				return Station.BLUE_ONE;
			} else if(station == 2) {
				SmartDashboard.putString("Station:","BLUE_TWO");
				return Station.BLUE_TWO;
			} else {
				SmartDashboard.putString("Station:","BLUE_THREE");
				return Station.BLUE_THREE;
			}
		} else {
			return Station.UNKNOWN;
		}
	}
}
