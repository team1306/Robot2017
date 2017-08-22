package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Axis;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.OI.Joystick;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class ManualTurret extends CommandBase {

	boolean test;
	
	public ManualTurret() {
		requires(turret);
	}
	
	@Override
	protected void initialize() {
		test = true;
	}

	@Override
	protected void execute() {
		if(OI.getJoyVal(Controller.S,Joystick.L,Axis.X) != 0) {
			turret.setSpeed(OI.getJoyVal(Controller.S, Joystick.L, Axis.X) * -0.2);
			test = false;
		} else if(!test){
			turret.stopAll();
			test = true;
		} else {
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		turret.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
