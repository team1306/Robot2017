package org.usfirst.frc.team1306.robot.commands.shooter;

import java.util.ArrayList;
import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.shooter.AdjustHood.HoodAngle;
import edu.wpi.first.wpilibj.Timer;

/**
 * @AutoFireFuel
 * 
 * @author Jackson Goth
 */
public class AutoFireFuel extends CommandBase {

	private double averagedDist, accumulator;
	private double shooterSpeedRangeUp = Constants.HOOD_MAX_UP_SPEED - Constants.HOOD_MIN_UP_SPEED;
	private double shooterSpeedRangeDown = Constants.HOOD_MAX_DOWN_SPEED - Constants.HOOD_MIN_DOWN_SPEED;
	private ArrayList<Double> distList;
	private Timer hopperTimer;
	private Timer hoodTimer; //Timer to prevent hood from rapidly changing state
	
	public AutoFireFuel() {
		requires(shooter);
		
		distList = new ArrayList<Double>();
		hopperTimer = new Timer();
		hoodTimer = new Timer();
	}
	
	@Override
	protected void initialize() {
		hoodTimer.reset();
		hoodTimer.start();
	}

	@Override
	protected void execute() {
		
		if(distList.size() < 10) { //Fills up initial array
			distList.add(vision.getDist());
		} else {
			distList.remove(0); //Removes oldest data from list
			distList.add(vision.getDist()); //Adds newest data to the top of the list
		}
		
		accumulator = 0;
		for(int i = 0; i < distList.size(); i++) {
			accumulator += distList.get(i);
		}
		averagedDist = accumulator / distList.size();
		
		if(vision.seeTarget()) {
			
//			HoodAngle currentAngle = shooter.getHoodAngle();
//			
//			if(hoodTimer.hasPeriodPassed(Constants.HOOD_RECOVERY_TIME)) {
//				if(averagedDist > Constants.HOOD_SWITCH_DIST && currentAngle.equals(HoodAngle.UP)) {
//					shooter.setHoodAngle(HoodAngle.DOWN);
//					hoodTimer.reset();
//					hoodTimer.start();
//				} else if(currentAngle.equals(HoodAngle.DOWN)) {
//					shooter.setHoodAngle(HoodAngle.UP);
//					hoodTimer.reset();
//					hoodTimer.start();
//				}
//			}
//			
//			double switchDist = Constants.HOOD_SWITCH_DIST;
//			
//			if(currentAngle.equals(HoodAngle.UP)) {
//				shooter.spinShooter((averagedDist / switchDist) * shooterSpeedRangeUp);
//			} else {
//				shooter.spinShooter(((averagedDist - switchDist) / Constants.SHOOT_MAX_DIST - switchDist) * shooterSpeedRangeDown);
//			}
			
//			shooter.spinShooter(((averagedDist - switchDist) / Constants.SHOOT_MAX_DIST - switchDist) * shooterSpeedRangeDown);
			
			shooter.spinIndexer();
			
	    	if(hopperTimer.hasPeriodPassed(Constants.SHOOTER_SPIN_UP_TIME)) {
				shooter.spinHopper();
				intake.spinIntake();
	    	}
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		shooter.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}

