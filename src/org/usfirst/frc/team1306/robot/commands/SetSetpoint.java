package org.usfirst.frc.team1306.robot.commands;

public class SetSetpoint extends CommandBase {

	Setpoint setpoint;
	
	public SetSetpoint(Setpoint setpoint) {
		requires(shooter);
		requires(turret);
		requires(hood);
		
		this.setpoint = setpoint;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		shooter.setRPM(setpoint.getShooterRPM(),setpoint.getIndexerRPM());
		turret.moveRot(setpoint.getTurretPos() + turret.getPosition());
		hood.setPos(setpoint.getHoodAngle());
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
