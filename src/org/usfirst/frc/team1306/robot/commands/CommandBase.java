package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.subsystems.Climber;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1306.robot.subsystems.Geartake;
import org.usfirst.frc.team1306.robot.subsystems.Gyro;
import org.usfirst.frc.team1306.robot.subsystems.Hood;
import org.usfirst.frc.team1306.robot.subsystems.Hopper;
import org.usfirst.frc.team1306.robot.subsystems.Intake;
import org.usfirst.frc.team1306.robot.subsystems.Shooter;
import org.usfirst.frc.team1306.robot.subsystems.Turret;
import org.usfirst.frc.team1306.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the abstract for all other commands. This static class contains
 * instances of all the subsystems and the oi class so that each command that
 * extends this class can have access to the subsystems.
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public abstract class CommandBase extends Command {

	protected static OI oi;
	protected static Drivetrain drivetrain;
	protected static Shooter shooter;
	protected static Intake intake;
	protected static Hopper hopper;
	protected static Hood hood;
	protected static Turret turret;
	protected static Climber climber;
	protected static Geartake gearmech;
	protected static Gyro gyro;
	protected static Vision vision;
	
	public static void init() {
		drivetrain = new Drivetrain();
		shooter = new Shooter();
		intake = new Intake();
		turret = new Turret();
		hood = new Hood();
		hopper = new Hopper();
		climber = new Climber();
		gearmech = new Geartake();
		gyro = new Gyro();
		vision = new Vision();
		oi = new OI();
	}

}
