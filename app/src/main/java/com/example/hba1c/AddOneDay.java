package com.example.hba1c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.database.Cursor;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;


public class AddOneDay extends AppCompatActivity {
    private Spinner sy,sm,sd;
    private EditText et1,et2,et3,et4,et5,et6;
    private ImageButton imgbtnsend,imgbtnback;
    DatabaseManager dm;
    Cursor k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_one_day);

        initializecomponents();
        insertrecords();
        currentDate();
    }

    private void initializecomponents(){
        dm=new DatabaseManager(getApplicationContext());
        k=dm.giveAllTheTests();
        sy=findViewById(R.id.syear);
        sy.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                /*String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("2"))
                {
                    // do your stuff
                }*/
                insertrecords2();
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        sm=findViewById(R.id.smonth);
        sm.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                /*String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("2"))
                {
                    // do your stuff
                }*/
                insertrecords2();
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        sd=findViewById(R.id.sday);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        et6=findViewById(R.id.et6);
        imgbtnsend=findViewById(R.id.imgbtnsend);
        imgbtnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().length()>0 && et2.getText().toString().length()>0 &&
                        et3.getText().toString().length()>0 && et4.getText().toString().length()>0 &&
                        et5.getText().toString().length()>0 && et6.getText().toString().length()>0) {
                    String date = sy.getSelectedItem().toString() + "-";
                    if (sm.getSelectedItem().toString().length() < 2)
                        date = date + "0" + sm.getSelectedItem().toString() + "-";
                    else date = date + sm.getSelectedItem().toString() + "-";
                    if (sd.getSelectedItem().toString().length() < 2)
                        date = date + "0" + sd.getSelectedItem().toString();
                    else date = date + sd.getSelectedItem().toString();
                    int j = 1;
                    while (k.moveToNext()) {
                        if (j > 6)
                            dm.updateTest(j - 6, k.getString(1), k.getString(3), k.getString(4));
                        if (j >= 535) {
                            switch (j) {
                                case 535: {
                                    dm.updateTest(j, date, et1.getText().toString(), String.format("%.1f", Double.parseDouble(et1.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 536: {
                                    dm.updateTest(j, date, et2.getText().toString(), String.format("%.1f", Double.parseDouble(et2.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 537: {
                                    dm.updateTest(j, date, et3.getText().toString(), String.format("%.1f", Double.parseDouble(et3.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 538: {
                                    dm.updateTest(j, date, et4.getText().toString(), String.format("%.1f", Double.parseDouble(et4.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 539: {
                                    dm.updateTest(j, date, et5.getText().toString(), String.format("%.1f", Double.parseDouble(et5.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 540: {
                                    dm.updateTest(j, date, et6.getText().toString(), String.format("%.1f", Double.parseDouble(et6.getText().toString()) * 0.0555));
                                    break;
                                }
                            }
                        }
                        j++;
                    }
                    finish();
                }else Toast.makeText(getApplicationContext(),"Enter all data",Toast.LENGTH_LONG).show();
            }
        });
        imgbtnback=findViewById(R.id.imgbtnback);
        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertrecords(){
        List<String> y = new ArrayList<String>();
        for(int i=1960;i<=2094;i++) y.add(Integer.toString(i));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, y);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sy.setAdapter(dataAdapter);

        List<String> m=new ArrayList<String>();
        for(int i=1;i<=12;i++) m.add(Integer.toString(i));
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sm.setAdapter(dataAdapter2);

        insertrecords2();
    }

    private void insertrecords2(){
        int max=0;
        if(sm.getSelectedItem().equals("1")||sm.getSelectedItem().equals("3")||sm.getSelectedItem().equals("5")||
                sm.getSelectedItem().equals("7")||sm.getSelectedItem().equals("8")||
                sm.getSelectedItem().equals("10")||sm.getSelectedItem().equals("12")) max=31;
        else if(sm.getSelectedItem().equals("4")||sm.getSelectedItem().equals("6")||
                sm.getSelectedItem().equals("9")||sm.getSelectedItem().equals("11")) max=30;
        else {
            if((Integer.parseInt(sy.getSelectedItem().toString())%4==0 &&
            Integer.parseInt(sy.getSelectedItem().toString())!=0)||
            Integer.parseInt(sy.getSelectedItem().toString())%400==0) max=29;
            else max=28;
        }
        List<String> d=new ArrayList<String>();
        for(int i=1;i<=max;i++) d.add(Integer.toString(i));
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, d);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sd.setAdapter(dataAdapter3);
    }

    private void currentDate(){
        Calendar now=Calendar.getInstance();
        sy.setSelection(now.get(Calendar.YEAR)-1960);
        sm.setSelection(now.get(Calendar.MONTH));
        sd.setSelection(now.get(Calendar.DAY_OF_MONTH));
    }
}
