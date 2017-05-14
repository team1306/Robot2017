package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.SetSetpoint;
import org.usfirst.frc.team1306.robot.commands.Setpoint;
import org.usfirst.frc.team1306.robot.commands.climber.Climb;
import org.usfirst.frc.team1306.robot.commands.geartake.AdvancedDeployGeartake;
import org.usfirst.frc.team1306.robot.commands.geartake.DeployGeartake;
import org.usfirst.frc.team1306.robot.commands.geartake.RetractGeartake;
import org.usfirst.frc.team1306.robot.commands.geartake.SpinGeartake;
import org.usfirst.frc.team1306.robot.commands.hood.AdjustHood;
import org.usfirst.frc.team1306.robot.commands.hood.HoodAngle;
import org.usfirst.frc.team1306.robot.commands.intake.SpinIntake;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team1306.robot.commands.turret.ResetTurret;
import org.usfirst.frc.team1306.robot.triggers.DPadDirection;
import org.usfirst.frc.team1306.robot.triggers.DPadPress;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

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
	//private final Button pbuttonStart;
	//private final Button pbuttonBack;
	private final Trigger primaryDPadUp;
	//private final Trigger primaryDPadRight;
	//private final Trigger primaryDPadLeft;
	private final Trigger primaryDPadDown;
	
	//Declare buttons on secondary controller
	private final Button sbuttonA;
	private final Button sbuttonB;
	private final Button sbuttonX;
	private final Button sbuttonY;
	private final Button sbuttonRB;
	private final Button sbuttonLB;
	private final Button sbuttonStart;
	//private final Button sbuttonBack;	
	private final Trigger secondaryDPadUp;
	private final Trigger secondaryDPadRight;
	private final Trigger secondaryDPadLeft;
	private final Trigger secondaryDPadDown;
	
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
		//pbuttonStart = new JoystickButton(primaryController, XboxController.START);
		//pbuttonBack = new JoystickButton(primaryController, XboxController.BACK);
		primaryDPadUp = new DPadPress(primaryController, DPadDirection.UP);
		//primaryDPadRight = new DPadPress(primaryController, DPadDirection.RIGHT);
		//primaryDPadLeft = new DPadPress(primaryController, DPadDirection.LEFT);
		primaryDPadDown = new DPadPress(primaryController, DPadDirection.DOWN);
		
		//Map buttons to xbox controller buttons for secondary controller
		sbuttonA = new JoystickButton(secondaryController, XboxController.A);
		sbuttonB = new JoystickButton(secondaryController, XboxController.B);
		sbuttonX = new JoystickButton(secondaryController, XboxController.X);
		sbuttonY = new JoystickButton(secondaryController, XboxController.Y);
		sbuttonRB = new JoystickButton(secondaryController, XboxController.RB);
		sbuttonLB = new JoystickButton(secondaryController, XboxController.LB);
		sbuttonStart = new JoystickButton(secondaryController, XboxController.START);
		//sbuttonBack = new JoystickButton(secondaryController, XboxController.BACK);
		secondaryDPadUp = new DPadPress(secondaryController, DPadDirection.UP);
		secondaryDPadRight = new DPadPress(secondaryController, DPadDirection.RIGHT);
		secondaryDPadLeft = new DPadPress(secondaryController, DPadDirection.LEFT);
		secondaryDPadDown = new DPadPress(secondaryController, DPadDirection.DOWN);
		
		//Primary Controller Mapping
		pbuttonA.toggleWhenPressed(new SpinShooter(true,Constants.SHOOTER_RPM_SPEED));
		pbuttonB.whenPressed(new ResetTurret());
		pbuttonX.whenPressed(new SpinIntake(false));
		
