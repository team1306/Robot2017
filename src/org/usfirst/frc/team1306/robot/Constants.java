package org.usfirst.frc.team1306.robot;

/**
 * To store finalized variables all in one place
 * @author Jackson Goth and Sam Roquitte
 */
public class Constants {

	//OI Constants
	public final static double DEADBAND = 0.15;				//Joystick deadband
	public final static double TRIGGER_DEADBAND = 0.1;		//Trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0;   //(Use SPEED_MODIFIER for speed changes)
	
	
	//Button Constants
	public final static int INTAKE_BUTTON = 3;
	public final static int SHOOTER_BUTTON = 1;
	public final static int BANG_SHOOTER_BUTTON = 4;
	
	//Intake Constants
	public final static double INTAKE_DEFAULT_SPEED = .85; 	//Default speed of intake
	public final static double INTAKE_SPEED_CHANGE = 0.0; 	//Amount the speed goes down when changed with Intake.lowerSpeed()
	public final static boolean INTAKE_ENABLED = true;		//Intake will only run when true
	
	//Shooter Constants
	public final static double SHOOTER_SPEED = 1.0;
	public final static boolean SHOOTER_ENABLED = true;		//Shooter will only run when true
	public final static double SHOOTER_BANG_SPEED = 6000;	//6000 for close, 9000 for 100% speed
	public final static int SHOOTER_BANG_DEADBAND = 0;

	//Quick-Turn Constants
	public final static double TURN_SPEED = 0.45;			//Speed at which QuickTurn turns robot
	
	
	//Autonomous Constants 
	
	public final static double AUTO_SPEED = 0.5;
	
	/*
	 * Amount of time for initial drive before turning. TODO Experimentally find the correct value for all of these
	 */
	public final static double RED_ONE_TIME_INITIAL = 1;
	public final static double RED_TWO_TIME_INITIAL = 1;
	public final static double RED_THREE_TIME_INITIAL = 1;
	public final static double BLUE_ONE_TIME_INITIAL = 1;
	public final static double BLUE_TWO_TIME_INITIAL = 1;
	public final static double BLUE_THREE_TIME_INITIAL = 1;
	
	/*
	 * Angle at which the robot turns to get in line with gear peg. TODO Experimentally find the correct value for all of these
	 */
	public final static double RED_ONE_ANGLE = 90.0;
	public final static double RED_TWO_ANGLE = 0.0;
	public final static double RED_THREE_ANGLE = -90.0;
	public final static double BLUE_ONE_ANGLE = -90.0;
	public final static double BLUE_TWO_ANGLE = 0.0;
	public final static double BLUE_THREE_ANGLE = 90.0;
	
	/*
	 * Amount of time the robot is supposed to drive to get the gear on peg. TODO Experimentally find the correct value for all of these
	 */
	public final static double RED_ONE_TIME_FINAL = 1;
	public final static double RED_TWO_TIME_FINAL = 1;
	public final static double RED_THREE_TIME_FINAL = 1;
	public final static double BLUE_ONE_TIME_FINAL = 1;
	public final static double BLUE_TWO_TIME_FINAL = 1;
	public final static double BLUE_THREE_TIME_FINAL = 1;
	
	//Drivetrain Constants
	public final static double SPEED_MODIFIER = 0.8; 		//Multiplier of speeds inputed into tankDrive
	public final static boolean DRIVETRAIN_ENABLED = true;	//Drivetrain will only run when true
	public final static boolean PID_DRIVETRAIN_ENABLED = true;	//PID-Drive will only run when true
	
	//PID Constants
	public final static double P = 0.1113;
	public final static double I = 0.0; //0.0 values aren't used in calculation
	public final static double D = 0.0;
	public final static double PIDTolerance = 2;
}
