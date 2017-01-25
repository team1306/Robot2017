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
	 * @param speed
	 * 	speed at which drivetrain will try to run
	 */
	public AutonomousCommand(Speed speed) {
		
		addSequential(speed.getDriveCommand());
		
	}
}
