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
    public CommandBase getSideShooterState(SideShooterConstants.sideShooterStates state) {
        return new SequentialCommandGroup(
                getSetTargetAngle(state.voltage).until(() -> getPosition() == state.angle / 360),
                getSetShootVoltage(state.voltage)
        );
    }

    private CommandBase getSetShootVoltage(double voltage) {
        return new StartEndCommand(
                () -> setShootVoltage(voltage),
                this::stopShootingMotor,
                this
        );
    }

    private CommandBase getSetTargetAngle(double voltage) {
        return new StartEndCommand(
                () -> setTargetAngle(voltage),
                this::stopAngleMotor,
                this
        );
    }

    private void setTargetAngle(double voltage) {
        PositionVoltage anglePosition = new PositionVoltage(voltage);
        angleMotor.setControl(anglePosition);
    }

    private void setShootVoltage(double voltage) {
        VoltageOut request = new VoltageOut(voltage);
        shootingMotor.setControl(request);
    }

    private double getPosition() {
        return angleMotor.getPosition().getValue();
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

