package org.usfirst.frc.team1306.robot.commands.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that turns the drivetrain to a give degree
 * @author Jackson Goth
 */
public class AngledTurn extends CommandBase {

	private double initDegree, currentAngle, accumulator;
	private double degree;
	private final double tolerance = 1;
	private ArrayList<Double> angleList;
	
	public AngledTurn(double degree) {
		requires(drivetrain);
		requires(gyro);
		this.degree = degree;
		
		angleList = new ArrayList<Double>();
	}

	@Override
	protected void initialize() {
		initDegree = gyro.getAngle();
	}

	@Override
	protected void execute() {
		
		if(angleList.size() < 5) { //Fills up initial array
			angleList.add(gyro.getAngle() - initDegree);
		} else {
			angleList.remove(0); //Removes oldest data from list
			angleList.add(gyro.getAngle() - initDegree); //Adds newest data to the top of the list
		}
		
		//Finds the average of yawList and puts it in averagedYaw
		accumulator = 0;
		for(int i = 0; i < angleList.size(); i++) {
			accumulator += angleList.get(i);
		}
		currentAngle = accumulator / angleList.size();
		
		if(currentAngle < degree) {
//			drivetrain.tankDrive(0.3, -0.3);
		} else {
//			drivetrain.tankDrive(-0.3, 0.3);
		}
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(degree - currentAngle) < 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
