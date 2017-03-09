package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.turret.Scan;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The autonomous command station
 * @author Jackson Goth
 */
public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand() {
		addSequential(new MotionProfile());
	}
	
	/**
	 * Method that accomplishes a given autonomous command
	 * 
	 * @param Station
	 * 		Which field position is robot located
	 */
	public AutonomousCommand(Alliance alliance,int station) {
		
		
		addSequential(new MotionProfile());
		//addSequential(new Scan(getLocation(alliance,station).getScanDir()));
		//addParallel(new DeployIntake());
		
		//addSequential(new Scan(getLocation(alliance,station).getScanDir()));
		//addSequential(new Fire());
	}
	
	private Station getLocation(Alliance alliance,int station) {
		
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
