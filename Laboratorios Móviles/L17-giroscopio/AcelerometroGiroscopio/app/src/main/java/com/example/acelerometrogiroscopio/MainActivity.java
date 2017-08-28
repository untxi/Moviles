package com.example.acelerometrogiroscopio;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private static final float SHAKE_THRESHOLD = 1.1f;
    private static final int SHAKE_WAIT_TIME_MS = 250;
    private static final float ROTATION_THRESHOLD = 2.0f;
    private static final int ROTATION_WAIT_TIME_MS = 100;

    private static MediaPlayer soundAcc;
    private static MediaPlayer soundGyro;

    private SensorManager mSensorManager;
    private Sensor mSensorAcc;
    private Sensor mSensorGyr;
    private long mShakeTime = 0;
    private long mRotationTime = 0;

    private TextView girosX;
    private TextView girosY;
    private TextView girosZ;
    private TextView acelX;
    private TextView acelY;
    private TextView acelZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorGyr = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        girosX = (TextView) findViewById(R.id.gyro_x);
        girosY = (TextView) findViewById(R.id.gyro_y);
        girosZ = (TextView) findViewById(R.id.gyro_z);
        acelX = (TextView) findViewById(R.id.accele_x);
        acelY = (TextView) findViewById(R.id.accele_y);
        acelZ = (TextView) findViewById(R.id.accele_z);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorAcc, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorGyr, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                acelX.setText(R.string.no_acuracy);
                acelY.setText(R.string.no_acuracy);
                acelZ.setText(R.string.no_acuracy);
            } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                girosX.setText(R.string.no_acuracy);
                girosY.setText(R.string.no_acuracy);
                girosZ.setText(R.string.no_acuracy);
            }
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            acelX.setText("x = " + Float.toString(event.values[0]));
            acelY.setText("y = " + Float.toString(event.values[1]));
            acelZ.setText("z = " + Float.toString(event.values[2]));
            detectShake(event);
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            girosX.setText("x = " + Float.toString(event.values[0]));
            girosY.setText("y = " + Float.toString(event.values[1]));
            girosZ.setText("z = " + Float.toString(event.values[2]));
            detectRotation(event);
        }
    }

    private void detectShake(SensorEvent event) {
        long now = System.currentTimeMillis();

        if ((now - mShakeTime) > SHAKE_WAIT_TIME_MS) {
            mShakeTime = now;

            float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            // Change background color if gForce exceeds threshold;
            // otherwise, reset the color
            if (gForce > SHAKE_THRESHOLD) {
            }
        }
    }

    private void detectRotation(SensorEvent event) {
        long now = System.currentTimeMillis();

        if ((now - mRotationTime) > ROTATION_WAIT_TIME_MS) {
            mRotationTime = now;

            // Change background color if rate of rotation around any
            // axis and in any direction exceeds threshold;
            // otherwise, reset the color
            if (Math.abs(event.values[0]) > ROTATION_THRESHOLD ||
                    Math.abs(event.values[1]) > ROTATION_THRESHOLD ||
                    Math.abs(event.values[2]) > ROTATION_THRESHOLD) {
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
