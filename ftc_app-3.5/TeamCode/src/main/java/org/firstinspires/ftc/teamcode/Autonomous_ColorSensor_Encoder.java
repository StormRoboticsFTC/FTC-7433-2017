package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by 7433 on 11/18/2017.
 */
/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENC
E OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */




@Autonomous(name="AutonomousColorSensorEncoder", group="Autonomous")  // @Autonomous(...) is the other common choice

public class Autonomous_ColorSensor_Encoder extends LinearOpMode {
    private ColorSensor ColorSensor = null;

    /* Declare OpMode members. */


//ColorSensor color_sensor;

    private boolean hardCode = true;

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor jewelMotor = null;
    private DcMotor pulleyMotor = null;
    private Servo rightArmServo = null;
    private Servo leftArmServo = null;
    int nLoopCount = 0;
   // private ColorSensor ColorSensor = null;
    //private ColorSensor color_sensor = null;
    //private Servo jewelServo = null;
    //private int programState = 1;

 /* static final double     COUNTS_PER_MOTOR_REV    = 1368 ;    // eg: TETRIX Motor Encoder
  static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
  static final double     WHEEL_DIAMETER_INCHES   = 3.8 ;     // For figuring circumference
  static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
          (WHEEL_DIAMETER_INCHES * 3.1415);
          */
    //static final double     DRIVE_SPEED             = 4.6;
    //static final double     TURN_SPEED              = 1.5;

