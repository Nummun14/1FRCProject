package frc.trigon.robot.subsystems.sideshooter;


import com.ctre.phoenixpro.controls.PositionVoltage;
import com.ctre.phoenixpro.controls.VoltageOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.*;

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

    public CommandBase getSetTargetAngleAndShoot(SideShooterConstants.sideShooterStates state){
        return new SequentialCommandGroup(
                getSetTargetAngle(state).until(() -> angleMotor.getPosition().getValue() == state.angle / 360)
        );
    }

    private CommandBase getShoot(SideShooterConstants.sideShooterStates state){
        return new StartEndCommand(
                () -> shoot(state),
                this::stopShootingMotor,
                this
        );
    }

    private CommandBase getSetTargetAngle(SideShooterConstants.sideShooterStates state){
        return new StartEndCommand(
                () -> setTargetAngle(state),
                this::stopAngleMotor,
                this
        );
    }

    private void setTargetAngle(SideShooterConstants.sideShooterStates state){
        PositionVoltage anglePosition = new PositionVoltage(state.voltage);
        angleMotor.setControl(anglePosition);
    }

    private void shoot(SideShooterConstants.sideShooterStates state){
        VoltageOut voltage = new VoltageOut(state.voltage);
        shootingMotor.setControl(voltage);
    }

    private void stopShootingMotor(){
        shootingMotor.stopMotor();
    }

    private void stopAngleMotor(){
        angleMotor.stopMotor();
    }
    private void stopAll(){
        angleMotor.stopMotor();
        shootingMotor.stopMotor();
    }
}

