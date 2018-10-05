package com.mins01.and_sensors_test;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;

public class SensorsControl implements SensorEventListener {
    private SensorManager sensorMan;
    private List<Sensor> sensors = new ArrayList<>();
    private final String TAG = "@@SensorsControl";
    //    private HashSet<Listener> mListeners = new HashSet<SensorAccelerometer.Listener>();
    private MainActivity activity;

    SensorsControl(SensorManager sensorMan, MainActivity activity) {
        this.sensorMan = sensorMan;
        this.activity = activity;
        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        activity.addTr("accX", "가속도X", "0");
        activity.addTr("accY", "가속도Y", "0");
        activity.addTr("accZ", "가속도Z", "0");
//        type_gravity = sensorMan.getDefaultSensor(Sensor.TYPE_GRAVITY);
        activity.addTr("TYPE_GRAVITY", "중력", "0");
        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
        activity.addTr("gyroX", "자이로X", "0");
        activity.addTr("gyroY", "자이로Y", "0");
        activity.addTr("gyroZ", "자이로Z", "0");
        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
        activity.addTr("mgX", "자기장X", "0");
        activity.addTr("mgY", "자기장Y", "0");
        activity.addTr("mgZ", "자기장Z", "0");
        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_LIGHT));
        activity.addTr("TYPE_LIGHT", "밝기(lux)", "0");
        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE));
        activity.addTr("temperature", "온도(C)", "0");
//        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_MOTION_DETECT));
//        activity.addTr("TYPE_MOTION_DETECT","모션센서","0");
        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_PRESSURE));
        activity.addTr("TYPE_PRESSURE", "대기압(hPa)", "0");
        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_PROXIMITY));
        activity.addTr("TYPE_PROXIMITY", "근접센서(cm)", "0");
        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY));
        activity.addTr("TYPE_RELATIVE_HUMIDITY", "상대습도", "0");
        sensors.add(sensorMan.getDefaultSensor(Sensor.TYPE_STEP_COUNTER));
        activity.addTr("TYPE_STEP_COUNTER", "스탭카운터", "0");


    }

    public void start() {
        for (int i = 0, m = sensors.size(); i < m; i++) {
            sensorMan.registerListener(this, sensors.get(i), SensorManager.SENSOR_DELAY_UI);
        }
    }

    void stop() {
        sensorMan.unregisterListener(this);
//        sensorMan.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        Log.e("@@", String.valueOf(sensorEvent.sensor.getType()));
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            double g = Math.sqrt(x * x + y * y + z * z);
            activity.editTdText("accX", String.valueOf(x));
            activity.editTdText("accY", String.valueOf(y));
            activity.editTdText("accZ", String.valueOf(z));
            activity.editTdText("TYPE_GRAVITY", String.valueOf(g));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            activity.editTdText("gyroX", String.valueOf(x));
            activity.editTdText("gyroY", String.valueOf(y));
            activity.editTdText("gyroZ", String.valueOf(z));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            activity.editTdText("mgX", String.valueOf(x));
            activity.editTdText("mgY", String.valueOf(y));
            activity.editTdText("mgZ", String.valueOf(z));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float x = sensorEvent.values[0];
            activity.editTdText("temperature", String.valueOf(x));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY) { //가속도 센서와 똑같다. 즉 의미 없다.
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            double g = Math.sqrt(x * x + y * y + z * z);
            activity.editTdText("type_gravity", String.valueOf(g));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            float x = sensorEvent.values[0];
            activity.editTdText("TYPE_LIGHT", String.valueOf(x));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MOTION_DETECT) {
            float x = sensorEvent.values[0];
            activity.editTdText("TYPE_MOTION_DETECT", String.valueOf(x));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float x = sensorEvent.values[0];
            activity.editTdText("TYPE_PRESSURE", String.valueOf(x / 1000));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float x = sensorEvent.values[0];
            activity.editTdText("TYPE_PROXIMITY", String.valueOf(x));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            float x = sensorEvent.values[0];
            activity.editTdText("TYPE_RELATIVE_HUMIDITY", String.valueOf(x));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            float x = sensorEvent.values[0];
            activity.editTdText("TYPE_STEP_COUNTER", String.valueOf(x));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

//    public interface Listener {
//        void onMotionDetected(SensorEvent event, float acceleration);
//    }
}