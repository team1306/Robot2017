package org.usfirst.frc.team1306.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1306.robot.commands.QuickTurn;
import org.usfirst.frc.team1306.robot.commands.spinIntake;
import org.usfirst.frc.team1306.robot.subsystems.Intake;

//import org.usfirst.frc.team1306.robot.commands.SmartQuickTurn;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private final XboxController primaryController;
	private final XboxController secondaryController;
	
	private final Button pbuttonA;
	private final Button pbuttonB;
	private final Button pbuttonX;
	private final Button pbuttonY;
	private final Button pbuttonRB;
	private final Button pbuttonLB;
	private final Button pbuttonBack;
	private final Button pbuttonStart;	
	
	private final Button sbuttonA;
	private final Button sbuttonB;
	private final Button sbuttonX;
	private final Button sbuttonY;
	private final Button sbuttonRB;
	private final Button sbuttonLB;
	private final Button sbuttonBack;
	private final Button sbuttonStart;
	
	public static Encoder enc;
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	public OI() {
		primaryController = new XboxController(RobotMap.PRIMARY_PORT);
		secondaryController = new XboxController(RobotMap.SECONDARY_PORT);
		
		pbuttonA = new JoystickButton(primaryController, XboxController.A);
		pbuttonB = new JoystickButton(primaryController, XboxController.B);
		pbuttonX = new JoystickButton(primaryController, XboxController.X);
		pbuttonY = new JoystickButton(primaryController, XboxController.Y);
		pbuttonRB = new JoystickButton(primaryController, XboxController.RB);
		pbuttonLB = new JoystickButton(primaryController, XboxController.LB); 
		pbuttonBack = new JoystickButton(primaryController, XboxController.BACK);
		pbuttonStart = new JoystickButton(primaryController, XboxController.START);
		
		sbuttonA = new JoystickButton(secondaryController, XboxController.A);
		sbuttonB = new JoystickButton(secondaryController, XboxController.B);
		sbuttonX = new JoystickButton(secondaryController, XboxController.X);
		sbuttonY = new JoystickButton(secondaryController, XboxController.Y);
		sbuttonRB = new JoystickButton(secondaryController, XboxController.RB);
		sbuttonLB = new JoystickButton(secondaryController, XboxController.LB);
		sbuttonBack = new JoystickButton(secondaryController, XboxController.BACK);
		sbuttonStart = new JoystickButton(secondaryController, XboxController.START);
		
		enc = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
		
		boolean leftTurn = true;
		boolean rightTurn = false;
		//pbuttonRB.whenPressed(new SmartQuickTurn(90));
		//pbuttonLB.whenPressed(new SmartQuickTurn(90));
		pbuttonRB.whileHeld(new QuickTurn(leftTurn));
		pbuttonLB.whileHeld(new QuickTurn(rightTurn));
		sbuttonX.whileHeld(new spinIntake());
		//pbuttonA.whenPressed(new commandName());

	}
	
	
	public enum axis {x, y};
	public enum controller {p, s};
	public enum trigger {l, r};
	public enum joystick {l, r};
	/**
	 * Returns y axis value of right joystick
	 * @param axis
	 * 		X or Y axis to return
	 * @return
	 * 		Returns the value from the selected axis
	 * @deprecated 
	 * 		Use getJoyVal instead
	 */
	public double getRightJoyVal(controller controller, axis axis) {
		return Math.pow(deadband(primaryController.getY(Hand.kRight)), Constants.JOYSTICK_MULTIPLIER);
	}
	
	/**
	 * Returns y axis value of left joystick
	 * @param axis
	 * 		X or Y axis to return
	 * @return
	 * 		Returns the value from the selected axis
	 * @deprecated 
	 * 		Use getJoyVal instead
	 */
	public double getLeftJoyVal(controller controller, axis axis) {
		return Math.pow(deadband(primaryController.getY(Hand.kLeft)), Constants.JOYSTICK_MULTIPLIER);
	}
	
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
	public double getTriggerVal(controller controller, trigger trigger) {
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
	
	public boolean getButtonVal(controller controller, int button) {
		boolean returnVal = false;
		
		//controller.getRawButton(button);
		
		return returnVal;
	}
	
	/*
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

