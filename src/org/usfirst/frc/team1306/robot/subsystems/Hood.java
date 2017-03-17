package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.commands.hood.HoodAngle;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem that controls solenoids of the hood to adjust the current position
 * @author Jackson Goth
 */
public class Hood extends Subsystem {

	private static int pos = 0;
	private static String name = "Up";
	
	private final DoubleSolenoid leftHoodShifter;
	private final DoubleSolenoid rightHoodShifter;
	
	public Hood() {
		leftHoodShifter = new DoubleSolenoid(0,1);
		rightHoodShifter = new DoubleSolenoid(2,3);
		
		setPos(HoodAngle.UP);
	}
	
	/**
	 * Sets the position of the hood
	 * @param angle
	 * 		HoodAngle enum (UP or DOWN)
	 */
	public void setPos(HoodAngle angle) {
		
		if(angle.equals(HoodAngle.UP)) {
			leftHoodShifter.set(DoubleSolenoid.Value.kForward);
			rightHoodShifter.set(DoubleSolenoid.Value.kForward);
			name = "Up";
		} else {
			leftHoodShifter.set(DoubleSolenoid.Value.kReverse);
			rightHoodShifter.set(DoubleSolenoid.Value.kReverse);
			name = "Down";
		}
	}
	
	/**
	 * Returns the name of the hood pos (good for smartdash/debug)
	 * @return
	 * 		Pos of hood
	 */
	public String getDir() {
		return name;
	}
	
	/**
	 * Returns the pos of the hood
	 * @return
	 * 		Pos of hood
	 */
	public int getPos() {
		return pos;
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
