package org.usfirst.frc.team1306.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * GearMech subsystem that controls the solenoid pushing out the gear
 * @author Jackson Goth
 */
public class GearMech extends Subsystem {
	
	private final Solenoid gearSolenoid;
	//private final Timer timer;
	
	public GearMech() {
		gearSolenoid = new Solenoid(5);
		//timer = new Timer();
	}

	/**
	 * Deploys the gear onto the peg
	 */
	public void deployGear() {
		gearSolenoid.set(true);
//		timer.reset();
//		timer.start();
//		while(!timer.hasPeriodPassed(Constants.GEAR_DEPLOY_TIME)) {
//		}
		//gearSolenoid.set(false);
	}
	
	/**
	 * Puts the GearMech back into the original position
	 */
	public void reverseGear() {
		gearSolenoid.set(false);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
