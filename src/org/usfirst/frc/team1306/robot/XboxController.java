package org.usfirst.frc.team1306.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * An Xbox controller. This is a relatively minor extension of Joystick that adds methods specific to the Xbox controller.
 * @author Finn Voichick
 */
public class XboxController extends Joystick {

	/**
	 * Creates a new XboxController connected to a specified port (seen on driver-station).
	 */
	public XboxController(int port) {
		super(port);
	}

	/**
	 * Get the x displacement of either joystick on an xbox controller.
	 */
	public double getXAxis(Hand hand) {
		if (hand.equals(Hand.kLeft)) {
			return getRawAxis(0);
		} else {
			return getRawAxis(4);
		}
	}

	/**
	 * Get the y displacement of either joystick on an xbox controller.
	 */
	public double getYAxis(Hand hand) {
		if (hand.equals(Hand.kLeft)) {
			return -getRawAxis(1);
		} else {
			return -getRawAxis(5);
		}
	}

	/**
	 * Get how far the left trigger is pressed down (0.0 to 1.0).
	 */
	public double getLT() {
		return getRawAxis(2);
	}

	/**
	 * Get how far the right trigger is pressed down (0.0 to 1.0).
	 */
	public double getRT() {
		return getRawAxis(3);
	}

	/**
	 * Get angle of the D-Pad button pressed in degrees, or -1 if the D-Pad
	 * isn't being pressed. This gets values only every 45 degrees.
	 */
	@Override
	public int getPOV() {
		return super.getPOV();
	}
	
}
