package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.vision.getData;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {

	public Vision() {
		
	}
	
	@Override
	protected void initDefaultCommand() {
		new getData();
	}

}
