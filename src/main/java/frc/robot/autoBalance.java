package frc.robot;
import com.kauailabs.navx.frc.AHRS;

public class autoBalance {

    private AHRS navX;
    private int state;
    private int debounceCount;
    private double robotSpeedSlow;
    private double robotSpeedFast;
    private double onChargeStationDegree;
    private double levelDegree;
    private double debounceTime;

    public autoBalance() {
      navX = RobotContainer.m_drivetrain.ahrs;
      state = 0;
      debounceCount = 0;

      robotSpeedFast = 0.55; //This is the robot's initial speed in phase 1 before it docks onto charge station
      robotSpeedSlow = 0.3;  //This is the robot's speed once it engages with the charge station (when it's tilted)

      onChargeStationDegree = 15;//This is the angle at which the robot knows it is on the charge station
      levelDegree = 3.9; //This is the angle at which the robot is completely balanced/engaged on the charge station
                        //i.e. when the angle at which the robot knows it's balanced (used for exiting program)

      debounceTime = 0.2;

    }

    
      public int secondsToTicks(double time){
        return (int)(time*50);
    }

    public double autoBalanceRoutine(){
        switch (state){
            //drive forwards to approach station, exit when tilt is detected
            case 0:
                if(navX.getPitch() > onChargeStationDegree){
                    debounceCount++;
                }
                if(debounceCount > secondsToTicks(debounceTime)){
                    state = 1;
                    debounceCount = 0;
                    return robotSpeedSlow;
                }
                return robotSpeedFast;
            //driving up charge station, drive slower, stopping when level
            case 1:
                if (navX.getPitch() < levelDegree){
                    debounceCount++; 
                }
                if(debounceCount > secondsToTicks(debounceTime)){
                    state = 2;
                    debounceCount = 0;
                    return 0;
                }
                return robotSpeedSlow;
            //on charge station, stop motors and wait for end of auto
            case 2:
                if(Math.abs(navX.getPitch()) <= levelDegree/2){
                    debounceCount++;
                }
                if(debounceCount>secondsToTicks(debounceTime)){
                    state = 4;
                    debounceCount = 0;
                    return 0;
                }
                if(navX.getPitch() >= levelDegree) {
                    return 0.25;//The speed at which the robot makes adjustments to balance on the charge station
                } else if(navX.getPitch() <= -levelDegree) {
                    return -0.25;//The speed at which the robot makes adjustments to balance on the charge station
                }
            case 3:
                return 0;
        }
        return 0;
    }




}
