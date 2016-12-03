 package org.firstinspires.ftc.team11181;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@SuppressWarnings("unused")
@TeleOp(name = "HaloDrive OpMode", group = "Iterative Opmode")
public class HaloDrive extends OpMode {
    private DcMotor[] motors;
    private CRServo[] servos;

    public HaloDrive() {
        super();
    }

    /**
     * Because of this method
     * motors[0] is the left motor
     * motors[1] is the right motor
     * same with servos
     */
    @Override
    public void init() {
        // Remember arrays start at 0(zero)
        motors = new DcMotor[]{
                hardwareMap.dcMotor.get("motor_left"), // motors[0]
                hardwareMap.dcMotor.get("motor_right"), // motors[1]
                hardwareMap.dcMotor.get("motor_lift"), // motors[2]
                //hardwareMap.dcMotor.get("motor_gun_left"), // motors[3]
                //hardwareMap.dcMotor.get("motor_gun_right") // motors[4]
        };

        servos = new CRServo[]{
                //hardwareMap.crservo.get("servo_trigger") // servos[0]
        };
    }

    @Override
    public void loop() {
        float power = -gamepad1.right_stick_y;
        float direction = gamepad1.right_stick_x;
        boolean gun = gamepad1.right_bumper;
        boolean gunTrigger = gamepad1.left_bumper;
        boolean up = gamepad1.y;
        boolean down = gamepad1.a;
        boolean open = gamepad1.x;
        boolean close = gamepad1.b;

        /*
        if (gun) {
            motors[3].setMaxSpeed(20);
            motors[3].setPower(-1 * Range.clip(20 + 10, -20.0F, 20.0F));
            motors[4].setMaxSpeed(20);
            motors[4].setPower(Range.clip(20 + 10, -20.0F, 20.0F));
        } else {
            motors[3].setMaxSpeed(20);
            motors[3].setPower(-1 * Range.clip(0, -20.0F, 20.0F));
            motors[4].setMaxSpeed(20);
            motors[4].setPower(Range.clip(0, -20.0F, 20.0F));
        }

        if (gunTrigger) {
            servos[0].setPower(Range.clip(20 + 10, -20.0F, 20.0F));
        } else {
            servos[0].setPower(Range.clip(0, -20.0F, 20.0F));
        }
        */

        if (up) {
            motors[2].setPower(Range.clip(20 + 10, -20.0F, 20.0F));
        } else if (down) {
            motors[2].setPower(-1 * Range.clip(20 + 10, -20.0F, 20.0F));
        } else {
            motors[2].setPower(Range.clip(0, -20.0F, 20.0F));
        }

        /*
        if (open) {
            servos[0].setPower(Range.clip(20 + 10, -20.0F, 20.0F));
        } else if (close) {
            servos[0].setPower(-1 * Range.clip(20 + 10, -20.0F, 20.0F));
        } else {
            servos[0].setPower(Range.clip(0, -20.0F, 20.0F));
        }
        */

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