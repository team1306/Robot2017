package org.usfirst.frc.team1306.robot.triggers;

/**
 * An enum that represents the possible angles for the D-Pad to be pressed.
 * 
 * @author Finn Voichick
 *
 */
public enum DPadDirection {

	UP(0), UPRIGHT(45), RIGHT(90), DOWNRIGHT(135), DOWN(180), DOWNLEFT(225), LEFT(270), UPLEFT(315);

	/** The angle, in degrees, of this direction. */
	private final int angle;

	/**
	 * Constructs a DPadDirection at a certain angle.
	 * 
	 * @param angle
	 *            the angle for the new DPadDirection to point.
	 */
	private DPadDirection(int angle) {
		this.angle = angle;
	}

	/**
	 * Gets the angle, in degrees, that this direction is pointing.
	 * 
	 * @return the angle of this DPadDirection.
	 */
	public int getAngle() {
		return angle;
	}
}
