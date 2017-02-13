package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.gearmech.DeployGear;
import org.usfirst.frc.team1306.robot.commands.turret.Scan;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The autonomous command station
 * @author Jackson Goth
 */
public class AutonomousCommand extends CommandGroup {

	/**
	 * Method that accomplishes a given autonomous command
	 * 
	 * @param Station
	 * 		Which field position is robot located
	 */
	public AutonomousCommand(Station station) {
		
		addSequential(new Scan(station.getScanDir(0)));
		addParallel(new MotionProfile());
		addSequential(new Fire());
		addSequential(new DeployGear());
		addSequential(new Wait(AutoConstants.SCAN_DELAY));
		addSequential(new Scan(station.getScanDir(1)));
		addSequential(new Fire());
	}
}
