package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.lib.util.Settings;
import org.usfirst.frc.team1306.lib.util.Settings.Device;
import org.usfirst.frc.team1306.lib.util.Settings.DriveMode;
import org.usfirst.frc.team1306.lib.util.Settings.TalonType;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.subsystems.Climber;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1306.robot.subsystems.Geartake;
import org.usfirst.frc.team1306.robot.subsystems.Gyro;
import org.usfirst.frc.team1306.robot.subsystems.Intake;
import org.usfirst.frc.team1306.robot.subsystems.Shooter;
import org.usfirst.frc.team1306.robot.subsystems.Turret;
import org.usfirst.frc.team1306.robot.subsystems.Vision;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the abstract for all other commands. This static class contains
 * instances of all the subsystems and the oi class so that each command that
 * extends this class can have access to the subsystems.
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public abstract class CommandBase extends Command {

	private static Settings driveConfig;
	
	protected static OI oi;
	protected static Drivetrain drivetrain;
	protected static Shooter shooter;
	protected static Intake intake;
	protected static Turret turret;
	protected static Climber climber;
	protected static Geartake gearmech;
	protected static Gyro gyro;
	protected static Vision vision;
	
	public static void init() {

		driveConfig = new Settings(); //Drivetrain Configuration
		
		/* Adding all of the TalonSRXs, one master and one slave for each side */
		driveConfig.add(new CANTalon(RobotMap.LEFT_TALON_1_PORT),TalonType.LEFT_MASTER);
		driveConfig.add(new CANTalon(RobotMap.RIGHT_TALON_1_PORT),TalonType.RIGHT_MASTER);
		driveConfig.add(new CANTalon(RobotMap.LEFT_TALON_2_PORT),TalonType.LEFT_SLAVE);
		driveConfig.add(new CANTalon(RobotMap.RIGHT_TALON_2_PORT),TalonType.RIGHT_SLAVE);
		
		driveConfig.add(Device.ENCODER); //Adding encoders to the config
		driveConfig.add(Device.GYRO); //Adding a gyro to the config
		
		driveConfig.setDriveMode(DriveMode.ARCADE);
		
		drivetrain = new Drivetrain(driveConfig);
		shooter = new Shooter();
		intake = new Intake();
		turret = new Turret();
		climber = new Climber();
		gearmech = new Geartake();
		gyro = new Gyro();
		vision = new Vision();
		oi = new OI();
	}

}
