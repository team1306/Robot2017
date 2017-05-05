package org.usfirst.frc.team1306.robot.commands.geartake;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.autonomous.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AdvancedDeployGeartake extends CommandGroup {
	
	public AdvancedDeployGeartake(boolean wait) {
		
//		addSequential(new SpinGeartake(-Constants.GEARTAKE_SPEED,0.50));
		if(wait) {
			addSequential(new Wait(1.1));
		}
		addSequential(new DeployGeartake());
		
		addSequential(new SpinGeartake(-Constants.GEARTAKE_SPEED,1.0));
//		addSequential(new TimedDrive(0.3,0.35));
	}
}
