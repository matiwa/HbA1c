package com.example.hba1c;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

//other pages
//https://www.codingdemos.com/draw-android-line-chart/
//https://learningprogramming.net/mobile/android/line-chart-in-android/
//this page
//https://www.tutorialspoint.com/how-to-use-line-chart-graph-in-android

public class Graph extends AppCompatActivity {
    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList lineEntries;
    DatabaseManager dm;
    Cursor k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = findViewById(R.id.lineChart);
        lineEntries = new ArrayList<>();
        dm=new DatabaseManager(getApplicationContext());
        k=dm.giveAllTheTests();
        int i=0;
        while(k.moveToNext()){
            lineEntries.add(new Entry((float)i,(float)Integer.parseInt(k.getString(3))));
            i++;
        }
        lineDataSet = new LineDataSet(lineEntries, "");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setLabel("");

        lineDataSet.setValueTextSize(18f);
    }
}
