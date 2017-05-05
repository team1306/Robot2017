package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.SetSetpoint;
import org.usfirst.frc.team1306.robot.commands.Setpoint;
import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team1306.robot.commands.geartake.AdvancedDeployGeartake;
import org.usfirst.frc.team1306.robot.commands.intake.SpinIntake;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinShooter;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

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
			
			addParallel(new DeployIntake());
			addParallel(new SpinIntake(true));
			addSequential(new DriveDistance(8.8,1.9));
			addSequential(new DriveDistance(3,1));
			addSequential(new DriveDistance(4,1.6));
			addSequential(new SetSetpoint(Setpoint.AUTO_HOPPER));
			addSequential(new DriveDistance(0.4,0.4));
			addSequential(new SpinShooter(Constants.SHOOT_TIME,Constants.SHOOTER_RPM_SPEED));
			
		} else if(routine.equals(AutoMode.GEAR)) {
			
//			addSequential(new TimedDrive(station,routine,false));
//			if(!station.equals(Station.RED_TWO) && !station.equals(Station.BLUE_TWO)) {
////				addSequential(new AngledTurn(station));
//				addSequential(new TimedDrive(station,routine,true));
//			}
		
			
			
//			addSequential(new TimedDrive(-0.3,2.50));
			
			addParallel(new DeployIntake());
			addParallel(new AdvancedDeployGeartake(true));
			addSequential(new DriveDistance(-5.7,3.5)); //-8.9
			
			addSequential(new TimedDrive(0.3,1));
//			addSequential(new DeployIntake());
	
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
				return Station.RED_ONE;
			} else if(station == 2) {
				return Station.RED_TWO;
			} else {
				return Station.RED_THREE;
			}
		} else {
			if(station == 1) {
				return Station.BLUE_ONE;
			} else if(station == 2) {
				return Station.BLUE_TWO;
			} else {
				return Station.BLUE_THREE;
			}
		}
	}
	
}
