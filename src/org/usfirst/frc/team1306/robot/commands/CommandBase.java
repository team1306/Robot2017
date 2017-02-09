package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.subsystems.Climber;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1306.robot.subsystems.Hopper;
import org.usfirst.frc.team1306.robot.subsystems.Intake;
import org.usfirst.frc.team1306.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the abstract for all other commands. This static class contains
 * instances of all the subsystems and the oi class so that each command that
 * extends this class can have access to the subsystems.
 * 
 */

public abstract class CommandBase extends Command {

	protected static OI oi;
	protected static Drivetrain drivetrain;
	protected static Shooter shooter;
	protected static Intake intake;
	protected static Hopper hopper;
	protected static Climber climber;
	
	public static void init() {
		drivetrain = new Drivetrain();
		shooter = new Shooter();
		intake = new Intake();
		hopper = new Hopper();
		climber = new Climber();
		oi = new OI();
	}

}
