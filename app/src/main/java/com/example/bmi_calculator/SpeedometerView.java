package com.example.bmi_calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SpeedometerView extends View {

    private Paint arcPaint, needlePaint, textPaint, tickPaint,PinkPaint,greenPaint,yellowPaint,redPaint, darkRedPaint;
    private RectF arcRectF;
    private float currentValue; // Represents the current BMI value



    public SpeedometerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Arc Paint
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(200); // Thickness of the arc

        // Needle Paint
        needlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        needlePaint.setColor(Color.BLACK); // Needle color
        needlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        needlePaint.setStrokeWidth(8); // Thickness of the needle

        // Text Paint for numbers and labels
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK); // Text color
        textPaint.setTextSize(45); // Text size
        textPaint.setTextAlign(Paint.Align.CENTER); // Align text in the center

        // Tick Paint
        tickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tickPaint.setColor(Color.BLACK); // Tick color
        tickPaint.setStrokeWidth(5); // Tick thickness

        PinkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PinkPaint.setStyle(Paint.Style.STROKE);
        PinkPaint.setStrokeWidth(200);
        PinkPaint.setColor(0xFFF4BBC9);

        darkRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        darkRedPaint.setStyle(Paint.Style.STROKE);
        darkRedPaint.setStrokeWidth(200);
        darkRedPaint.setColor(0xFFADD8E6);

        greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        greenPaint.setStyle(Paint.Style.STROKE);
        greenPaint.setStrokeWidth(200);
        greenPaint.setColor(0xFF00FF00); // Green color for the first third

        yellowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        yellowPaint.setStyle(Paint.Style.STROKE);
        yellowPaint.setStrokeWidth(200);
        yellowPaint.setColor(0xFFFFFF00); // Yellow color for the second third

        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setStrokeWidth(200);
        redPaint.setColor(0xFFFF0000);// Black color for the arc


        arcRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Set up the rectangle bounds for our arc
        arcRectF.set(width * 0.1f, height * 0.5f, width * 0.9f, height * 1.5f);

        // Draw the arc for the speedometer
        arcPaint.setColor(Color.GREEN);
        canvas.drawArc(arcRectF, 180, 10, false, redPaint);
        canvas.drawArc(arcRectF, 190, 10, false, PinkPaint);
        canvas.drawArc(arcRectF, 200, 10, false, yellowPaint);
        canvas.drawArc(arcRectF, 210, 10, false,darkRedPaint );
        canvas.drawArc(arcRectF, 220, 10, false, PinkPaint);
        canvas.drawArc(arcRectF, 230, 10, false, redPaint);
        canvas.drawArc(arcRectF, 240, 12, false, yellowPaint);
        canvas.drawArc(arcRectF, 252, 29, false, greenPaint);
        canvas.drawArc(arcRectF, 281, 20, false, yellowPaint);
        canvas.drawArc(arcRectF, 301, 20, false, PinkPaint);
        canvas.drawArc(arcRectF, 321, 20, false, redPaint);
        canvas.drawArc(arcRectF, 341, 20, false, darkRedPaint);

        // Draw the arc representing the speedometer

        float startAngleGreen = 258; // Start angle of green arc
        float endAngleGreen = 280; // End angle of green arc
        float endAngleyellow= 297;
        float endAnglepink= 316;
        float endAnglered= 338;

        // Draw the text "18.5" at the start of the green segment
        // Draw the text "18.5" at the middle of the green segment
        drawTextAtAngle(canvas, "18.5", startAngleGreen, arcRectF, textPaint);
        drawTextAtAngle(canvas, "25", endAngleGreen, arcRectF, textPaint);
        drawTextAtAngle(canvas, "30", endAngleyellow, arcRectF, textPaint);
        drawTextAtAngle(canvas, "35", endAnglepink, arcRectF, textPaint);
        drawTextAtAngle(canvas, "40", endAnglered, arcRectF, textPaint);
        Paint arcTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcTextPaint.setColor(Color.BLACK); // Set the color as needed
        arcTextPaint.setTextSize(50); // Set the text size as needed
        arcTextPaint.setTextAlign(Paint.Align.LEFT);

        // Define a Path on which to draw the text
        @SuppressLint("DrawAllocation") Path arcPath = new Path();

// Calculate the middle angle of the green arc segment for the "Normal" text
        float midAngleGreen = (float) (235 + 275) / 2;

// Use arcPath.addArc() to add an arc to the path where the text will be drawn
        arcPath.addArc(arcRectF, midAngleGreen, 40); // Adjust the angle and sweep as necessary for alignment

// Draw the text "Normal" along the arc path
        String normalText = "Normal";
        canvas.drawTextOnPath(normalText, arcPath, 0, 0, arcTextPaint);

