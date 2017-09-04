package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Axis;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.OI.Joystick;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class ManualTurret extends CommandBase {

	public ManualTurret() {
		requires(turret);
	}

	@Override
	protected void execute() {
		if(OI.getJoyVal(Controller.S,Joystick.L,Axis.X) < 0 - Constants.DEADBAND || OI.getJoyVal(Controller.S,Joystick.L,Axis.X) > 0 + Constants.DEADBAND) {
			turret.setSpeed(OI.getJoyVal(Controller.S, Joystick.L, Axis.X) * -0.2);
		} else {
			turret.stop();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
