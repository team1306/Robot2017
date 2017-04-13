package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.SetSetpoint;
import org.usfirst.frc.team1306.robot.commands.Setpoint;
import org.usfirst.frc.team1306.robot.commands.geartake.DeployGeartake;
import org.usfirst.frc.team1306.robot.commands.geartake.SpinGeartake;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinShooter;

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
			//TODO Possible auto routine for later
		} else if(routine.equals(AutoMode.GEAR)) {
			
//			addSequential(new MotionProfile(station.getGearProfile()));
			
			addSequential(new TimedDrive(-0.3,2.50));
			addSequential(new DeployGeartake());
			addSequential(new SpinGeartake(-Constants.GEARTAKE_SPEED,1));
			addSequential(new TimedDrive(0.3,1));
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
//			if(alliance.equals(Alliance.Red)) {
//				addSequential(new FindTarget(ScanDirection.RIGHT));
//			} else if(alliance.equals(Alliance.Blue)) {
//				addSequential(new FindTarget(ScanDirection.LEFT));
//			}
			addSequential(new SetSetpoint(Setpoint.AUTO_CLOSE));
			addSequential(new SpinShooter(Constants.SHOOT_TIME,Constants.SHOOTER_RPM_SPEED));
			addSequential(new TimedDrive(-0.3,2.7));
			addSequential(new DeployIntake());
			
		} else if(routine.equals(AutoMode.BASELINE)) {

			addSequential(new TimedDrive(-0.3,2.7));
			addSequential(new DeployIntake());
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

	private double getRot(Station station) {
		if(station.equals(Station.BLUE_ONE)) {
			return Constants.BLUE_ONE_ROT;
		} else if(station.equals(Station.BLUE_THREE)) {
			return Constants.BLUE_THREE_ROT;
		} else if(station.equals(Station.RED_ONE)) {
			return Constants.RED_ONE_ROT;
		} else if(station.equals(Station.RED_THREE)) {
			return Constants.RED_THREE_ROT;
		} else {
			return 0;
		}
	}
}
