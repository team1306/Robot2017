package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

/**
 * @SetSetpoint
 * 
 * Command that changes the RPM the shooter/indexer motors will run at, turns the turret to a given angle, and raise or lowers the hood
 * to score fuel from a pre-planned spot on the field.
 *
 * @author Jackson Goth
 */
public class SetSetpoint extends CommandBase {

	private Setpoint setpoint;
	private Alliance alliance;
	
	public SetSetpoint(Setpoint s) {
		
		setpoint = s;
		alliance = DriverStation.getInstance().getAlliance(); //Finding out what alliance we are on to determine which direction turret should turn
	}

	@Override
	protected void execute() {
		
		shooter.setRPM(setpoint.shooterSpeed, setpoint.indexerSpeed); //Changes the RPM the shooter and indexer motors will run at
		shooter.setHoodAngle(setpoint.angle); //Actuates hood up or down if the setpoint requires it
		
		if(alliance.equals(Alliance.Blue)) { //If on blue alliance, turn turret to this angle
			turret.moveRot(-setpoint.getTurretRot());
		} else { //Otherwise you are on red alliance, in which case you turn turret to opposite angle
			turret.moveRot(setpoint.getTurretRot());
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
