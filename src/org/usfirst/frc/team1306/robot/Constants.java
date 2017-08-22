package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveMode;

/**
 * To store finalized variables all in one place
 * @author Jackson Goth and Sam Roquitte
 */
public class Constants {

	//Subsystem Control (Switching to false will disable all output for that subsystem)
	public final static boolean INTAKE_ENABLED = true;			
	public final static boolean SHOOTER_ENABLED = true;			
	public final static boolean DRIVETRAIN_ENABLED = true;		
	public final static boolean HOPPER_ENABLED = true;	
	public final static boolean HOOD_ENABLED = true;
	public final static boolean GEARTAKE_ENABLED = true;
	public final static boolean CLIMBER_ENABLED = true; 		
	public final static boolean TURRET_ENABLED = true;			
	public final static boolean INDEXER_ENABLED = true;			
	
	//SmartDashboard Debug Modes
	public final static boolean DEBUG_SUBSYSTEMS = false; //Enables debug smartdashboard outputs for all subsystems (excluding voltage)
	public final static boolean DEBUG_DRIVETRAIN = false; //Speed and Position of Encoders TODO Add Gyro?
	public final static boolean DEBUG_SHOOTER = false; //Speed and Position of both encoders
	public final static boolean DEBUG_TURRET = false; //Position of turret in encoder units and rotations
	
	public final static boolean DEBUG_VOLTAGE = false; //Current draw of each PDP slot
	
	//OI Constants
	public enum ControlMode {NORMAL, OUTREACH};
	public final static ControlMode CONTROL_MODE = ControlMode.NORMAL;
	public final static double DEADBAND = 0.15; //Joystick and trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0; //Joystick inputs raised to this power
	
	//Button Constants (Used in some commands for detecting if a button is still pressed)
	public final static int INTAKE_BUTTON = XboxController.X;
	public final static int SHOOTER_BUTTON = XboxController.A;
	public final static int GEARTAKE_FORWARD_BUTTON = XboxController.RB;
	public final static int GEARTAKE_REVERSE_BUTTON = XboxController.LB;
	
	//Autonomous Constants //TODO Write usage
	public final static double SHOOT_TIME = 10.0;
	
	//Geartake Constants
	public final static int GEAR_DEPLOY_TIME = 1;
	public final static double GEARTAKE_SPEED = 1.0;
	
	//Climber Constants
	public final static double CLIMBER_SPEED = 1.0;
	public final static double CLIMBER_BACK_SPEED = -0.2;
	
	//Intake Constants
	public final static double INTAKE_SPEED = 0.75;
	
	//Hopper Constants
	public final static double HOPPER_SPEED = 1.0;
	
	//Indexer Constants
	public final static double INDEXER_SPEED = 1.0;
	
	//Setpoint Constants
	public final static double SHOOTER_BOILER_RPM = 2720;
	public final static double SHOOTER_BOILER_ADJ = 0.0;
	public final static double INDEXER_BOILER_RPM = 2650;
	public final static double TURRET_BOILER_POS = 0.0;

	public final static double SHOOTER_PEG_RPM = 3210;
	public final static double SHOOTER_PEG_ADJ = 10.0;
	public final static double INDEXER_PEG_RPM = 3200;
	public final static double TURRET_PEG_POS = 0.0;
	
	public final static double SHOOTER_AUTO_HOPPER_RPM = 3055;
	public final static double SHOOTER_AUTO_HOPPER_ADJ = 78.0;
	public final static double INDEXER_AUTO_HOPPER_RPM = 3040;
	public final static double TURRET_AUTO_HOPPER_POS = -0.2228;
	
	//Shooter Constants
	public final static double SHOOTER_SPEED = 0.78;
	public final static double SHOOTER_RPM_SPEED = 2590;		
	public final static double INDEXER_RPM_SPEED = 2525;		
	public final static double SHOOTER_SPIN_UP_TIME = 0.5;			//Time to let shooters spin up before turning on hopper and indexers
	public final static double SHOOTER_F = 13.28;				
	public final static double SHOOTER_P = Double.MAX_VALUE;
	public final static double SHOOTER_I = 0.0;
	public final static double SHOOTER_D = 0.0;
	public final static double INDEXER_F = 0.02886; 			
	public final static double INDEXER_P = 0.04092; 			
	public final static double INDEXER_I = 0.0;					
	public final static double INDEXER_D = 0.0;					
	
	//Turret Constants
	public final static double TURRET_TURN_LEFT_SPEED = 0.13;	
	public final static double TURRET_TURN_RIGHT_SPEED = -0.13;		
	public final static double TURRET_RESET_POSITION = 0;
	public final static double TURRET_GEAR_CONVERSION = 1.88888889; //(136/72) Amount of gear rotations that make up one full turret rotation
	public final static double TURRET_RIGHT_ROT_LIMIT = 0.25;
	public final static double TURRET_LEFT_ROT_LIMIT = -0.25;
	public final static double TURRET_START_POS = 3480;
	
	//Drivetrain Constants
	public final static DriveMode DRIVE_MODE = DriveMode.ARCADE; //What driving controls the robot will use (Arcade or Tank)
	public final static double SPEED_MODIFIER = 1.0; //Speed multiplier (only really used with percentvbus driving)
	public final static double OUTREACH_MODIFIER = 0.2;
	public final static double F = 1.4; //TODO Re-Tune
	public final static double P = 0.85;	//TODO Re-Tune
	public final static double I = 0.0; //TODO Re-Tune
	public final static double D = 0.0; //TODO Re-Tune
	
	
}
