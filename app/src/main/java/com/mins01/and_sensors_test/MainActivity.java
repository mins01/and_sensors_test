package com.mins01.and_sensors_test;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.mins01.and_sensors_test.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout = null;
    private LayoutInflater inflater = null;
    private HashMap<String, TextView> textMap = new HashMap<>();
    private SensorManager sensorMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = findViewById(R.id.tableLayout);
        sensorMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        initTableLayout();
//        addTr("x", "y");
//        addTr("x1", "y1");
//        addTr("x2", "y2");
//        editTdText("x", "ssss");
        initSensors();
    }

    /**
     * 테이블 내용 초기화
     */
    private void initTableLayout() {
        View v;
        while (tableLayout.getChildCount() > 0) {
            v = tableLayout.getChildAt(0);
            ((ViewGroup) (v.getParent())).removeView(v);
        }
    }

    public void addTr(String keyname, String label) {
        addTr(keyname, label, "");
    }

    public void addTr(String keyname, String label, String text) {
        // LayoutInflater inflater = new this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = getLayoutInflater();
        TableRow tr = (TableRow) inflater.inflate(R.layout.tr, tableLayout, false);
        ((TextView) tr.findViewById(R.id.tdLabel)).setText(label);
        ((TextView) tr.findViewById(R.id.tdText)).setText(text);
        tableLayout.addView(tr);
        textMap.put(keyname, (TextView) tr.findViewById(R.id.tdText));
    }

    public void editTdText(String keyname, String text) {
        TextView tv = textMap.get(keyname);
        if (tv == null) {
            Log.e("@@", "지정 뷰가 없음");
            return;
        }
        tv.setText(text);
    }

    /**
     * 센서관련
     */
    SensorsControl snctrl;
    private void initSensors() {
        snctrl = new SensorsControl(sensorMan, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        snctrl.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        snctrl.stop();
    }
}

