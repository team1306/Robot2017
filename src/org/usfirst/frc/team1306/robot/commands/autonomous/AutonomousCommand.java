package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.geartake.DeployGeartake;
import org.usfirst.frc.team1306.robot.commands.geartake.RetractGeartake;
import org.usfirst.frc.team1306.robot.commands.geartake.SpinGeartake;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team1306.robot.commands.turret.FindTarget;
import org.usfirst.frc.team1306.robot.commands.turret.ScanDirection;
import org.usfirst.frc.team1306.robot.commands.turret.TurnTurret;

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
		
		Station station = getStation(alliance,position);
		
		if(routine.equals(AutoMode.HOPPER_GEAR)) {
			
			if(station.equals(Station.RED_THREE)) {
				addSequential(new MotionProfile(Constants.MP_HOPPER_RED));
				
				//Vision Works
				addSequential(new FindTarget(ScanDirection.RIGHT));
				
				//Vision Doesn't Work
				addSequential(new TurnTurret(Constants.RED_HOPPER_SETPOINT));
				
				addSequential(new SpinShooter(Constants.SHOOT_TIME));
			} else if(station.equals(Station.BLUE_ONE)) {
				addSequential(new MotionProfile(Constants.MP_HOPPER_RED));
				
				//Vision Works
				addSequential(new FindTarget(ScanDirection.LEFT));
				
				//Vision Doesn't Work
				addSequential(new TurnTurret(Constants.BLUE_HOPPER_SETPOINT));
				
				addSequential(new SpinShooter(Constants.SHOOT_TIME));
			} else {
				addSequential(new MotionProfile(station.getGearProfile()));
				addSequential(new RetractGeartake());
				addSequential(new MotionProfile(Constants.MP_FORWARD));
			}
			
		} else if(routine.equals(AutoMode.GEAR)) {
			
			addSequential(new MotionProfile(station.getGearProfile()));
			addSequential(new DeployGeartake());
			addSequential(new SpinGeartake(-Constants.GEARTAKE_SPEED,1));
//			addSequential(new MotionProfile(Constants.MP_FORWARD));
			addSequential(new DeployIntake());

			//Vision Works
//			if(position == 2) {
//				if(alliance.equals(Alliance.Red)) {
//					addSequential(new FindTarget(ScanDirection.RIGHT));
//				} else {
//					addSequential(new FindTarget(ScanDirection.LEFT));
//				}
//				addSequential(new SpinShooter(Constants.SHOOT_TIME));
//			}
		
			//Vision Doesn't Work     
//			if(position == 2) {
//				if(alliance.equals(Alliance.Red)) {
//					addSequential(new TurnTurret(Constants.RED_TWO_SETPOINT));
//				} else {
//					addSequential(new TurnTurret(Constants.BLUE_TWO_SETPOINT));
//				}
//				addSequential(new SpinShooter(Constants.SHOOT_TIME));
//			}		
		} else if(routine.equals(AutoMode.TEN_KPA)) {
			
			//Vision Works
			if(alliance.equals(Alliance.Red)) {
				addSequential(new FindTarget(ScanDirection.RIGHT));
			} else if(alliance.equals(Alliance.Blue)) {
				addSequential(new FindTarget(ScanDirection.LEFT));
			}
			
			addSequential(new SpinShooter(Constants.SHOOT_TIME));
			addSequential(new MotionProfile(Constants.MP_BASELINE));
			
		} else if(routine.equals(AutoMode.BASELINE)) {
			addSequential(new MotionProfile(Constants.MP_BASELINE));
		} else if(routine.equals(AutoMode.BLANK)){
			
		}
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
