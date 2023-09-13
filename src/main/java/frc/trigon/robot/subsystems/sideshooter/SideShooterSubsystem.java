package frc.trigon.robot.subsystems.sideshooter;


import com.ctre.phoenixpro.controls.PositionVoltage;
import com.ctre.phoenixpro.controls.VoltageOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SideShooterSubsystem extends SubsystemBase {

    private final static SideShooterSubsystem INSTANCE = new SideShooterSubsystem();

    private final TalonFX
            shootingMotor = SideShooterConstants.SHOOTING_MOTOR,
            angleMotor = SideShooterConstants.ANGLE_MOTOR;

    public static SideShooterSubsystem getInstance() {
        return INSTANCE;
    }

    private SideShooterSubsystem() {
    }

    /**
     * Creates a CommandBase that sets the target angle for a side shooter mechanism
     * and initiates shooting when the specified state is reached.
     *
     * @param state The desired side shooter state to set and shoot.
     * @return A CommandBase for setting the target angle and shooting.
     */
    public CommandBase getSetTargetAngleAndShoot(SideShooterConstants.sideShooterStates state) {
        return new SequentialCommandGroup(
                getSetTargetAngle(state).until(() -> angleMotor.getPosition().getValue() == state.angle / 360)
        );
    }

    private CommandBase getShoot(SideShooterConstants.sideShooterStates state) {
        return new StartEndCommand(
                () -> shoot(state),
                this::stopShootingMotor,
                this
        );
    }

    private CommandBase getSetTargetAngle(SideShooterConstants.sideShooterStates state) {
        return new StartEndCommand(
                () -> setTargetAngle(state),
                this::stopAngleMotor,
                this
        );
    }

    private void setTargetAngle(SideShooterConstants.sideShooterStates state) {
        PositionVoltage anglePosition = new PositionVoltage(state.voltage);
        angleMotor.setControl(anglePosition);
    }

    private void shoot(SideShooterConstants.sideShooterStates state) {
        VoltageOut voltage = new VoltageOut(state.voltage);
        shootingMotor.setControl(voltage);
    }

    private void stopShootingMotor() {
        shootingMotor.stopMotor();
    }

    private void stopAngleMotor() {
        angleMotor.stopMotor();
    }

    private void stopAll() {
        angleMotor.stopMotor();
        shootingMotor.stopMotor();
    }
}

