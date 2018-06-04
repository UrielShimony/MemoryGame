package com.example.urielshimony.myapplication.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.urielshimony.myapplication.R;

import static com.example.urielshimony.myapplication.FinalStrings.DATE_OF_BIRTH;
import static com.example.urielshimony.myapplication.FinalStrings.PLAYER_NAME;

public class MenuActivity extends AppCompatActivity {

    private String date;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extras = getIntent().getExtras();
        this.name = extras.getString(PLAYER_NAME);
        this.date = getIntent().getSerializableExtra(DATE_OF_BIRTH).toString();



    }

    public void play(View view)
    {
        //create  choseDifficultActivity
        Intent intent = new Intent(this, choseDifficultActivity.class);
        intent.putExtra(PLAYER_NAME, name);
        intent.putExtra(DATE_OF_BIRTH, date);
        startActivity(intent);

    }

    public void showScoreTable(View view)
    {
        //create  HighScoreActivity
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);

    }
    public void exit(View view) {

    }



}
/*
test sensor
package com.example.urielshimony.myapplication.UI;

import android.app.Activity;
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

public class MenuActivity extends AppCompatActivity implements SensorEventListener {

    private String date;
    private String name;

    //
    // sensor

    private SensorManager mSensorManager;
    private Sensor mRotationSensor;
    private static final int SENSOR_DELAY = 500 * 1000; // 500ms
    private static final int FROM_RADS_TO_DEGS = -57;
    private WindowManager mWindowManager;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;
    private boolean defaultVect = false;
    private float firstPitch;
    private float firstRoll;
    private float firstAzimut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extras = getIntent().getExtras();
        this.name = extras.getString("name");
        this.date = getIntent().getSerializableExtra("date_of_birth").toString();

        // Get an instance of the SensorManager
        mSensorManager = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);
        mRotationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        mSensorManager.registerListener(this, mRotationSensor, SENSOR_DELAY);


    }

    public void play(View view) {
        //create  choseDifficultActivity
        Intent intent = new Intent(this, choseDifficultActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("date_of_birth", date);
        startActivity(intent);

    }

    public void showScoreTable(View view) {
        //create  HighScoreActivity
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);

    }

    public void exit(View view) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mRotationSensor&&event.accuracy>2) {
            if (event.values.length > 4) {
                float[] truncatedRotationVector = new float[4];
                System.arraycopy(event.values, 0, truncatedRotationVector, 0, 4);
                update(truncatedRotationVector);
            } else {
                update(event.values);
            }
        }

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
            defaultVect = true;
            ((TextView) findViewById(R.id.FirstRoll)).setText("ROLL first : " + roll);
            ((TextView) findViewById(R.id.firstPitch)).setText("Pitch first: " + pitch);
            ((TextView) findViewById(R.id.FirstAzi)).setText("Azi first: " + azimuth);
            firstPitch=pitch;
            firstRoll=roll;
            firstAzimut = azimuth;
        }
        ((TextView) findViewById(R.id.pitch1)).setText("Pitch: " + pitch);
        ((TextView) findViewById(R.id.roll1)).setText("Roll: " + roll);
        ((TextView) findViewById(R.id.azi1)).setText("AZI: " + azimuth);

        if(Math.abs(firstPitch-pitch)>0.4
                || Math.abs(firstRoll-roll)>1.5
                || Math.abs(firstAzimut-azimuth)>1.5){
            ((TextView) findViewById(R.id.isGood)).setText("baddddd");
            ((TextView) findViewById(R.id.isGood)).setTextColor(Color.RED);
            if(Math.abs(firstPitch-pitch)>0.4){
                ((TextView) findViewById(R.id.pitch1)).setTextColor(Color.RED);
            }else {

            }

            if(Math.abs(firstRoll-roll)>1.5){
                ((TextView) findViewById(R.id.roll1)).setTextColor(Color.RED);
            }else{

            }
            if(Math.abs(firstAzimut-azimuth)>1.5){
                ((TextView) findViewById(R.id.azi1)).setTextColor(Color.RED);

            }


        }else {
            ((TextView) findViewById(R.id.isGood)).setText("goooodd");
            ((TextView) findViewById(R.id.isGood)).setTextColor(Color.GREEN);
            ((TextView) findViewById(R.id.roll1)).setTextColor(Color.BLACK);
            ((TextView) findViewById(R.id.azi1)).setTextColor(Color.BLACK);
            ((TextView) findViewById(R.id.pitch1)).setTextColor(Color.BLACK);


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}

 */