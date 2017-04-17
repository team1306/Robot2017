package org.usfirst.frc.team1306.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * An Xbox controller. This is a relatively minor extension of Joystick that
 * adds methods specific to the Xbox controller.
 * @author Finn Voichick
 */
public class XboxController extends Joystick {

	/**
	 * Creates a new XboxController connected to the specified port.
	 * 
	 * @param port
	 *            the port that the Xbox controller is connected to. This can be
	 *            seen on the driver station.
	 */
	public XboxController(int port) {
		super(port);
	}

	/**
	 * Get the x displacement of either joystick on an xbox controller.
	 * 
	 * @param hand
	 * 			  which joystick, LS or RS.
	 *            
	 * @return raw X displacement of joystick, on a scale from -1.0 to 1.0.
	 */
	
	public double getXNew(Hand hand) {
		if (hand.equals(Hand.kLeft)) {
			return getRawAxis(0);
		} else {
			return getRawAxis(4);
		}
	}

	/**
	 * Get the y displacement of either joystick on an xbox controller.
	 * 
	 * @param hand
	 * 	          which joystick, LS or RS.
	 *            
	 * @return raw y displacement of joystick, on a scale from -1.0 to 1.0.
	 */
	
	public double getYNew(Hand hand) {
		if (hand.equals(Hand.kLeft)) {
			return -getRawAxis(1);
		} else {
			return -getRawAxis(5);
		}
	}

	/**
	 * Get whether either trigger is pressed down.
	 * 
	 * @param hand
	 *            which trigger, LT or RT.
	 * @return true if the trigger is pressed, otherwise false.
	 */
	@Override
	public boolean getTrigger(Hand hand) {
		if (hand.equals(Hand.kLeft)) {
			return getLT() > 0.5;
		} else {
			return getRT() > 0.5;
		}
	}

	/**
	 * Get how far the left trigger is pressed down.
	 * 
	 * @return the left trigger axis value, on a scale from 0.0 to 1.0.
	 */
	public double getLT() {
		return getRawAxis(2);
	}

	/**
	 * Get how far the right trigger is pressed down.
	 * 
	 * @return the right trigger axis value, on a scale from 0.0 to 1.0.
	 */
	public double getRT() {
		return getRawAxis(3);
	}

	/**
	 * Get angle of the D-Pad button pressed in degrees, or -1 if the D-Pad
	 * isn't being pressed. This gets values only every 45 degrees.
	 * 
	 * @return the angle of the button pressed on the D-Pad.
	 */
	@Override
	public int getPOV() {
		return super.getPOV();
	}
	
	/** The A button index */
	public final static int A = 1;
	/** The B button index */
	public final static int B = 2;
	/** The X button index */
	public final static int X = 3;
	/** The Y button index */
	public final static int Y = 4;
	/** The LB button index */
	public final static int LB = 5;
	/** The RB button index */
	public final static int RB = 6;
	/** The BACK button index */
	public final static int BACK = 7;
	/** The START button index */
	public final static int START = 8;
	/** The LS button index */
	public final static int LS = 9;
	/** The RS button index */
	public final static int RS = 10;
}
