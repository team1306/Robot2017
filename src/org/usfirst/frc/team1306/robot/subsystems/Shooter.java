package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.Setpoint;
import org.usfirst.frc.team1306.robot.commands.shooter.AdjustHood.HoodAngle;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.VelocityMeasurementPeriod;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Shooter
 * 
 * Shooter subsystem that runs control loops on both shooter motors and the indexer motor, to get them to maintain
 * the rpm for the currently desired setpoint.
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public class Shooter extends Subsystem {

	private final Talon hopperMotor;
	private final CANTalon leftShooterMotor;
	private final CANTalon rightShooterMotor;
	private final CANTalon indexerMotor;
	private final DoubleSolenoid hoodShifter;
	
	private double shooterRPM = Constants.SHOOTER_BOILER_RPM;
	private double indexerRPM = Constants.INDEXER_BOILER_RPM;
	private final static double hopperSpeed = Constants.HOPPER_SPEED;
	
	private HoodAngle angle = HoodAngle.UP;
	private Alliance alliance;
	
	public Shooter() {
		
		leftShooterMotor = new CANTalon(RobotMap.LEFT_SHOOTER_PORT);
		leftShooterMotor.enable();
		rightShooterMotor = new CANTalon(RobotMap.RIGHT_SHOOTER_PORT);
		rightShooterMotor.enable();
		indexerMotor = new CANTalon(RobotMap.INDEXER_TALON_PORT);
		indexerMotor.enable();
		hopperMotor = new Talon(RobotMap.HOPPER_TALON_PORT);
		
		leftShooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftShooterMotor.configEncoderCodesPerRev(12);
		leftShooterMotor.reverseSensor(false);
		leftShooterMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		leftShooterMotor.configPeakOutputVoltage(+12.0f, 0.0f);
		leftShooterMotor.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_10Ms);
		leftShooterMotor.SetVelocityMeasurementWindow(20);
		leftShooterMotor.setF(Constants.SHOOTER_F);
		leftShooterMotor.setP(Constants.SHOOTER_P);
		leftShooterMotor.setI(Constants.SHOOTER_I);
		leftShooterMotor.setD(Constants.SHOOTER_D);
		
		rightShooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightShooterMotor.configEncoderCodesPerRev(12);
		rightShooterMotor.reverseSensor(true);
		rightShooterMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		rightShooterMotor.configPeakOutputVoltage(+12.0f, 0.0f);
		rightShooterMotor.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_10Ms);
		rightShooterMotor.SetVelocityMeasurementWindow(20);
		rightShooterMotor.setF(Constants.SHOOTER_F);
		rightShooterMotor.setP(Constants.SHOOTER_P);
		rightShooterMotor.setI(Constants.SHOOTER_I);
		rightShooterMotor.setD(Constants.SHOOTER_D);
		
		indexerMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		indexerMotor.reverseSensor(true);
		indexerMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		indexerMotor.configPeakOutputVoltage(+12.0f, -12.0f);
		rightShooterMotor.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_10Ms);
		rightShooterMotor.SetVelocityMeasurementWindow(20);
		indexerMotor.setF(Constants.INDEXER_F);
		indexerMotor.setP(Constants.INDEXER_P);
		indexerMotor.setI(Constants.INDEXER_I);
		indexerMotor.setD(Constants.INDEXER_D);
		
		hoodShifter = new DoubleSolenoid(3,4);
		setHoodAngle(angle); //Initial hood angle should be up
		
		alliance = DriverStation.getInstance().getAlliance();
	}
	
	/**
	 * Changes the RPM that the shooter and indexer motors will try and maintain
	 * Used to switch between setpoints.
	 */
	public void setRPM(double shooterSpeed, double indexerSpeed) {
		shooterRPM = shooterSpeed;
		indexerRPM = indexerSpeed;
	}
	
	/**
	 * Used to spin the shooter at a specific speed for vision shooting
	 */
	public void spinShooter(double speed) {
		
		if(Constants.SHOOTER_ENABLED) {
			leftShooterMotor.set(speed);
			rightShooterMotor.set(speed);
		}
	}
	
	/**
	 * Spins shooter motors at given RPM with adjustments based on alliance and setpoint.
	 * Adjustments are applied because with dual shooters they will almost always be shooting differen't
	 * distances than each other and that adjustment is reversed when you're on the other alliance.
	 */
	public void spinShooter() {
		
		if(Constants.SHOOTER_ENABLED) {
			leftShooterMotor.changeControlMode(TalonControlMode.Speed);
			rightShooterMotor.changeControlMode(TalonControlMode.Speed);
			
			SmartDashboard.putNumber("shooterRPM",shooterRPM);
			
			if(shooterRPM == Setpoint.AUTO_HOPPER.shooterSpeed) {
				if(alliance.equals(Alliance.Red)) {
					leftShooterMotor.set(shooterRPM + Setpoint.AUTO_HOPPER.shooterAdj);
					rightShooterMotor.set(shooterRPM);
				} else {
					leftShooterMotor.set(shooterRPM);
					rightShooterMotor.set(shooterRPM + Setpoint.AUTO_HOPPER.shooterAdj);
				}
				
			} else if(shooterRPM == Setpoint.PEG.shooterSpeed) {
				if(alliance.equals(Alliance.Red)) {
					leftShooterMotor.set(shooterRPM + Setpoint.PEG.shooterAdj);
					rightShooterMotor.set(shooterRPM);
				} else {
					leftShooterMotor.set(shooterRPM);
					rightShooterMotor.set(shooterRPM + Setpoint.PEG.shooterAdj);
				}
				
			} else { //Boiler setpoint doesn't need adjustment
				leftShooterMotor.set(shooterRPM);
				rightShooterMotor.set(shooterRPM);
			}
		}
	}
	
	/**
	 * Spins indexer motors at a given RPM based on the current setpoint.
	 */
	public void spinIndexer() {
		if(Constants.INDEXER_ENABLED) {
			indexerMotor.changeControlMode(TalonControlMode.Speed);
			indexerMotor.set(indexerRPM * (24/18));
		}
	}
	
	public void spinHopper() {
		if(Constants.HOPPER_ENABLED) {
			hopperMotor.set(-hopperSpeed);
		}
	}
	
	/**
	 * Sets the position of the hood (Up or Down)
	 */
	public void setHoodAngle(HoodAngle angle) {
		
		if(Constants.HOOD_ENABLED) {
			if(angle.equals(HoodAngle.UP)) {
				hoodShifter.set(DoubleSolenoid.Value.kForward);
				angle = HoodAngle.UP;
			} else {
				hoodShifter.set(DoubleSolenoid.Value.kReverse);
				angle = HoodAngle.DOWN;
			}
		}
		
	}
	
	public HoodAngle getHoodAngle() {
		return angle;
	}
	
	/**
	 * Stops all motors (shooters, indexer, hopper)
	 */
	public void stop() {
		leftShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		rightShooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		indexerMotor.changeControlMode(TalonControlMode.PercentVbus);
		leftShooterMotor.set(0.0);
		rightShooterMotor.set(0.0);
		indexerMotor.set(0.0);
		hopperMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
