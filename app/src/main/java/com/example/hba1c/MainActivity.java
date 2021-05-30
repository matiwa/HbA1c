package com.example.hba1c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgbtnshow,imgbtngraph,imgbtnsearch,imgbtnadd1,imgbtnadd7,imgbtnupdate,imgbtnexit;
    DatabaseManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializecomponents();
    }
    private void initializecomponents(){
        dm=new DatabaseManager(getApplicationContext());

        imgbtnshow=findViewById(R.id.imgbtnshow);
        imgbtnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ShowingActivity.class);
                startActivity(intent);
            }
        });
        imgbtngraph=findViewById(R.id.imgbtngraph);
        imgbtngraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Graph.class);
                startActivity(intent);
            }
        });
        imgbtnsearch=findViewById(R.id.imgbtnsearching);
        imgbtnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Searching.class);
                startActivity(intent);
            }
        });
        imgbtnadd1=findViewById(R.id.imgbtnadd1);
        imgbtnadd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddOneDay.class);
                startActivity(intent);
            }
        });
        imgbtnadd7=findViewById(R.id.imgbtnadd7);
        imgbtnadd7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddOneWeek.class);
                startActivity(intent);
            }
        });
        imgbtnupdate=findViewById(R.id.imgbtnupdate);
        imgbtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UpdateOneTest.class);
                startActivity(intent);
            }
        });
        imgbtnexit=findViewById(R.id.imgbtnexit);
        imgbtnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
