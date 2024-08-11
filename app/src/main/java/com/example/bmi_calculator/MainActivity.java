package com.example.bmi_calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {
    public static final String Extra_weight= "Extra_weight";
    public static final String Extra_height= "Extra_height";

    private int count = 0;
    private TextView countTextView, num1;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch1, switch2;
    EditText inft;
    TextView inm;
    Button button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        num1 = findViewById(R.id.num1);
        inft = findViewById(R.id.inft);
        inm = findViewById(R.id.inm);
        countTextView = findViewById(R.id.num);
        button = findViewById(R.id.button);
        updateCountText();


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && inft.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in feet first", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String feetInchesValue = inft.getText().toString();
                        String[] parts = feetInchesValue.split("\\."); // Split the input by decimal point

                        int feet = Integer.parseInt(parts[0]); // Parse feet
                        int inches = 0; // Initialize inches
                        if (parts.length > 1) {
                            inches = Integer.parseInt(parts[1]); // Parse inches if available
                        }

                        double totalInches = (feet * 12) + inches; // Convert feet to inches and add inches
                        double centimeters = totalInches * 2.54; // Convert total inches to centimeters
                        double meters = centimeters / 100;
                        inm.setText(String.valueOf(meters));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        int Weight = Integer.parseInt(countTextView.getText().toString());
                        Double pounds = Weight * 2.20462;
                        @SuppressLint("DefaultLocale")
                        String formattedResult = String.format("%.3f", pounds);
                        num1.setText(formattedResult);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if both feet and weight are filled before proceeding with calculation
                if (inft.getText().toString().isEmpty() || countTextView.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in feet and weight first", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Perform calculation
                calculateBMI();
            }
        });
    }
        // Start MainActivity2


    // Method to perform calculation
    private void calculateBMI() {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        String weight =  countTextView.getText().toString();
        String height = inm.getText().toString();

        intent.putExtra(Extra_weight, weight);
        intent.putExtra(Extra_height, height);

        startActivity(intent);
        }



    // Method to handle incrementing count
    public void activity1(View view) {
        count++;
        updateCountText();
    }

    // Method to handle decrementing count
    public void activity(View view) {
        if (count > 0) {
            count--;
        }
        updateCountText();
    }

    // Method to update count text view
    private void updateCountText() {
        countTextView.setText(String.valueOf(count));
    }
}





