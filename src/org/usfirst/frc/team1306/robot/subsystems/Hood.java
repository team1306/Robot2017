package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.commands.hood.HoodAngle;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem that controls hood
 * @author Jackson Goth
 */
public class Hood extends Subsystem {

	private static int pos = 0;
	private static String name = "Up";
	
	private final DoubleSolenoid leftHoodShifter;
	private final DoubleSolenoid rightHoodShifter;
	
	public Hood() {
		leftHoodShifter = new DoubleSolenoid(1,2);
		rightHoodShifter = new DoubleSolenoid(3,4);
		
		setPos(HoodAngle.UP);
	}
	
	public void setPos(HoodAngle angle) {
		pos = angle.getDir();
		name = angle.getName();
		if(pos == 0) {
			leftHoodShifter.set(DoubleSolenoid.Value.kForward);
			rightHoodShifter.set(DoubleSolenoid.Value.kForward);
		} else {
			leftHoodShifter.set(DoubleSolenoid.Value.kReverse);
			rightHoodShifter.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	public String getPos() {
		return name;
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
