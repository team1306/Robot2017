package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveMode;

/**
 * To store finalized variables all in one place
 * @author Jackson Goth and Sam Roquitte
 */
public class Constants {

	//Subsystem Constants (Determines if different subsystems will run or not)
	public final static boolean INTAKE_ENABLED = true;			
	public final static boolean SHOOTER_ENABLED = true;			
	public final static boolean DRIVETRAIN_ENABLED = true;		
	public final static boolean HOPPER_ENABLED = true;	
	public final static boolean HOOD_ENABLED = true;
	public final static boolean GEARTAKE_ENABLED = true;
	public final static boolean CLIMBER_ENABLED = true; 		
	public final static boolean TURRET_ENABLED = true;			
	public final static boolean INDEXER_ENABLED = true;			
	
	//OI Constants
	public final static double DEADBAND = 0.15; //Joystick deadband
	public final static double TRIGGER_DEADBAND = 0.15; //Trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0; //Multiplier for the joystick inputs  
	
	//Button Constants
	public final static int INTAKE_BUTTON = XboxController.X;
	public final static int SHOOTER_BUTTON = XboxController.A;
	public final static int GEARTAKE_BUTTON = XboxController.RB;
	public final static int GEARTAKE_BACK_BUTTON = XboxController.LB;
	
	//Autonomous Constants
	public final static double SHOOT_TIME = 10.0;
	public final static int MP_BASELINE = 0;
	public final static int MP_GEAR_RED_ONE = 1;
	public final static int MP_GEAR_RED_TWO= 2;
	public final static int MP_GEAR_RED_THREE = 3;
	public final static int MP_GEAR_BLUE_ONE = 4;
	public final static int MP_GEAR_BLUE_TWO = 5;;
	public final static int MP_GEAR_BLUE_THREE = 6;
	public final static int MP_HOPPER_RED = 7;
	public final static int MP_HOPPER_BLUE = 8;
	public final static int MP_FORWARD = 9;
	
	//Geartake Constants
	public final static int GEAR_DEPLOY_TIME = 1;
	public final static double GEARTAKE_SPEED = 1.0;
	
	//Climber Constants
	public final static double CLIMBER_SPEED = 0.70; //Cart: -0.25 Down, 0.6-0.7 Up
	public final static double CLIMBER_BACK_SPEED = -0.2;
	
	//Intake Constants
	public final static double INTAKE_SPEED = 0.75;
	
	//Hopper Constants
	public final static double HOPPER_SPEED = 1.0;	
	public final static int HOPPER_RAMP_I = 1;
	
	//Indexer Constants
	public final static double INDEXER_SPEED = 0.72;
	
	//Shooter Constants
	public final static double SHOOTER_SPEED = 0.78;
	public final static double SHOOTER_RPM_SPEED = 100;		//Shooter speed in RPM (default:3000) 3800
	public final static double INDEXER_RPM_SPEED = 2300;		//Shooter speed in RPM (default:2000) 4500 2700 2700
	public final static int SHOOTER_SPIN_UP_TIME = 1;			//Time to let shooters spin up before turning on hopper and indexers
	public final static int SHOOTER_BANG_RANGE = 240;			//If shooter speed is below this number, bang bang will kick in
	public final static double SHOOTER_BANG_CEILING = 1.0;		//Speed bang bang uses when below desired speed
	public final static double SHOOTER_F = 13.28;				//13.28
	public final static double SHOOTER_P = Double.MAX_VALUE;
	public final static double SHOOTER_I = 0.0;
	public final static double SHOOTER_D = 0.0;
	public final static double INDEXER_F = 0.02886; 			//0.1162 0.1249
	public final static double INDEXER_P = 0.04092; 			//0.04092
	public final static double INDEXER_I = 0.0;					//TODO Calculate indexer I
	public final static double INDEXER_D = 0.0;					//TODO Calculate indexer D
	
	//Turret Constants
	public final static double MANUAL_TURRET_ROT = 0.5;			//Maxumum rotation value for the turret when in manual mode
	public final static double DPAD_TURRET_STEP = 0.20;
	public final static double TURRET_TURN_LEFT_SPEED = 0.13;	
	public final static double TURRET_TURN_RIGHT_SPEED = -0.13;	
	public final static double TURRET_LEFT_LIMIT = 2750;		
	public final static double TURRET_RIGHT_LIMIT = -1120;		
	public final static double TURRET_RESET_POSITION = 0;		
	public final static double TURRET_TURN_TOLERANCE = 10;
	public final static double TURRET_RECOVERY_TIME = 0.5;
	public final static double YAW_DEADBAND = 2.5;
	public final static double TURRET_GEAR_CONVERSION = 1.88888889; 		//72, 136 (136/72) Conversion turret rotations into gear rotations TODO Calculate this value with the gear tooth ratio
	public final static double TURRET_RIGHT_ROT_LIMIT = 0.25;
	public final static double TURRET_LEFT_ROT_LIMIT = -0.25;
	public final static double TURRET_START_POS = 780;
	public final static double RED_TWO_SETPOINT = 0.0;
	public final static double BLUE_TWO_SETPOINT = 0.0;
	public final static double RED_HOPPER_SETPOINT = 0.0;
	public final static double BLUE_HOPPER_SETPOINT = 0.0;
	
	//Drivetrain Constants
	public final static DriveMode DRIVE_MODE = DriveMode.ARCADE;	//Drive modes defined in DriveMode ENUM
	public final static double SPEED_MODIFIER = 1.0;			//Multiplier of speeds inputed into tankDrive=
	public final static double F = 0.7494;
	public final static double P = 0.0;	//0.1  46.5 46 46 45.5 46
	public final static double I = 0.0; 
	public final static double D = 0.0;
	
	
}
