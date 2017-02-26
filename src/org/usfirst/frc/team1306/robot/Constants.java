package org.usfirst.frc.team1306.robot;

/**
 * To store finalized variables all in one place
 * @author Jackson Goth and Sam Roquitte
 */
public class Constants {

	//Subsystem Constantss
	public final static boolean INTAKE_ENABLED = true;			//Intake will only run when true
	public final static boolean SHOOTER_ENABLED = true;			//Shooter will only run when true
	public final static boolean DRIVETRAIN_ENABLED = true;		//Drivetrain will only run when true
	public final static boolean PID_DRIVETRAIN_ENABLED = false;	//PID-Drive will only run when true
	public final static boolean HOPPER_ENABLED = true;			//Hopper will only run when true
	public final static boolean CLIMBER_ENABLED = true; 		//Climber will only run when true
	public final static boolean TURRET_ENABLED = true;			//Turret will only be able to turn if true
	public final static boolean INDEXER_ENABLED = true;
	
	//Global Constants
	public final static double SPEED_ZERO = 0.0;
	
	//OI Constants
	public final static double DEADBAND = 0.15;					//Joystick deadband
	public final static double TRIGGER_DEADBAND = 0.15;			//Trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0;  		//(Use SPEED_MODIFIER for drivetrain speed changes)
	
	//Button Constants
	public final static int INTAKE_BUTTON = XboxController.X;
	public final static int SHOOTER_BUTTON = XboxController.A;
	public final static int SHOOTER_BACK_BUTTON = XboxController.START;
	public final static int BANG_SHOOTER_BUTTON = XboxController.A;
	
	//GearMech Constants
	public final static int GEAR_DEPLOY_TIME = 1;
	
	//Climber Constants
	public final static double CLIMBER_SPEED = 1.0;
	
	//Intake Constants
	public final static double INTAKE_SPEED = 0.70;				//Speed at which intake motor turns
	
	//Hopper Constants
	public final static double HOPPER_SPEED = 1.0;				//Speed at which hopper motor turns
	
	//Indexer Constants
	public final static double INDEXER_SPEED = 1.0;				//Speed at which indexer motor turns
	
	//Shooter Constants
	public final static double SHOOTER_SPEED = 0.95;			//Shooter low speed
	public final static int SHOOTER_BANG_RANGE = 250;			//(int)(SHOOTER_SPEED * 8738);
	public final static double SHOOTER_BANG_CEILING = 1.0;		//Value bang bang sets to when under desired speed
	
	//Turret Constants
	public final static double TURRET_TURN_LEFT_SPEED = 0.25;	
	public final static double TURRET_TURN_RIGHT_SPEED = -0.25;	
	public final static double TURRET_LEFT_LIMIT = 3170;		
	public final static double TURRET_RIGHT_LIMIT = 1180;		
	public final static int TURRET_RESET_POSITION = 2150;		
	public final static double TURRET_TURN_TOLERANCE = 20; //TODO Refine
	public final static double TURRET_P = 0.0;
	public final static double TURRET_I = 0.0;
	public final static double TURRET_D = 0.0;
	
	//Drivetrain Constants
	public final static double SPEED_MODIFIER = 1.0;			//Multiplier of speeds inputed into tankDrive
	public final static double PID_SPEED = 200;
	public final static double TURN_SPEED = 0.45;				//Speed at which QuickTurn turns robot
	public final static double LEFT_ROTATIONS = 40;
	public final static double RIGHT_ROTATIONS = -40;
	public final static double F = 0.7494;						
	public final static double P = 0.77;	
	public final static double I = 0.0033; 
	public final static double D = 0.0;
	
	//Vision Constants
	public final static double LOGITECH_HORIZ_ANGLE = 60; 		//Degrees
	public final static double LOGITECH_RES_HEIGHT = 600; 		//Pixels
	public final static double LOGITECH_FOCAL_LENGTH = 0.15748; //Inches (4 mm)
	public final static double LOGITECH_RES_WIDTH = 800; 		//Pixels
	public final static double UPPER_TAPE_WIDTH = 4.00; 		//Inches
	public final static double TOWER_HEIGHT = 88; 				//Inches
	public final static double DATA_REFRESH_RATE = 0.1;
	public final static double VISION_YAW_TOLERANCE = 5;		//TODO Refine
	public final static String JETSON_IP = "";
}
