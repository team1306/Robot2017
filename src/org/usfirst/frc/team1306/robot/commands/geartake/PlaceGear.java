package org.usfirst.frc.team1306.robot.commands.geartake;

import org.usfirst.frc.team1306.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceGear extends CommandGroup {
	
	public PlaceGear() {
	
		addSequential(new DeployGeartake());
		addSequential(new SpinGeartake(-Constants.GEARTAKE_SPEED,1.0));
	}
}