//		pbuttonLB.whenPressed(new FindTarget(ScanDirection.LEFT));
//		pbuttonRB.whenPressed(new FindTarget(ScanDirection.RIGHT));
		
		primaryDPadUp.whenActive(new AdjustHood(HoodAngle.UP));
		primaryDPadDown.whenActive(new AdjustHood(HoodAngle.DOWN));
		
		
		
		//Secondary Controller Mapping
		sbuttonA.whenPressed(new SpinShooter(false,Constants.SHOOTER_RPM_SPEED));
		sbuttonB.whenPressed(new DeployGeartake());
		sbuttonX.toggleWhenPressed(new SpinIntake(true));
		sbuttonY.whenPressed(new RetractGeartake());
		
		sbuttonLB.whenPressed(new SpinGeartake(Constants.GEARTAKE_SPEED));
		sbuttonRB.whenPressed(new AdvancedDeployGeartake(false));
		
		sbuttonStart.whenPressed(new Climb());
		
		secondaryDPadUp.whenActive(new SetSetpoint(Setpoint.BOILER));
		secondaryDPadLeft.whenActive(new SetSetpoint(Setpoint.PEG));
//		secondaryDPadLeft.whenActive(new SetSetpoint(Setpoint.HOPPER));
		secondaryDPadDown.whenActive(new ResetTurret());
//		secondaryDPadDown.whenActive(new SetSetpoint(Setpoint.AUTO_HOPPER));
//		secondaryDPadDown.whenActive(new ResetTurret());
		
	}
	
	/**
	 * Joystick axis (x or y)
	 * @author Sam Roquitte
	 */
	public enum axis {x, y};
	
	/**
	 * Controller primary or secondary (p or s)
	 * @author Sam Roquitte
	 */
	public enum controller {p, s};
	
	/**
	 * Trigger left or right (l or r)
	 * @author Sam Roquitte
	 */
	public enum trigger {l, r};
	
	/**
	 * Joystick left or right (l or r)
	 * @author Sam Roquitte
	 */
	public enum joystick {l, r};
	
	/**
	 * Side left or right (l, r)
	 * @author Sam Roquitte
	 */
	public enum side {l, r};
	
	/**
	 * Returns the joystick value (from -1.0 to 1.0) for the specified controller's joystick's axis (uses deadband)
	 * @param controller
	 * 		The controller that you would like to read from (p or s)
	 * @param joystick
	 * 		The joystick that you would like to read from (l or r)
	 * @param axis
	 * 		The axis that you would like to read (x or y)
	 * @return
	 * 		Returns the value of the specified controller's joystick's axis (from -1.0 to 1.0)
	 */
	public static double getJoyVal(controller controller, joystick joystick, axis axis) {
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
	 * Returns the value of the specified trigger (from 0.0 to 1.0)
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
	
	/**.
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
	 * Sets the rumble of a controller
	 * @param controller
	 * 		Which controller to set rumble
	 * @param side
	 * 		Which side (l=left r=right)
	 * @param rumbleness
	 * 		RUMBLE!!! (0-1)
	 */
	public static void setRumble(controller controller, side side, double rumbleness) {
		switch (controller) {
			case p:
				switch (side) {
					case l:
						primaryController.setRumble(GenericHID.RumbleType.kLeftRumble, rumbleness);
					break;
					case r:
						primaryController.setRumble(GenericHID.RumbleType.kRightRumble, rumbleness);
					break;
				}
			break;
			case s:
				switch (side) {
				case l:
					secondaryController.setRumble(GenericHID.RumbleType.kLeftRumble, rumbleness);
				break;
				case r:
					secondaryController.setRumble(GenericHID.RumbleType.kRightRumble, rumbleness);
				break;
			}
			break;
		}
	}
	
	/**
	 * Resets the rumble to 0
	 * @param controller
	 * 		Which controller to reset/turn off rumble
	 */
	public static void resetRumble(controller controller) {
		switch (controller) {
			case p:
				primaryController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
				primaryController.setRumble(GenericHID.RumbleType.kRightRumble, 0);
			break;
			case s:
				secondaryController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
				secondaryController.setRumble(GenericHID.RumbleType.kRightRumble, 0);
			break;
		}
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
