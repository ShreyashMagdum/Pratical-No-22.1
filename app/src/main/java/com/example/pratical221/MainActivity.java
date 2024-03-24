package com.example.pratical221;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements
        SensorEventListener {
    SensorManager sm;
    Sensor s;
    boolean isShuffled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm= (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {s =
                sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this, "Sensor Not Found !", Toast.LENGTH_SHORT).show();
        } }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        if (!isShuffled && (Math.abs(x) > 15 || Math.abs(y) > 15 || Math.abs(z) >15)) {
            changeBackgroundColor();
            isShuffled = true;
        }}
    private void changeBackgroundColor() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb((int) (Math.random() * 256), (int)
                (Math.random() * 256), (int) (Math.random() * 256)));
        Toast.makeText(this, "Color Changed ...", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }}