package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.axis;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.joystick;
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
		if(OI.getJoyVal(controller.s,joystick.l,axis.x) != 0) {
			turret.setSpeed(OI.getJoyVal(controller.s, joystick.l, axis.x) * -0.2);
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
