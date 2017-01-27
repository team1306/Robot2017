package org.usfirst.frc.team1306.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The autonomous command station
 * @author Jackson Goth
 *
 */

public class AutonomousCommand extends CommandGroup{

	/**
	 * Method that accomplishes a given autonomous command
	 * 
	 * @param Station
	 * 		Which field position is robot located
	 */
	public AutonomousCommand(Station station) {
		
		/**
		 * Does initial drive forward, turn to a given angle, and then does final drive to gear peg
		 */
		addSequential(station.getDriveCommand("initial"));
		//addSequential(station.getTurnCommand()); TODO Get this working with gyro
		addSequential(station.getDriveCommand("final"));
		
		//TODO Shoot command?
	}
}
