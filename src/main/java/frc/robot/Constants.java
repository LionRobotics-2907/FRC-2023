// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

//SparkMax motor controllers
public static final int DRIVETRAIN_LEFT_FRONT_SPARKMAX = 2;//old was 13
public static final int DRIVETRAIN_LEFT_BACK_SPARKMAX = 3;//old was 1
public static final int DRIVETRAIN_RIGHT_FRONT_SPARKMAX = 15;//old was 2
public static final int DRIVETRAIN_RIGHT_BACK_SPARKMAX = 14;//old was 3
public static final int GRIPPER_SPARKMAX = 13;
public static final int WINCH_SPARKMAX = 0;

//Joystick
public static final int DRIVER_CONTROLLER = 0;
public static final int MANI_CONTROLLER = 1;
public static final int DRIVER_CONTROLLER_MOVE_AXIS = 1; 
public static final int DRIVER_CONTROLLER_ROTATE_AXIS = 4;

//encoder 
public static final int EncoderCPR = 42;
public static final double wheelDiameter = 6;
public static final double gearRatioA = 16.37;
public static final double wheelCircumference = wheelDiameter * Math.PI;
public static final double gearRatioB = 7.56;
public static final double DistancePerPulseInch = (1/EncoderCPR) / (gearRatioA)  * wheelCircumference;//may need to change

public static final int SOLENOID = 0;

}//end constants