    @Override
    public void runOpMode() throws InterruptedException {
      /*  telemetry.addData("Status", "Initialized");
        telemetry.update();

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        boolean bLedOn = true;
        ColorSensor.enableLed(bLedOn);
        */
      //  float havValues[] = {OF, OF, OF};

       /* eg: Initialize the hardware variables. Note that the strings used here as parameters
        * to 'get' must correspond to the names assigned during the robot configuration
        * step (using the FTC Robot Controller app on the phone).
        */
        leftMotor  = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        rightArmServo = hardwareMap.servo.get("rightArmServo");
        leftArmServo = hardwareMap.servo.get("leftArmServo");
        jewelMotor = hardwareMap.dcMotor.get("jewelMotor");
        pulleyMotor = hardwareMap.dcMotor.get("pulleyMotor");
         ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        boolean bLedOn = true;
        ColorSensor.enableLed(bLedOn);

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        // rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //leftMotor = hardwareMap.dcMotor.get("leftMotor");
        //rightMotor = hardwareMap.dcMotor.get("rightMotor");
        //jewelServo = hardwareMap.servo.get("jewelServo");
        //color_sensor = hardwareMap.colorSensor.get("color");

        // eg: Set the drive motor directions:
        // Reverse the motor that runs backwards when connected directly to the battery
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        jewelMotor.setDirection(DcMotor.Direction.REVERSE);
        pulleyMotor.setDirection(DcMotor.Direction.REVERSE);
        runtime.reset();

        telemetry.addData("LED", bLedOn ? "On" : "Off");
        telemetry.addData("Clear", ColorSensor.alpha());
        telemetry.addData("Red  ", ColorSensor.red());
        telemetry.addData("Green", ColorSensor.green());
        telemetry.addData("Blue ", ColorSensor.blue());
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.addData("nLoopCount", nLoopCount);
        nLoopCount ++;

        telemetry.update();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive() && (nLoopCount < 10)) {
            nLoopCount += 10;
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            // telemetry.update();
           /*shooterLeft.setPower(0.37);
           shooterRight.setPower(0.37);*/
           /* leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            */
           // leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
           // rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            jewelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            pulleyMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //color_sensor.red();
            //color_sensor.blue();
            // leftArmServo.setPosition(.3);
            //  rightArmServo.setPosition(.3);
            //sleep(1000);

                jewelMotor.setPower(.2);
                Thread.sleep(600);

            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", ColorSensor.alpha());
            telemetry.addData("Red  ", ColorSensor.red());
            telemetry.addData("Blue ", ColorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.update();

            Color.RGBToHSV(ColorSensor.red(), ColorSensor.green(), ColorSensor.blue(), hsvValues);

            int leftPosition = leftMotor.getCurrentPosition();
            telemetry.addData("Encoder Position", leftPosition);
            int rightPosition = rightMotor.getCurrentPosition();
            telemetry.addData("Encoder Position", rightPosition);
            telemetry.update();

            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", ColorSensor.alpha());
            telemetry.addData("Red  ", ColorSensor.red());
            telemetry.addData("Blue ", ColorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.update();
            sleep(1000);

            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", ColorSensor.alpha());
            telemetry.addData("Red  ", ColorSensor.red());
            telemetry.addData("Blue ", ColorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.update();

            sleep(2000);



            if (ColorSensor.red() > ColorSensor.blue()) {
                telemetry.addData("LED", bLedOn ? "On" : "Off");
                telemetry.addData("Clear", ColorSensor.alpha());
                telemetry.addData("Red  ", ColorSensor.red());
                telemetry.addData("Blue ", ColorSensor.blue());
                telemetry.addData("Hue", hsvValues[0]);
                telemetry.update();

                leftMotor.setTargetPosition(-300);
                rightMotor.setTargetPosition(300);
                leftMotor.setPower(.1);
                rightMotor.setPower(.1);
               while (leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
                    leftPosition = leftMotor.getCurrentPosition();
                   telemetry.addData("Encoder Position", leftPosition);
                    rightPosition = rightMotor.getCurrentPosition();
                   telemetry.addData("Encoder Position", rightPosition);
                   telemetry.update();
               }



                leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftMotor.setTargetPosition(220);
                rightMotor.setTargetPosition(-220);
                leftMotor.setPower(.1);
                rightMotor.setPower(.1);

                jewelMotor.setDirection(DcMotor.Direction.FORWARD);
                jewelMotor.setPower(.2);
                Thread.sleep(600);

                while(leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
                    leftPosition = leftMotor.getCurrentPosition();
                    telemetry.addData("Encoder Position", leftPosition);
                    rightPosition = rightMotor.getCurrentPosition();
                    telemetry.addData("Encoder Position", rightPosition);
                    telemetry.update();
                }
                leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            } else if (ColorSensor.red() < ColorSensor.blue()){
                telemetry.addData("LED", bLedOn ? "On" : "Off");
                telemetry.addData("Clear", ColorSensor.alpha());
                telemetry.addData("Red  ", ColorSensor.red());
                telemetry.addData("Blue ", ColorSensor.blue());
                telemetry.addData("Hue", hsvValues[0]);
                telemetry.update();

                leftMotor.setTargetPosition(250);
                rightMotor.setTargetPosition(-250);
                leftMotor.setPower(.1);
                rightMotor.setPower(.1);
                while (leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
                    leftPosition = leftMotor.getCurrentPosition();
                    telemetry.addData("Encoder Position", leftPosition);
                    rightPosition = rightMotor.getCurrentPosition();
                    telemetry.addData("Encoder Position", rightPosition);
                    telemetry.update();
                }

                leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                jewelMotor.setDirection(DcMotor.Direction.FORWARD);
                jewelMotor.setPower(.2);
                Thread.sleep(600);

                leftMotor.setTargetPosition(-700);
                rightMotor.setTargetPosition(700);
                leftMotor.setPower(.2);
                rightMotor.setPower(.2);

                while(leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
                    leftPosition = leftMotor.getCurrentPosition();
                    telemetry.addData("Encoder Position", leftPosition);
                    rightPosition = rightMotor.getCurrentPosition();
                    telemetry.addData("Encoder Position", rightPosition);
                    telemetry.update();
                }
                leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }

            /*leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftMotor.setTargetPosition(2000);
            rightMotor.setTargetPosition(2000);
            leftMotor.setPower(.5);
            rightMotor.setPower(.5);

            while(leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
                leftPosition = leftMotor.getCurrentPosition();
                telemetry.addData("Left Encoder Position", leftPosition);
                rightPosition = rightMotor.getCurrentPosition();
                telemetry.addData("Right Encoder Position", rightPosition);
                telemetry.update();
            }

            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

rightMotor.setDirection(DcMotor.Direction.REVERSE);

            leftMotor.setTargetPosition(700);
            rightMotor.setTargetPosition(700);
            leftMotor.setPower(.3);
            rightMotor.setPower(.3);

            while(leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
                leftPosition = leftMotor.getCurrentPosition();
                telemetry.addData("Left Encoder Position", leftPosition);
                rightPosition = rightMotor.getCurrentPosition();
                telemetry.addData("Right Encoder Position", rightPosition);
                telemetry.update();
            }

            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            */

            /* while(leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
                leftPosition = leftMotor.getCurrentPosition();
                telemetry.addData("Left Encoder Position", leftPosition);
                rightPosition = rightMotor.getCurrentPosition();
                telemetry.addData("Right Encoder Position", rightPosition);
                telemetry.update();
                */
          /*  leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftMotor.setDirection(DcMotor.Direction.FORWARD);
            rightMotor.setDirection(DcMotor.Direction.REVERSE);
            jewelMotor.setDirection(DcMotor.Direction.REVERSE);

            leftMotor.setTargetPosition(2600);
            rightMotor.setTargetPosition(2600);
            leftMotor.setPower(1);
            rightMotor.setPower(1);

            while(leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()){
                leftPosition = leftMotor.getCurrentPosition();
                telemetry.addData("Encoder Position", leftPosition);
                rightPosition = rightMotor.getCurrentPosition();
                telemetry.addData("Encoder Position", rightPosition);
                telemetry.update();
}

            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(1000);

            leftMotor.setTargetPosition(700);
            rightMotor.setTargetPosition(-700);
            leftMotor.setPower(1);
            rightMotor.setPower(1);

            while(leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()){
                leftPosition = leftMotor.getCurrentPosition();
                telemetry.addData("Encoder Position", leftPosition);
                rightPosition = rightMotor.getCurrentPosition();
                telemetry.addData("Encoder Position", rightPosition);
                telemetry.update();
            }
            */

            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           // leftMotor.setPower(1);
           // rightMotor.setPower(1);
           // sleep(2000);
            /*leftPosition = leftMotor.getCurrentPosition();
            rightPosition = rightMotor.getCurrentPosition();
            telemetry.addData("end of loop", 0);
            telemetry.addData("L Encoder Position", leftPosition);
            telemetry.addData("R Encoder Position", rightPosition);
            telemetry.update();
            sleep(5000);
            */
                /*jewelMotor.setPower(-.3);
                sleep(100);
                leftMotor.setTargetPosition(-500);
                rightMotor.setTargetPosition(500);
                leftMotor.setPower(-.5);
                rightMotor.setPower(.5);
                Thread.sleep(200);*/
//sleep(10000);




          /*  leftMotor.setTargetPosition(-3000);
            rightMotor.setTargetPosition(-3000);

            int leftPosition = leftMotor.getCurrentPosition();
            telemetry.addData("Encoder Position", leftPosition);
            int rightPosition = rightMotor.getCurrentPosition();
            telemetry.addData("Encoder Position", rightPosition);

            telemetry.update();
            leftMotor.setPower(.9);
            rightMotor.setPower(.4);

            while(leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
                leftPosition = leftMotor.getCurrentPosition();
                telemetry.addData("Left Encoder Position", leftPosition);
                rightPosition = rightMotor.getCurrentPosition();
                telemetry.addData("Right Encoder Position", rightPosition);
                telemetry.update();
            }

            leftMotor.setPower(0);
            rightMotor.setPower(0);
            sleep(5000);
*/
            // sleep(5000);
         /*   rightArmServo.setPosition(.7);
            leftArmServo.setPosition(.7);

            leftMotor.setPower(1);
            rightMotor.setPower(1);
            Thread.sleep(1200);
            idle();

            leftMotor.setPower(-1);
            rightMotor.setPower(1);
            Thread.sleep(1700);

            leftMotor.setPower(1);
            rightMotor.setPower(1);
            Thread.sleep(1800);
*/
            // leftMotor.getCurrentPosition();
            //  leftMotor.setTargetPosition(100);
            // leftMotor.isBusy();
   /*
    Thread.sleep(1000);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
   //conveyor.setPower(1);
   try {
    Thread.sleep(1000);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
           /*conveyor.setPower(0);
           shooterLeft.setPower(0);
           shooterRight.setPower(0);*/
   /*try {
    Thread.sleep(2000);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
   leftMotor.setPower(1);
   rightMotor.setPower(1);
   try {
    Thread.sleep(625);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
   leftMotor.setPower(0);
   rightMotor.setPower(0);
   programState = 0;
/*
   requestOpModeStop();



   idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
  }
 }

 public void encoderDrive(double speed,
                          double leftInches, double rightInches,
                          double timeoutS) throws InterruptedException {
  int newLeftTarget;
  int newRightTarget;


  // Ensure that the opmode is still active
  //  if (opModeIsActive()) {


  // Determine new target position, and pass to motor controller
  // newLeftTarget = leftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
  //  newRightTarget = rightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
  //  leftMotor.setTargetPosition(newLeftTarget);
  // rightMotor.setTargetPosition(newRightTarget);


  // Turn On RUN_TO_POSITION
  //leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  //rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


  // reset the timeout time and start motion.
  //runtime.reset();
  //leftMotor.setPower(Math.abs(speed));
  //rightMotor.setPower(Math.abs(speed));


  // keep looping while we are still active, and there is time left, and both motors are running.
            /*while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy())) {


                // Display it for the driver.
                //telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
               // telemetry.addData("Path2",  "Running at %7d :%7d",
                     //   leftMotor.getCurrentPosition(),
                      //  rightMotor.getCurrentPosition());
               // telemetry.update();


                // Allow time for other processes to run.
                idle();
            }


            // Stop all motion;
            leftMotor.setPower(0);
            rightMotor.setPower(0);


            // Turn off RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            //  sleep(250);   // optional pause after each move */
        }
    }
}
