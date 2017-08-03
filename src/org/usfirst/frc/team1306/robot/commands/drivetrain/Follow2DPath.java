package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;

/**
 * @Follow2DPath
 * 
 * @author Jackson Goth
 */
public class Follow2DPath extends CommandBase {

	private Profile2D profile;
	private Timer timer;
	private int counter;
	
	public Follow2DPath(Profile2D p) {
		requires(drivetrain);
		profile = p;
		
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		
		/* Resets the timer that will determine when the command will end */
		timer.reset();
		timer.start();
		
		drivetrain.leftMotors.changeControlMode(TalonControlMode.Speed);
		drivetrain.rightMotors.changeControlMode(TalonControlMode.Speed);
		drivetrain.leftMotors.setEncPos(0);
		drivetrain.rightMotors.setEncPos(0);
		
		counter = 0; //Resets counter that's used to determine position in profile
	}

	@Override
	protected void execute() {
		
		counter = (int) (timer.get() / 0.01);
		
		double leftSpeed = profile.leftPath.path.get(counter).velocity;
		double rightSpeed = profile.rightPath.path.get(counter).velocity;
		
		double leftError = profile.leftPath.path.get(counter).position - (Math.abs(drivetrain.leftMotors.getEncPos()/1024)*12.5663);
		double rightError = profile.rightPath.path.get(counter).position - (Math.abs(drivetrain.rightMotors.getEncPos()/1024)*12.5663);
		leftError *= 1;
		rightError *= 1;
		
		drivetrain.driveSpeed(((leftSpeed+leftError)/12.5663)*60,((rightSpeed+rightError)/12.5663)*60);
	}

	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(profile.maxTime);
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
