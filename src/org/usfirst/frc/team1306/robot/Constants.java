package org.usfirst.frc.team1306.robot;

/**
 * To store finalized variables all in one place
 * @author Jackson Goth and Sam Roquitte
 */
public class Constants {

	//Subsystem Constants
	public final static boolean INTAKE_ENABLED = true;	//Intake will only run when true
	public final static boolean SHOOTER_ENABLED = true;	//Shooter will only run when true
	public final static boolean DRIVETRAIN_ENABLED = true;	//Drivetrain will only run when true
	public final static boolean PID_DRIVETRAIN_ENABLED = true;	//PID-Drive will only run when true
	public final static boolean HOPPER_ENABLED = true;	//Hopper will only run when true
	public final static boolean CLIMBER_ENABLED = false; //Climber will only run when true
	public final static double SPEED_ZERO = 0.0;
	
	//OI Constants
	public final static double DEADBAND = 0.15;	//Joystick deadband
	public final static double TRIGGER_DEADBAND = 0.15;	//Trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0;  	//(Use SPEED_MODIFIER for drivetrain speed changes)
	
	//Button Constants
	public final static int INTAKE_BUTTON = XboxController.X;
	public final static int SHOOTER_BUTTON = XboxController.A;
	public final static int BANG_SHOOTER_BUTTON = XboxController.A;
	public final static int HOPPER_BUTTON = XboxController.Y;
	
	//Intake Constants
	public final static double INTAKE_SPEED = 0.85;	//Speed at which intake motor turns
	
	//Hopper Constants
	public final static double HOPPER_SPEED = 1.0;	//Speed at which hopper motor turns
	
	//Shooter Constants
	public final static double SHOOTER_SPEED = 0.95;	//When you adjust this you also have to change range
	public final static int SHOOTER_BANG_RANGE = (int)(SHOOTER_SPEED * 8738);	//Calculated by old_range(new_speed / old_speed)
	public final static double SHOOTER_BANG_CEILING = 1.0;
	
	//Turret Constants
	public final static double TURRET_TURN_SPEED = 0.5;
	
	//Quick-Turn Constants
	public final static double TURN_SPEED = 0.45;	//Speed at which QuickTurn turns robot
	
	//Autonomous Constants 
	public final static double AUTO_SPEED = 0.5;
	public final static double SHOOT_TIME = 10.0;
	public final static double ROTATE_VEL = 0.2;
	
	//Drivetrain Constants
	public final static double SPEED_MODIFIER = 0.8;	//Multiplier of speeds inputed into tankDrive
	public final static double PID_SPEED = 200;

	//PID Drivetrain Constants
	public final static double F = 0.7494;	//0.0 values aren't used in calculation
	public final static double P = 0.77;	
	public final static double I = 0.0033; 
	public final static double D = 0.0;
	
	//Vision Constants
	public final static double LOGITECH_HORIZ_ANGLE= 60; //Degrees
	public final static double LOGITECH_RES_HEIGHT=600; //Pixels
	public final static double LOGITECH_FOCAL_LENGTH=0.15748; //inches (4 mm)
	public final static double LOGITECH_RES_WIDTH=800; //Pixels
	public final static double UPPER_TAPE_WIDTH= 4.00; //Inches
	public final static double TOWER_HEIGHT= 88; //Inches
	
	//Turret Constants
	public static final boolean TURRET_ENABLED = true;
	public static final double TURRET_TURN_SPEED = 0.5;
}
