package org.usfirst.frc.team1306.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The autonomous command station
 * @author Jackson Goth
 *
 */

public class AutonomousCommand extends CommandGroup{

	/**
	 * Not Final
	 */
	public AutonomousCommand(Speed speed) {
		
		addSequential(speed.getDriveCommand());
		
	}
}
