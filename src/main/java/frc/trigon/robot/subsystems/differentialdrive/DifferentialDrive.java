package frc.trigon.robot.subsystems.differentialdrive;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DifferentialDrive extends SubsystemBase {

    private final static DifferentialDrive INSTANCE = new DifferentialDrive();

    public static DifferentialDrive getInstance() {
        return INSTANCE;
    }

    private DifferentialDrive() {
    }


}

