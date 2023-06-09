// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gripper extends SubsystemBase {
  public CANSparkMax gripper = null;
  /** Creates a new Gripper. */
  public Gripper() {
    gripper = new CANSparkMax(Constants.GRIPPER_SPARKMAX, MotorType.kBrushless);
  }

  public void manualControl(double input) {
    gripper.set(input);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
