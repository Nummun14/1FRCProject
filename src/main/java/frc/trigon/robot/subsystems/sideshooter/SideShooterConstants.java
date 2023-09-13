package frc.trigon.robot.subsystems.sideshooter;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;

public class SideShooterConstants {

    private static final int
            ANGLE_MOTOR_ID = 0,
            SHOOTING_MOTOR_ID = 1;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;
    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;
    private static final double ANGLE_OFfSET = 0;
    static final TalonFX
            ANGLE_MOTOR = new TalonFX(ANGLE_MOTOR_ID),
            SHOOTING_MOTOR = new TalonFX(SHOOTING_MOTOR_ID);
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
        config.Feedback.FeedbackRotorOffset = ANGLE_OFfSET;

        ANGLE_MOTOR.getConfigurator().apply(config);

        TalonFXConfiguration shootingConfig = new TalonFXConfiguration();
        shootingConfig.Audio.BeepOnBoot = false;
        shootingConfig.MotorOutput.Inverted = INVERTED_VALUE;
        shootingConfig.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        SHOOTING_MOTOR.getConfigurator().apply(shootingConfig);
    }

    public enum sideShooterStates {
        MIDDLE_CUBE(-10, -6),
        HIGH_CUBE(50, 16),
        COLLECTING(30, 12);

        final double angle;
        final double voltage;

        sideShooterStates(double angle, double voltage) {
            this.angle = angle;
            this.voltage = voltage;
        }
    }
}
