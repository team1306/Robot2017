package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.drivetrain.QuickTurn;
import org.usfirst.frc.team1306.robot.commands.hood.AdjustHood;
import org.usfirst.frc.team1306.robot.commands.hood.HoodAngle;
import org.usfirst.frc.team1306.robot.commands.intake.SpinIntake;
import org.usfirst.frc.team1306.robot.commands.shooter.BangSpinShooter;
import org.usfirst.frc.team1306.robot.commands.turret.Scan;
import org.usfirst.frc.team1306.robot.commands.turret.ScanDirection;
import org.usfirst.frc.team1306.robot.commands.turret.TurnTurret;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//Declare primary and secondary xbox controllers
	private static XboxController primaryController = null;
	private static XboxController secondaryController = null;
	
	//Declare buttons on primary controller
	private final Button pbuttonA;
	private final Button pbuttonB;
	private final Button pbuttonX;
	private final Button pbuttonY;
	private final Button pbuttonRB;
	private final Button pbuttonLB;
	private final Button pbuttonStart;	
	
	//Declare buttons on secondary controller
	private final Button sbuttonA;
	private final Button sbuttonB;
	private final Button sbuttonY;
	private final Button sbuttonRB;
	private final Button sbuttonLB;
	
	public OI() {
		//Declare ports of xbox controllers
		primaryController = new XboxController(RobotMap.PRIMARY_PORT);
		secondaryController = new XboxController(RobotMap.SECONDARY_PORT);
		
		//Map buttons to xbox controller buttons for primary controller
		pbuttonA = new JoystickButton(primaryController, XboxController.A);
		pbuttonB = new JoystickButton(primaryController, XboxController.B);
		pbuttonX = new JoystickButton(primaryController, XboxController.X);
		pbuttonY = new JoystickButton(primaryController, XboxController.Y);
		pbuttonRB = new JoystickButton(primaryController, XboxController.RB);
		pbuttonLB = new JoystickButton(primaryController, XboxController.LB); 
		pbuttonStart = new JoystickButton(primaryController, XboxController.START);
		
		//Map buttons to xbox controller buttons for secondary controller
		sbuttonA = new JoystickButton(secondaryController, XboxController.A);
		sbuttonB = new JoystickButton(secondaryController, XboxController.B);
		sbuttonY = new JoystickButton(secondaryController, XboxController.Y);
		sbuttonRB = new JoystickButton(secondaryController, XboxController.RB);
		sbuttonLB = new JoystickButton(secondaryController, XboxController.LB);
		
		//Bind commands to buttons
		pbuttonRB.whileHeld(new QuickTurn(true));
		pbuttonLB.whileHeld(new QuickTurn(false));
		
		pbuttonA.whenPressed(new BangSpinShooter());
		//pbuttonA.whenPressed(new SpinShooter()); Use bang bang instead
		//pbuttonB.whenPressed(new ResetTurret());
		pbuttonX.whenPressed(new SpinIntake());
		//pbuttonY.whenPressed(new Scan());
		//pbuttonStart.whenPressed(new Climb());
		pbuttonStart.whenPressed(new TurnTurret()); //Testing
		
		//sbuttonA.whenPressed(new AdjustHood(HoodAngle.DOWN));
		//sbuttonY.whenPressed(new AdjustHood(HoodAngle.UP));
		//sbuttonRB.whenPressed(new Scan(ScanDirection.RIGHT));
		//sbuttonLB.whenPressed(new Scan(ScanDirection.LEFT));
		//sbuttonB.whenPressed(new ReverseDrive());
		
	}
	
	public enum axis {x, y}; 		//X or Y Axis on Joystick
	public enum controller {p, s}; 	//Primary or Secondary Controller
	public enum trigger {l, r}; 	//Left or Right Trigger
	public enum joystick {l, r}; 	//Left or Right Joystick
	
	/**
	 * Returns the joystick value (from -1.0 to 1.0) for the specified controller's joystick's axis
	 * @param controller
	 * 		The controller that you would like to read from (p or s)
	 * @param joystick
	 * 		The joystick that you would like to read from (l or r)
	 * @param axis
	 * 		The axis that you would like to read (x or y)
	 * @return
	 * 		Returns the value of the specified controller's joystick's axis (from -1.0 to 1.0)
	 */
	public double getJoyVal(controller controller, joystick joystick, axis axis) {
		double returnVal = 0.0;
		switch (controller) {
			case p:
				switch (joystick) {
					case l:
						switch (axis) {
							case x:
								returnVal = Math.pow(deadband(primaryController.getXNew(Hand.kLeft)), Constants.JOYSTICK_MULTIPLIER);
							break;
							case y:
								returnVal = Math.pow(deadband(primaryController.getYNew(Hand.kLeft)), Constants.JOYSTICK_MULTIPLIER);
							break;
						}
					break;
					case r:
						switch (axis) {
						case x:
							returnVal = Math.pow(deadband(primaryController.getXNew(Hand.kRight)), Constants.JOYSTICK_MULTIPLIER);
						break;
						case y:
							returnVal = Math.pow(deadband(primaryController.getYNew(Hand.kRight)), Constants.JOYSTICK_MULTIPLIER);
						break;
					}
					break;
				}
			break;
			case s:
				switch (joystick) {
				case l:
					switch (axis) {
						case x:
							returnVal = Math.pow(deadband(secondaryController.getX(Hand.kLeft)), Constants.JOYSTICK_MULTIPLIER);
						break;
						case y:
							returnVal = Math.pow(deadband(secondaryController.getY(Hand.kLeft)), Constants.JOYSTICK_MULTIPLIER);
						break;
					}
				break;
				case r:
					switch (axis) {
					case x:
						returnVal = Math.pow(deadband(secondaryController.getX(Hand.kRight)), Constants.JOYSTICK_MULTIPLIER);
					break;
					case y:
						returnVal = Math.pow(deadband(secondaryController.getY(Hand.kRight)), Constants.JOYSTICK_MULTIPLIER);
					break;
				}
				break;
			}
			break;
		}
		return returnVal;
	}
	
	/**
	 * Returns the value of the specified trigger (from -1.0 to 1.0)
	 * @param controller
	 * 		Which controller to read (p or s)
	 * @param trigger
	 * 		Which trigger to read (l or r)
	 * @return
	 * 		Returns the specified value
	 */
	public static double getTriggerVal(controller controller, trigger trigger) {
		double returnVal = 0.0;
		switch (controller){
			case p:
				switch (trigger) {
					case l:
						returnVal = primaryController.getLT();
					break;
					case r:
						returnVal = primaryController.getRT();
					break;
				}
			break;
			case s:
				switch (trigger) {
					case l:
						returnVal = secondaryController.getLT();
					break;
					case r:
						returnVal = secondaryController.getRT();
					break;
				}
			break;
		}
		return returnVal;
	}
	
	/**
	 * Returns the value of a specified button on a controller
	 * @param controller
	 * 		The controller that you would like to read from (p or s)
	 * @param button
	 * 		The button to read from (A=1 B=2 X=3 Y=4 LB=5 RB=6 Back=7 Start=8 LS=9 RS=10)
	 */
	public static boolean getButtonVal(controller controller, int button) {
		boolean returnVal = false;
		
		switch(controller) {
			case p:
				returnVal = primaryController.getRawButton(button);
			break;
			case s:
				returnVal = secondaryController.getRawButton(button);
			break;
		}

		return returnVal;
	}
	
	/**
	 * Applies deadband to joystick values to prevent false readings when joystick is idle.  It prevents very small changes to 
	 * an idle joystick to trigger anything.
	 */
	private static double deadband(double value) {
		if (value < -Constants.DEADBAND) {
			return (value + Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		}
		if (value > Constants.DEADBAND) {
			return (value - Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		}
		return 0;
	}
}
