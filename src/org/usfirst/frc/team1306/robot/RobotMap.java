package org.usfirst.frc.team1306.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author James Tautges
 */
public class RobotMap {

	// oi ports
	public static final int PRIMARY_PORT = 0;
	public static final int SECONDARY_PORT = 1;

	// drivetrain controllers ports
	public static final int LEFT_TALON_1_PORT = 1;
	public static final int LEFT_TALON_2_PORT = 4;
	public static final int RIGHT_TALON_1_PORT = 2;
	public static final int RIGHT_TALON_2_PORT = 5;

	public static final int LEFT_SHIFTER_PORT_A = 0;
	public static final int LEFT_SHIFTER_PORT_B = 1;
	public static final int RIGHT_SHIFTER_PORT_A = 2;
	public static final int RIGHT_SHIFTER_PORT_B = 3;

	// shooter and turret controller ports
	public static final int FLYWHEEL_TALON_PORT = 9;
	public static final int HOOD_TALON_PORT = 11;
	public static final int TURRET_TALON_PORT = 10;

	// intake control ports
	public static final int INTAKE_ARM_LEFT_TALON_PORT = 8;
	public static final int INTAKE_ARM_RIGHT_TALON_PORT = 7;

	public static final int INTAKE_ROLLER_1_PORT = 0;
	public static final int INTAKE_ROLLER_2_PORT = 1;

	// indexer ports
	public static final int INDEXER_PORT = 2;

	// intake sensor ports
	public static final int INDEXER_LIMIT_1_PORT = 0;
	public static final int INDEXER_LIMIT_2_PORT = 1;
	public static final int PRESSURE_PAD_PORT = 0;

}
