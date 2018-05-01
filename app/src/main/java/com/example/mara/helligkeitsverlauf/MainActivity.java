package com.example.mara.helligkeitsverlauf;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Button btn_save;
    private Button btn_show;
    private EditText editTextOrt;
    private TextView textViewHelligkeit;
    private SensorManager sensorManager;
    private Sensor sensor;
    private double lux;

    private Repo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repo = new Repo();

        btn_save = findViewById(R.id.btn_save);
        btn_show = findViewById(R.id.btn_show);
        editTextOrt = findViewById(R.id.editText);
        textViewHelligkeit = findViewById(R.id.textViewHelligkeit);

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                intent.putExtra("helligkeiten", repo);
                startActivity(intent);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ort = editTextOrt.getText().toString();
                if (ort.length() <= 0) {
                    return;
                }
                repo.add(new Helligkeit(lux, ort, new Date()));
                editTextOrt.setText("");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            lux = sensorEvent.values[0];
            textViewHelligkeit.setText(String.valueOf(lux));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
