package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearMech extends Subsystem {
	
	private final Solenoid gearSolenoid;
	private final Timer timer;
	
	public GearMech() {
		gearSolenoid = new Solenoid(0);
		timer = new Timer();
	}

	public void deployGear() {
		gearSolenoid.set(true);
		//timer.delay(1);
		SmartDashboard.putString("deploying","true");
//		timer.reset();
//		timer.start();
//		while(!timer.hasPeriodPassed(Constants.GEAR_DEPLOY_TIME)) {
//		}
		//gearSolenoid.set(false);
	}
	
	public void reverseGear() {
		gearSolenoid.set(false);
		//timer.delay(1);
		SmartDashboard.putString("deploying","false");
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
