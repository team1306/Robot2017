package org.usfirst.frc.team1306.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The autonomous command
 * @author Jackson Goth
 *
 */

public class AutonomousCommand extends CommandGroup{

	public AutonomousCommand(Speed speed) {
		
		addSequential(speed.getDriveCommand());
		
	}
}
