package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.SetSetpoint;
import org.usfirst.frc.team1306.robot.commands.Setpoint;
import org.usfirst.frc.team1306.robot.commands.climber.Climb;
import org.usfirst.frc.team1306.robot.commands.geartake.DeployGeartake;
import org.usfirst.frc.team1306.robot.commands.geartake.PlaceGear;
import org.usfirst.frc.team1306.robot.commands.geartake.RetractGeartake;
import org.usfirst.frc.team1306.robot.commands.geartake.SpinGeartake;
import org.usfirst.frc.team1306.robot.commands.hood.AdjustHood;
import org.usfirst.frc.team1306.robot.commands.hood.HoodAngle;
import org.usfirst.frc.team1306.robot.commands.intake.SpinIntake;
import org.usfirst.frc.team1306.robot.commands.shooter.FireFuel;
import org.usfirst.frc.team1306.robot.commands.turret.ResetTurret;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;
import org.usfirst.frc.team1306.robot.triggers.DPadDirection;
import org.usfirst.frc.team1306.robot.triggers.DPadPress;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * @OI
 * 
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow control of the robot.
 * It is also where commands can get joystick/trigger readings and set the rumble on the controller.
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public class OI {
	
	//Declare primary and secondary xbox controllers
	private static XboxController primaryController = null;
	private static XboxController secondaryController = null;
	
	public OI() {
		
		//Declare ports of xbox controllers
		primaryController = new XboxController(RobotMap.PRIMARY_PORT);
		secondaryController = new XboxController(RobotMap.SECONDARY_PORT);
		
		//Declares and maps buttons to xbox controller buttons for primary controller
//		Button pbuttonA = new JoystickButton(primaryController, ControllerButton.A.value);
//		Button pbuttonB = new JoystickButton(primaryController, ControllerButton.B.value);
		Button pbuttonX = new JoystickButton(primaryController, ControllerButton.X.value);
//		Button pbuttonY = new JoystickButton(primaryController, ControllerButton.Y.value);
//		Button pbuttonRB = new JoystickButton(primaryController, ControllerButton.RB.value);
//		Button pbuttonLB = new JoystickButton(primaryController, ControllerButton.LB.value); 
//		Button pbuttonStart = new JoystickButton(primaryController, ControllerButton.START.value);
//		Button pbuttonBack = new JoystickButton(primaryController, ControllerButton.BACK.value);
		Trigger primaryDPadUp = new DPadPress(primaryController, DPadDirection.UP);
//		Trigger primaryDPadRight = new DPadPress(primaryController, DPadDirection.RIGHT);
//		Trigger primaryDPadLeft = new DPadPress(primaryController, DPadDirection.LEFT);
		Trigger primaryDPadDown = new DPadPress(primaryController, DPadDirection.DOWN);
		
		//Declares and maps buttons to xbox controller buttons for secondary controller
		Button sbuttonA = new JoystickButton(secondaryController, ControllerButton.A.value);
		Button sbuttonB = new JoystickButton(secondaryController, ControllerButton.B.value);
		Button sbuttonX = new JoystickButton(secondaryController, ControllerButton.X.value);
		Button sbuttonY = new JoystickButton(secondaryController, ControllerButton.Y.value);
		Button sbuttonRB = new JoystickButton(secondaryController, ControllerButton.RB.value);
		Button sbuttonLB = new JoystickButton(secondaryController, ControllerButton.LB.value);
		Button sbuttonStart = new JoystickButton(secondaryController,ControllerButton.START.value);
//		Button sbuttonBack = new JoystickButton(secondaryController, ControllerButton.BACK.value);
		Trigger secondaryDPadUp = new DPadPress(secondaryController, DPadDirection.UP);
//		Trigger secondaryDPadRight = new DPadPress(secondaryController, DPadDirection.RIGHT);
		Trigger secondaryDPadLeft = new DPadPress(secondaryController, DPadDirection.LEFT);
		Trigger secondaryDPadDown = new DPadPress(secondaryController, DPadDirection.DOWN);
		
		pbuttonX.whenPressed(new SpinIntake(false));
		primaryDPadUp.whenActive(new AdjustHood(HoodAngle.UP));
		primaryDPadDown.whenActive(new AdjustHood(HoodAngle.DOWN));
		
		sbuttonA.whileHeld(new FireFuel());
		sbuttonB.whenPressed(new DeployGeartake());
		sbuttonX.toggleWhenPressed(new SpinIntake(true));
		sbuttonY.whenPressed(new RetractGeartake());
		
		sbuttonLB.whenPressed(new SpinGeartake(Constants.GEARTAKE_SPEED));
		sbuttonRB.whenPressed(new PlaceGear());
		
		sbuttonStart.whenPressed(new Climb());
		
		secondaryDPadUp.whenActive(new SetSetpoint(Setpoint.BOILER));
		secondaryDPadLeft.whenActive(new SetSetpoint(Setpoint.PEG));
		secondaryDPadDown.whenActive(new ResetTurret());
	}
	
	public enum Controller {P,S}; //Controller (primary or secondary)
	public enum Joystick {L,R}; //Joystick (left or right)
	public enum Axis {X,Y}; //Joystick axis (x or y)
	
	public enum PullTrigger {L,R}; //Trigger (left or right)
	
	public enum Side {L,R}; //Side (left or right) (for rumble)
	
	/**
	 * Returns the joystick value (from -1.0 to 1.0) for a specified controller's joystick's axis (uses deadband)
	 */
	public static double getJoyVal(Controller c, Joystick j, Axis a) {
		
		XboxController controller;
		Hand side;
		
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		if(j.equals(Joystick.L)) { side = Hand.kLeft; }
		else { side = Hand.kRight; }
		
		if(a.equals(Axis.X)) {
			return Math.pow(deadband(controller.getXAxis(side)),Constants.JOYSTICK_MULTIPLIER);
		} else {
			return Math.pow(deadband(controller.getYAxis(side)),Constants.JOYSTICK_MULTIPLIER);
		}
	}
	
	/**
	 * Returns the value of the specified trigger (from 0.0 to 1.0)
	 */
	public static double getTriggerVal(Controller c, PullTrigger t) {
		
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		if(t.equals(PullTrigger.L)) {
			return controller.getLT();
		} else {
			return controller.getRT();
		}
	}
	
	/**.
	 * Returns the value of a specified button on a specified controller
	 */
	public static boolean getButtonStatus(Controller c, ControllerButton b) {
		
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		return controller.getRawButton(b.value);
	}
	
	/**
	 * Sets the rumble of a specified controller to a specified amount of rumble
	 */
	public static void setRumble(Controller c, Side s, double rumbleness) {
		
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		if(s.equals(Side.L)) {
			controller.setRumble(GenericHID.RumbleType.kLeftRumble, rumbleness);
		} else {
			controller.setRumble(GenericHID.RumbleType.kRightRumble, rumbleness);
		}
	}
	
	/**
	 * Resets the rumble on the specified side of a specified controller
	 */
	public static void resetRumble(Controller c, Side s) {
		
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		if(s.equals(Side.L)) {
			controller.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
		} else {
			controller.setRumble(GenericHID.RumbleType.kRightRumble, 0);
		}
	}
	
	public static double getTriggerStatus() {
		return primaryController.getLT();
	}
	
	/**
	 * Applies deadband to joystick values to prevent false readings when joystick is idle. It prevents 
	 * very small changes to an idle joystick to trigger anything.
	 */
	private static double deadband(double value) {
		if (value < -Constants.DEADBAND) {
			return (value + Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		} else if (value > Constants.DEADBAND) {
			return (value - Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		} else {
			return 0;
		}
	}
}