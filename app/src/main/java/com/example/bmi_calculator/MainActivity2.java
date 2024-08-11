package com.example.bmi_calculator;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class MainActivity2 extends AppCompatActivity {
    private SpeedometerView speedometerView;
    private TextView resultTextView;
    private float targetBMI;
    private float currentBMI = 0;
    private static final float ANIMATION_SPEED = 0.1f; // You can adjust the animation speed as needed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        speedometerView = findViewById(R.id.speedometer);
        resultTextView = findViewById(R.id.result);

        // Retrieve the BMI result
        Intent intent = getIntent();
        String weightString = intent.getStringExtra(MainActivity.Extra_weight);
        String heightString = intent.getStringExtra(MainActivity.Extra_height);

        float weight = Float.parseFloat(weightString);
        float height = Float.parseFloat(heightString);

        // Calculate the BMI
        targetBMI = weight / (height * height);

        // Update the result TextView
        String bmiResult = String.format("BMI: %.1f kg/m2", targetBMI); // One decimal place
        resultTextView.setText(bmiResult);

        // Start the needle animation
        startNeedleAnimation();
    }

    private void startNeedleAnimation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Run the animation until the needle reaches the target BMI
                while (currentBMI < targetBMI) {
                    try {
                        Thread.sleep(50); // Adjust this value for smoother animation
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Increment the current BMI value
                    currentBMI += ANIMATION_SPEED;

                    // Ensure the current BMI does not exceed the target BMI
                    if (currentBMI > targetBMI)
                        currentBMI = targetBMI;

                    // Update the SpeedometerView with the current BMI value
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            speedometerView.setBMIValue(currentBMI);
                        }
                    });
                }
            }
        }).start();
    }
}
