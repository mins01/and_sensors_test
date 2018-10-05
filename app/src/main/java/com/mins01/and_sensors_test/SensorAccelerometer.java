package com.mins01.and_sensors_test;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorAccelerometer implements SensorEventListener {
    private SensorManager sensorMan;
    private Sensor accelerometer;
    private final String TAG = "@@SensorAccelerometer";
//    private HashSet<Listener> mListeners = new HashSet<SensorAccelerometer.Listener>();
    private MainActivity activity;

    public SensorAccelerometer(SensorManager sensorMan, MainActivity activity) {
        this.sensorMan = sensorMan;
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.activity = activity;
        activity.addTr("accX","가속도X");
        activity.addTr("accY","가속도Y");
        activity.addTr("accZ","가속도Z");
        activity.editTdText("accX","0");
        activity.editTdText("accY","0");
        activity.editTdText("accZ","0");
    }

    public void start() {
        sensorMan.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        sensorMan.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        Log.e("@@",String.valueOf(sensorEvent.sensor.getType()));
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            Log.v(TAG,String.valueOf(x)+","+String.valueOf(y)+","+String.valueOf(z));
            activity.editTdText("accX",String.valueOf(x));
            activity.editTdText("accY",String.valueOf(y));
            activity.editTdText("accZ",String.valueOf(z));

//            float diff = (float) Math.sqrt(x * x + y * y + z * z);
//            if (diff > 0.5) { // 0.5 is a threshold, you can test it and change it
////                Log.d(TAG, "Device motion detected!!!!");
//            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

//    public interface Listener {
//        void onMotionDetected(SensorEvent event, float acceleration);
//    }
}