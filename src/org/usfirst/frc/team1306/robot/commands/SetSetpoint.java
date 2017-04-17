package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

/**
 * Command that does all the adjustments necessary to shoot at a given setpoints; sets shooter rpm, indexer rpm, hood angle, turret position.
 * @author Jackson Goth
 */
public class SetSetpoint extends CommandBase {

	Setpoint setpoint;
	Alliance alliance;
	
	public SetSetpoint(Setpoint setpoint) {
		requires(shooter);
		requires(turret);
		requires(hood);
		
		this.setpoint = setpoint; //Initializing setpoint
		this.alliance = DriverStation.getInstance().getAlliance(); //Finding out which alliance the robot is on
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		shooter.setRPM(setpoint.getShooterRPM(), setpoint.getIndexerRPM()); //Adjusts desired motor rpm for shooters and indexers
		hood.setPos(setpoint.getHoodAngle()); //Adjusts the hood angle for expected distance
		
		if(alliance.equals(Alliance.Blue)) { //If on blue alliance, turn turret to this angle
			turret.moveRot(-setpoint.getTurretPos());
		} else { //Otherwise you are on red alliance, in which case you turn turret to opposite angle
			turret.moveRot(setpoint.getTurretPos());
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
