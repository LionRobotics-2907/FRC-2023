// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class Drivetrain extends SubsystemBase {
  public CANSparkMax leftFrontSparkMax = null;
  public CANSparkMax leftBackSparkMax = null;
  public CANSparkMax rightFrontSparkMax = null;
  public CANSparkMax rightBackSparkMax = null;
  MotorControllerGroup leftMotors = null;
  MotorControllerGroup rightMotors = null;
  public DifferentialDrive differentialDrive;
  public RelativeEncoder RFdriveEncoder;
  public AHRS ahrs;
  public double ACCELLIMIT = 2.5;
  public SlewRateLimiter filter;

  /** Creates a new Drivetrain. */
  public Drivetrain() { //be sure to match ports up with actual robot
   leftFrontSparkMax = new CANSparkMax(Constants.DRIVETRAIN_LEFT_FRONT_SPARKMAX, MotorType.kBrushless);
   leftBackSparkMax = new CANSparkMax(Constants.DRIVETRAIN_LEFT_BACK_SPARKMAX, MotorType.kBrushless);
   rightFrontSparkMax = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_FRONT_SPARKMAX, MotorType.kBrushless);
   rightBackSparkMax = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_BACK_SPARKMAX, MotorType.kBrushless);

   leftMotors = new MotorControllerGroup(leftFrontSparkMax, leftBackSparkMax);
   rightMotors = new MotorControllerGroup(rightFrontSparkMax, rightBackSparkMax);
   rightMotors.setInverted(true); //may need to comment thjs out or switch to left

   differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

   RFdriveEncoder = rightFrontSparkMax.getEncoder();//remember position going forward will negative. It is inverted
   ahrs = new AHRS(SPI.Port.kMXP);

   filter = new SlewRateLimiter(ACCELLIMIT);

  // driveEncoder = new Encoder()
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed) {

    moveSpeed = filter.calculate(moveSpeed * 1.0);
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);

  }

  @Override
  public void periodic() {
   // SmartDashboard.putNumber("Joystick X", RobotContainer.driverController.getX());
   //getbusVoltage tells us the voltage fed into the motor controller
   SmartDashboard.putNumber("LF Voltage", leftFrontSparkMax.getBusVoltage());
   SmartDashboard.putNumber("RF Voltage", rightFrontSparkMax.getBusVoltage());
   SmartDashboard.putNumber("LB Voltage", leftBackSparkMax.getBusVoltage());
   SmartDashboard.putNumber("RB Voltage", rightBackSparkMax.getBusVoltage());
   //getOutputCurrent() tells us the motor Controllers output current in amps may not need this
   SmartDashboard.putNumber("LF Output", leftFrontSparkMax.getOutputCurrent());
   SmartDashboard.putNumber("RF Output", rightFrontSparkMax.getOutputCurrent());
   SmartDashboard.putNumber("LB Output", leftBackSparkMax.getOutputCurrent());
   SmartDashboard.putNumber("RB Output", rightBackSparkMax.getOutputCurrent());
   //Encoder
   SmartDashboard.putNumber("RF Encoder Position", RFdriveEncoder.getPosition() * -1);//multiply by -1 to set forward positive.
   SmartDashboard.putNumber("RF Encoder Velocity", RFdriveEncoder.getVelocity());

   //navX
   SmartDashboard.putNumber("Turning angle", ahrs.getAngle());
   SmartDashboard.putNumber("Yaw", ahrs.getYaw());
   SmartDashboard.putNumber("Incline", ahrs.getPitch());


    // This method will be called once per scheduler run
  }


  



}//end class
