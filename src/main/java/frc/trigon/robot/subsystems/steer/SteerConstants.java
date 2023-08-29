package frc.trigon.robot.subsystems.steer;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;

public class SteerConstants {

    private static final int MOTOR_ID = 0;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;
    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;
    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);
    static final double GEAR_RATIO = 12.8;
    static final double
            P = 1,
            I = 0,
            D = 0;


    static {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Audio.BeepOnBoot = false;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        config.Slot0.kP = P;
        config.Slot0.kD = D;
        config.Slot0.kI = I;

        MOTOR.getConfigurator().apply(config);
    }
}
