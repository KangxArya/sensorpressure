package com.example.tugas.pressure.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textview;
    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private Boolean isPressureSensorAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.textview);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)!=null) {
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            isPressureSensorAvailable = true;
        }
        else {
            textview.setText("Pressure Sensor is no Available");
            isPressureSensorAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textview.setText(sensorEvent.values[0] + "nPa");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPressureSensorAvailable) {
            sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isPressureSensorAvailable) {
            sensorManager.unregisterListener(this);
        }
    }
}
