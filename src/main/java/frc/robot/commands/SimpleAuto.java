// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SimpleAuto extends SequentialCommandGroup {
  /**Auto-Path for when we are NOT in front of charge station. If needed you can delete some of these commands
   * or adjust the speeds and distances. There is approximately a 10:1 ration in distance to speed (10 equates to approx 1 foot)
   */
  public SimpleAuto() {
    addCommands(
      new WaitCommand(1.0),
      new EncoderDrive(10,0.6),
      new WaitCommand(2.0),
      new EncoderDrive(-130.0,0.6),//drives backwards
      new WaitCommand(2),
      new GyroTurn(180,0.4)
    );
  }





  // Called every time the scheduler runs while the command is scheduled.

}