// Repeat for other text labels, adjusting the angles and text as necessary
// For example, to draw "Overweight" you might do:
        Path overweightPath = new Path();
        float midAngleYellow = (274 + 286) / 2;
        overweightPath.addArc(arcRectF, midAngleYellow, 30); // Adjust the angle and sweep as necessary
        String overweightText = "Overweight";
        canvas.drawTextOnPath(overweightText, overweightPath, 0, 0, arcTextPaint);


        Path ObesityPath = new Path();
        float midAnglepink = (286 + 350) / 2;
        ObesityPath.addArc(arcRectF, midAnglepink, 30);// Adjust the angle and sweep as necessary
        String ObesityText = "Obesity";
        canvas.drawTextOnPath(ObesityText,ObesityPath, 0, 0, arcTextPaint);

        Path UnderWeightPath = new Path();
        float start = (180 + 220) / 2;
        UnderWeightPath.addArc(arcRectF, start, 40);// Adjust the angle and sweep as necessary
        String UnderWeightText = "UnderWeight";
        canvas.drawTextOnPath(UnderWeightText,UnderWeightPath, 0, 0, arcTextPaint);



        // Draw the scale ticks and labels
        //drawScale(canvas);


        // Draw the needle at the correct position
        drawNeedle(canvas, currentValue);
    }

    private void drawTextAtAngle(Canvas canvas, String text, float angle, RectF arcBounds, Paint paint) {
        // Convert the arc's angle to radians for the mathematical calculations
        double angleRadians = Math.toRadians(angle);

        // Offset the text radius inside the arc based on the stroke width of the arc paint
        // and additional space to position the text inside the arc
        float textRadius = arcBounds.width() / 2 - paint.getStrokeWidth() - paint.getTextSize();

        // Calculate the position for the text
        float x = (float) (arcBounds.centerX() + textRadius * Math.cos(angleRadians));
        float y = (float) (arcBounds.centerY() + textRadius * Math.sin(angleRadians));

        // Adjust y position based on font metrics for vertical centering
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        y += (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;

        // Rotate the canvas to make sure the text is upright
        canvas.save();
        canvas.rotate((float) Math.toDegrees(angleRadians), x, y);

        // Draw the text on the canvas
        canvas.drawText(text, x, y, paint);

        // Restore the canvas' rotation
        canvas.restore();
    }


    private void drawScale(Canvas canvas) {
        int numTicks = 45; // Number of ticks on the scale
        float totalSweepAngle = 180f; // Total sweep angle of the scale
        float sweepAnglePerTick = totalSweepAngle / numTicks; // Sweep angle per tick

        for (int i = 0; i <= numTicks; i++) {
            float tickAngle = 180 + i * sweepAnglePerTick;
            double angleRad = Math.toRadians(tickAngle);

            float innerX = arcRectF.centerX() + (arcRectF.width() * 0.5f) * (float) Math.cos(angleRad);
            float innerY = arcRectF.centerY() + (arcRectF.height() * 0.5f) * (float) Math.sin(angleRad);
            float outerX = arcRectF.centerX() + (arcRectF.width() * 0.45f) * (float) Math.cos(angleRad);
            float outerY = arcRectF.centerY() + (arcRectF.height() * 0.45f) * (float) Math.sin(angleRad);

            canvas.drawLine(innerX, innerY, outerX, outerY, tickPaint);

            if (i % 5 == 0) {
                String label = String.valueOf(i);
                float textX = arcRectF.centerX() + (arcRectF.width() * 0.4f) * (float) Math.cos(angleRad);
                float textY = arcRectF.centerY() + (arcRectF.height() * 0.4f) * (float) Math.sin(angleRad);
                canvas.drawText(label, textX, textY, textPaint);
            }
        }
    }

    public void setBMIValue(float bmiValue) {
        // Ensure that the BMI value is within the expected range
        bmiValue = Math.max(0, Math.min(bmiValue, 45));

        // Set the BMI value
        this.currentValue = bmiValue;

        // Request to redraw the view
        invalidate();
    }


    private void drawNeedle(Canvas canvas, float bmiValue) {
        // Normalize the BMI value within the range [0, 40]
        float normalizedValue = bmiValue / 45;

        // Calculate the angle for the needle
        float needleAngle = 180 + (normalizedValue * 180);

        // Convert angle to radians for trigonometry
        double angleRad = Math.toRadians(needleAngle);

        // Determine the end point of the needle
        float centerX = arcRectF.centerX();
        float centerY = arcRectF.centerY();
        float length = arcRectF.width() * 0.4f;
        float endX = centerX + length * (float) Math.cos(angleRad);
        float endY = centerY + length * (float) Math.sin(angleRad);

        // Draw the needle
        canvas.drawLine(centerX, centerY, endX, endY, needlePaint);
    }

    public void setValue(float bmiValue) {
        if (bmiValue >= 0 && bmiValue <= 45) {
            setBMIValue(bmiValue);
        }
    }
}
