// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
public class AutoTest extends SequentialCommandGroup {
   /**Auto-Path for when we ARE in front of charge station. If needed you can delete some of these commands
   * or adjust the speeds and distances. There is approximately a 10:1 ration in distance to speed (10 equates to approx 1 foot)
   * This auto path will drive forward and drop game cube to community, we drive backwards and attempt autobalance
   */
  public AutoTest() {
    addCommands(
    
    new WaitCommand(2),
    new EncoderDrive(8,0.6),
    new WaitCommand(1.5),
    new AutoBalance()
      
    );
    // Use addRequirements() here to declare subsystem dependencies.

  }

}
