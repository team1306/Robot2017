package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveMode;

/**
 * To store finalized variables all in one place
 * @author Jackson Goth and Sam Roquitte
 */
public class Constants {

	//Subsystem Constants (Determines if differen't subsystems will run or not)
	public final static boolean INTAKE_ENABLED = true;			
	public final static boolean SHOOTER_ENABLED = true;			
	public final static boolean DRIVETRAIN_ENABLED = true;		
	public final static boolean HOPPER_ENABLED = true;			
	public final static boolean CLIMBER_ENABLED = true; 		
	public final static boolean TURRET_ENABLED = true;			
	public final static boolean INDEXER_ENABLED = true;			
	
	//Global Constants
	public final static double SPEED_ZERO = 0.0;
	
	//OI Constants
	public final static double DEADBAND = 0.15;					//Joystick deadband
	public final static double TRIGGER_DEADBAND = 0.15;			//Trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0;		//Multiplier for the joystick inputs  
	
	//Button Constants
	public final static int INTAKE_BUTTON = XboxController.X;
	public final static int SHOOTER_BUTTON = XboxController.A;
	public final static int SHOOTER_BACK_BUTTON = XboxController.START;
	public final static int MANUAL_TURRET_BUTTON = XboxController.LB;
	public final static int DPAD_TURRET_BUTTON = XboxController.RB;
	public final static int BANG_SHOOTER_BUTTON = XboxController.B;
	public final static int AIM_LEFT_BUTTON = XboxController.LB;
	public final static int AIM_RIGHT_BUTTON = XboxController.RB;
	public final static int GEARTAKE_BUTTON = XboxController.RB;
	public final static int GEARTAKE_BACK_BUTTON = XboxController.LB;
	
	//Autonomous Constants
	public final static int AUTO_KPA = 0;
	public final static int AUTO_BLANK = 1;
	public final static double AUTO_SPEED = 0.5;
	public final static double SHOOT_TIME = 10.0;
	public final static double ROTATE_VEL = 0.2;
	public final static double SCAN_DELAY = 1.0;
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
	
	//GearMech Constants
	public final static int GEAR_DEPLOY_TIME = 1;
	public final static double GEARTAKE_SPEED = 0.6;
	
	//Climber Constants
	public final static double CLIMBER_SPEED = 1.0;
	public final static double CLIMBER_BACK_SPEED = -0.2;
	
	//Intake Constants
	public final static double INTAKE_SPEED = 0.70;
	
	//Hopper Constants
	public final static double HOPPER_SPEED = 1.0;	
	public final static int HOPPER_RAMP_I = 1;
	
	//Indexer Constants
	public final static double INDEXER_SPEED = 1.0;
	
	//Shooter Constants
	public final static double SHOOTER_SPEED = 0.72;			//0.84
	public final static double SHOOTER_RPM_SPEED = 3800;		//Shooter speed in RPM (default:3000) 3600 2750 3200
	public final static double INDEXER_RPM_SPEED = 2700;		//Shooter speed in RPM (default:2000) 4500 2700 2700
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
	public final static double DPAD_TURRET_STEP = 0.2;
	public final static double TURRET_TURN_LEFT_SPEED = 0.13;	
	public final static double TURRET_TURN_RIGHT_SPEED = -0.13;	
	public final static double TURRET_LEFT_LIMIT = 2750;		
	public final static double TURRET_RIGHT_LIMIT = -1120;		
	public final static double TURRET_RESET_POSITION = 0;		
	public final static double TURRET_TURN_TOLERANCE = 10;
	public final static double TURRET_RECOVERY_TIME = 0.5;
	public final static double YAW_DEADBAND = 2.5;
	public final static double TURRET_GEAR_CONVERSION = 1; 		//Conversion turret rotations into gear rotations TODO Calculate this value with the gear tooth ratio
	
	//Drivetrain Constants
	public final static DriveMode DRIVE_MODE = DriveMode.ARCADE;	//Drive modes defined in DriveMode ENUM
	public final static double SPEED_MODIFIER = 1.0;			//Multiplier of speeds inputed into tankDrive=
	public final static double F = 0.7494;
	public final static double P = 0.65;	//0.1  46.5 46 46 45.5 46
	public final static double I = 0.0; 
	public final static double D = 0.0;
	
	//Vision Constants
	public final static double LOGITECH_HORIZ_ANGLE = 60; 		//Degrees
	public final static double LOGITECH_RES_HEIGHT = 600; 		//Pixels
	public final static double LOGITECH_FOCAL_LENGTH = 0.15748; //Inches (4 mm)
	public final static double LOGITECH_RES_WIDTH = 800; 		//Pixels
	public final static double UPPER_TAPE_WIDTH = 4.00; 		//Inches
	public final static double TOWER_HEIGHT = 88; 				//Inches
	public final static double DATA_REFRESH_RATE = 0.1;
	public final static double VISION_YAW_TOLERANCE = 5;
	public final static String JETSON_IP = "";
}
