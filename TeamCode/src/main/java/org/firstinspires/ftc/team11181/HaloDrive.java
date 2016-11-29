package org.firstinspires.ftc.team11181;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.internal.FtcOpModeRegister;

@TeleOp(name = "HaloDrive OpMode", group = "Iterative Opmode")
public class HaloDrive extends OpMode {
    public DcMotor[] motors;

    public HaloDrive() {
        super();
    }

    /**
     * Because of this method
     * motors[0] is the left motor
     * motors[1] is the right motor
     */
    @Override
    public void init() {
        // Remember arrays start at 0(zero)
        motors = new DcMotor[]{
                hardwareMap.dcMotor.get("motor_left"), // motors[0]
                hardwareMap.dcMotor.get("motor_right") // motors[1]
        };
    }

    @Override
    public void loop() {
        float power = -gamepad1.right_stick_y;
        float direction = gamepad1.right_stick_x;

        /**
         * Flips the left motor because its physically flipped
         */
        float left = -power * 20 + direction;
        float right = power * 20 + direction;

        /**
         * Stops the motor from getting to much power
         * Caps the motors at 20.0F
         */
        left = Range.clip(left, -20.0F, 20.0F);
        right = Range.clip(right, -20.0F, 20.0F);

        motors[0].setMaxSpeed(20);
        motors[1].setMaxSpeed(20);

        motors[0].setPower(left);
        motors[1].setPower(right);
    }
}
