package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1306.robot.subsystems.Hood;
import org.usfirst.frc.team1306.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the abstract for all other commands. This static class contains
 * instances of all the subsystems and the oi class so that each command that
 * extends this class can have access to the subsystems.
 * 
 * @author James Tautges
 */

public abstract class CommandBase extends Command {

	protected static OI oi;
	protected static Drivetrain drivetrain;
	//protected static Shooter shooter;
	//protected static Turret turret;
	//public static Indexer indexer; // TODO figure out how to make this protected
	protected static Intake intake;
	//protected static IntakeArm intakeArm;
	protected static Hood hood;

	public static void init() {
		drivetrain = new Drivetrain();
		/*shooter = new Shooter();
		turret = new Turret();
		indexer = new Indexer();
		intake = new Intake();
		intakeArm = new IntakeArm();*/
		hood = new Hood();
		oi = new OI();
	}

}
