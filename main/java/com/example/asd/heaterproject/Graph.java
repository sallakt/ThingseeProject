package com.example.asd.heaterproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graph extends AppCompatActivity implements View.OnClickListener{

    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;

    public TextView currentAlt;
    public String altitude;
    public Integer res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        SharedPreferences altValue = getSharedPreferences("LOCATIONID", Activity.MODE_PRIVATE);
        altitude = altValue.getString("altitude", "0");
        TextView currentAlt = (TextView) findViewById(R.id.currentAlt);
        currentAlt.setText(String.valueOf(altitude) + " m");

        res = (Double.valueOf(altitude)).intValue();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 26),
                new DataPoint(10, 23),
                new DataPoint(20, 22),
                new DataPoint(30, 33),
                new DataPoint(40, 37),
                new DataPoint(50, 60),
                new DataPoint(60, 45),
                new DataPoint(70, 32),
                new DataPoint(80, res)
        });
        graph.addSeries(series);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backButton:
                Intent gps = new Intent(this, Hiking.class);
                startActivity(gps);
                break;
        }
    }
}