package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.gearmech.SpinGeartake;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team1306.robot.commands.turret.FindTarget;
import org.usfirst.frc.team1306.robot.commands.turret.ScanDirection;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The autonomous command station containing many different autonomous routines
 * @author Jackson Goth
 */
public class AutonomousCommand extends CommandGroup {
	
	/**
	 * This is the advanced autonomous routine that will score gears or shoot a full hopper
	 * @param Alliance
	 * 		Which side of the field is the robot on (Red or Blue?)
	 * @param Position
	 * 		Which field position is robot located
	 * @param Routine
	 * 		Which autonomous routine should the robot run?
	 */
	public AutonomousCommand(Alliance alliance, int position, AutoMode routine) {
		
		SmartDashboard.putString("starting auto","true");
		//addSequential(new MagicDrive(5));
		
		Station station = getStation(alliance,position);
		
		if(routine.equals(AutoMode.HOPPER_GEAR)) {
			
			placeGear(station);
			//addSequential(new DeployIntake());
			
			//If in alliance station closest to the boiler it will move to the nearest hopper and empty it
//			if(station.equals(Station.RED_THREE) || station.equals(Station.BLUE_ONE)) {
//				addSequential(new MotionProfile(station.getHopperProfile()));
//				addSequential(new SpinShooter(Constants.SHOOT_TIME));
//			}
			
		} else if(routine.equals(AutoMode.GEAR)) {
			
			placeGear(station);
			addSequential(new DeployIntake());
			//TODO Shoot?
			
		} else if(routine.equals(AutoMode.TEN_KPA)) {
			
			//Vision scanning
			if(alliance.equals(Alliance.Red)) {
				addSequential(new FindTarget(ScanDirection.RIGHT));
			} else if(alliance.equals(Alliance.Blue)) {
				addSequential(new FindTarget(ScanDirection.LEFT));
			}
			
			addSequential(new SpinShooter(Constants.SHOOT_TIME/2)); //Lower shoot time because less balls
			addSequential(new TimedDrive(Constants.AUTO_SPEED,2.5)); //Cross baseline TODO test
			addSequential(new DeployIntake());
			
		} else if(routine.equals(AutoMode.BLANK)){
			
		}
	}
	
	private void placeGear(Station station) {
		
		if(station.equals(station.RED_ONE) || station.equals(station.BLUE_ONE)) {
//			addSequential(new MagicDrive(5));
//			addSequential(new AngledTurn(45));
//			addSequential(new MagicDrive(5));
		} else if(station.equals(station.RED_TWO) || station.equals(station.BLUE_TWO)) {
//			addSequential(new MagicDrive(5));
//			addSequential(new AngledTurn(45));
//			addSequential(new MagicDrive(5));
		} else if(station.equals(Station.RED_THREE) || station.equals(station.RED_THREE)) {
//			addSequential(new MagicDrive(5));
//			addSequential(new AngledTurn(45));
//			addSequential(new MagicDrive(5));
		}
		
//		addSequential(new MagicDrive(1));
//		addSequential(new AngledTurn(90));
		addSequential(new MotionProfile(station.getGearProfile()));
//		addSequential(new SpinGeartake(-Constants.GEARTAKE_SPEED,Constants.GEAR_DEPLOY_TIME));
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
		} else {
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
		}
	}
}
