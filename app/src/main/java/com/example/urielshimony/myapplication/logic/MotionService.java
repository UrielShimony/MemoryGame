package com.example.urielshimony.myapplication.logic;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urielshimony.myapplication.R;

public class MotionService implements SensorEventListener {


    // sensor
    private SensorManager mSensorManager;
    private Sensor mRotationSensor;
    private static final int SENSOR_DELAY = 500 * 1000; // 500ms
    private boolean defaultVect = false;
    private float firstPitch;
    private float firstRoll;
    private float firstAzimut;


    public MotionService(Activity activity) {
        // Get an instance of the SensorManager
        mSensorManager = (SensorManager) activity.getSystemService(Activity.SENSOR_SERVICE);
        mRotationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        mSensorManager.registerListener(this, mRotationSensor, SENSOR_DELAY);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mRotationSensor && event.accuracy > 2) {
            if (event.values.length > 4) {
                float[] truncatedRotationVector = new float[4];
                System.arraycopy(event.values, 0, truncatedRotationVector, 0, 4);
                update(truncatedRotationVector);
            } else {
                update(event.values);
            }
        }

    }

    public void setDefaultVect(float[] orientaion) {
        firstAzimut = orientaion[0];
        firstPitch = orientaion[1];
        firstRoll = orientaion[2];
        defaultVect = true;


    }

    private void update(float[] vectors) {
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors);
        int worldAxisX = SensorManager.AXIS_X;
        int worldAxisZ = SensorManager.AXIS_Z;
        float[] adjustedRotationMatrix = new float[9];
        SensorManager.remapCoordinateSystem(rotationMatrix, worldAxisX, worldAxisZ, adjustedRotationMatrix);
        float[] orientation = new float[3];
        SensorManager.getOrientation(adjustedRotationMatrix, orientation);
        float azimuth = orientation[0];
        float pitch = orientation[1];
        float roll = orientation[2];

        if (!defaultVect) {
            setDefaultVect(orientation);
        }

        if (Math.abs(firstPitch - pitch) > 0.4
                || Math.abs(firstRoll - roll) > 1.5
                || Math.abs(firstAzimut - azimuth) > 1.5) {

            // todo throw event
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
