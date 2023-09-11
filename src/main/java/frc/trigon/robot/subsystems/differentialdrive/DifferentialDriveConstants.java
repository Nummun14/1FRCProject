package frc.trigon.robot.subsystems.differentialdrive;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DifferentialDriveConstants {

    private static final int MOTOR1_ID = 1;
    private static final int MOTOR2_ID = 2;
    private static final int MOTOR3_ID = 3;
    private static final int MOTOR4_ID = 4;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;
    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;
    static final TalonFX MOTOR1 = new TalonFX(MOTOR1_ID);
    static final TalonFX MOTOR2 = new TalonFX(MOTOR2_ID);
    static final TalonFX MOTOR3 = new TalonFX(MOTOR3_ID);
    static final TalonFX MOTOR4 = new TalonFX(MOTOR4_ID);
    static final MotorControllerGroup MOTOR_CONTROLLER_GROUP_RIGHT = new MotorControllerGroup(MOTOR1, MOTOR2);
    static final MotorControllerGroup MOTOR_CONTROLLER_GROUP_LEFT = new MotorControllerGroup(MOTOR3, MOTOR4);
    static final DifferentialDrive DIFFERENTIAL_DRIVE1 = new DifferentialDrive(MOTOR_CONTROLLER_GROUP_LEFT, MOTOR_CONTROLLER_GROUP_RIGHT);


    static {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Audio.BeepOnBoot = false;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        MOTOR1.getConfigurator().apply(config);
        MOTOR2.getConfigurator().apply(config);
        MOTOR3.getConfigurator().apply(config);
        MOTOR4.getConfigurator().apply(config);
    }

}