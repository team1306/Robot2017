package org.usfirst.frc.team1306.robot;

/**
 * To store finalized variables all in one place
 * @author Jackson Goth
 */
public class Constants {

	//OI Constants
	public final static double DEADBAND = 0.15;				//Joystick deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0;   //Change if too sensitive or not sensitive enough (Use SPEED_MODIFIER for speed changes)
	
	//Intake Constants
	public final static double INTAKE_DEFAULT_SPEED = .55; //Default speed of intake
	public final static double INTAKE_SPEED_CHANGE = 0.0; //Amount the speed goes down when changed with Intake.lowerSpeed()
	public final static boolean INTAKE_ENABLED = true;
	
	//Shooter Constants
	public final static double SHOOTER_SPEED = 500; //Talon SR Speed = 0.9
	public final static boolean SHOOTER_ENABLED = false;
	
	//Quick-Turn Constants
	public final static double TURN_SPEED = 0.3;
	
	//Autonomous Constants
	
	public final static double PID_TIME = 10; //Time of PID Catch-up in Autonomous
	public final static double SPEED_TWO = 2.0; //Desired speed of 2
	public final static double SPEED_FOUR = 4.0; //Desired speed of 4
	public final static double SPEED_SIX = 6.0; //Desired speed of 6
	public final static double SPEED_DEFAULT = .5; //Default speed of drivetrain
	
	public final static double FORWARD_SLOW_SPEED = 0.5;
	public final static double FORWARD_SLOW_TIME = 1.0;
	public final static double FORWARD_FAST_SPEED = 1.0;
	public final static double FORWARD_FAST_TIME = 0.5;
	public final static double BACKWARD_SLOW_SPEED = -0.5;
	public final static double BACKWARD_SLOW_TIME = 1.0;
	public final static double BACKWARD_FAST_SPEED = -1.0;
	public final static double BACKWARD_FAST_TIME = 0.5;
	
	//Drivetrain Constants
	public final static double SPEED_MODIFIER = 0.7; //Changes Speed of Drivetrain
	public final static boolean DRIVETRAIN_ENABLED = true;
	
	//PID Constants
	public final static double P = 1.0;
	public final static double I = 1;
	public final static double D = 1;
	public final static double PIDTolerance = 0.01;
}
