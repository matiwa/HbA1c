package com.example.hba1c;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddOneWeek extends AppCompatActivity {
    private Spinner sy,sm,sd;
    private EditText et1,et2,et3,et4,et5,et6;
    private EditText et7,et8,et9,et10,et11,et12;
    private EditText et13,et14,et15,et16,et17,et18;
    private EditText et19,et20,et21,et22,et23,et24;
    private EditText et25,et26,et27,et28,et29,et30;
    private EditText et31,et32,et33,et34,et35,et36;
    private EditText et37,et38,et39,et40,et41,et42;
    private ImageButton imgbtnsend,imgbtnback;
    DatabaseManager dm;
    Cursor k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_one_week);

        initializecomponents();
        insertrecords();
        currentDate();
    }

    private void initializecomponents(){
        dm=new DatabaseManager(getApplicationContext());
        k=dm.giveAllTheTests();
        sy=findViewById(R.id.syear);
        sy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
        sm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
        et7=findViewById(R.id.et7);
        et8=findViewById(R.id.et8);
        et9=findViewById(R.id.et9);
        et10=findViewById(R.id.et10);
        et11=findViewById(R.id.et11);
        et12=findViewById(R.id.et12);
        et13=findViewById(R.id.et13);
        et14=findViewById(R.id.et14);
        et15=findViewById(R.id.et15);
        et16=findViewById(R.id.et16);
        et17=findViewById(R.id.et17);
        et18=findViewById(R.id.et18);
        et19=findViewById(R.id.et19);
        et20=findViewById(R.id.et20);
        et21=findViewById(R.id.et21);
        et22=findViewById(R.id.et22);
        et23=findViewById(R.id.et23);
        et24=findViewById(R.id.et24);
        et25=findViewById(R.id.et25);
        et26=findViewById(R.id.et26);
        et27=findViewById(R.id.et27);
        et28=findViewById(R.id.et28);
        et29=findViewById(R.id.et29);
        et30=findViewById(R.id.et30);
        et31=findViewById(R.id.et31);
        et32=findViewById(R.id.et32);
        et33=findViewById(R.id.et33);
        et34=findViewById(R.id.et34);
        et35=findViewById(R.id.et35);
        et36=findViewById(R.id.et36);
        et37=findViewById(R.id.et37);
        et38=findViewById(R.id.et38);
        et39=findViewById(R.id.et39);
        et40=findViewById(R.id.et40);
        et41=findViewById(R.id.et41);
        et42=findViewById(R.id.et42);
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
                        if (j > 42)
                            dm.updateTest(j - 42, k.getString(1), k.getString(3), k.getString(4));
                        if (j >= 499) {
                            switch (j) {
                                case 499: {
                                    dm.updateTest(j, date, et1.getText().toString(), String.format("%.1f", Double.parseDouble(et1.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 500: {
                                    dm.updateTest(j, date, et2.getText().toString(), String.format("%.1f", Double.parseDouble(et2.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 501: {
                                    dm.updateTest(j, date, et3.getText().toString(), String.format("%.1f", Double.parseDouble(et3.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 502: {
                                    dm.updateTest(j, date, et4.getText().toString(), String.format("%.1f", Double.parseDouble(et4.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 503: {
                                    dm.updateTest(j, date, et5.getText().toString(), String.format("%.1f", Double.parseDouble(et5.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 504: {
                                    dm.updateTest(j, date, et6.getText().toString(), String.format("%.1f", Double.parseDouble(et6.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 505: {
                                    dm.updateTest(j, date, et7.getText().toString(), String.format("%.1f", Double.parseDouble(et7.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 506: {
                                    dm.updateTest(j, date, et8.getText().toString(), String.format("%.1f", Double.parseDouble(et8.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 507: {
                                    dm.updateTest(j, date, et9.getText().toString(), String.format("%.1f", Double.parseDouble(et9.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 508: {
                                    dm.updateTest(j, date, et10.getText().toString(), String.format("%.1f", Double.parseDouble(et10.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 509: {
                                    dm.updateTest(j, date, et11.getText().toString(), String.format("%.1f", Double.parseDouble(et11.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 510: {
                                    dm.updateTest(j, date, et12.getText().toString(), String.format("%.1f", Double.parseDouble(et12.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 511: {
                                    dm.updateTest(j, date, et13.getText().toString(), String.format("%.1f", Double.parseDouble(et13.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 512: {
                                    dm.updateTest(j, date, et14.getText().toString(), String.format("%.1f", Double.parseDouble(et14.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 513: {
                                    dm.updateTest(j, date, et15.getText().toString(), String.format("%.1f", Double.parseDouble(et15.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 514: {
                                    dm.updateTest(j, date, et16.getText().toString(), String.format("%.1f", Double.parseDouble(et16.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 515: {
                                    dm.updateTest(j, date, et17.getText().toString(), String.format("%.1f", Double.parseDouble(et17.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 516: {
                                    dm.updateTest(j, date, et18.getText().toString(), String.format("%.1f", Double.parseDouble(et18.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 517: {
                                    dm.updateTest(j, date, et19.getText().toString(), String.format("%.1f", Double.parseDouble(et19.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 518: {
                                    dm.updateTest(j, date, et20.getText().toString(), String.format("%.1f", Double.parseDouble(et20.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 519: {
                                    dm.updateTest(j, date, et21.getText().toString(), String.format("%.1f", Double.parseDouble(et21.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 520: {
                                    dm.updateTest(j, date, et22.getText().toString(), String.format("%.1f", Double.parseDouble(et22.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 521: {
                                    dm.updateTest(j, date, et23.getText().toString(), String.format("%.1f", Double.parseDouble(et23.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 522: {
                                    dm.updateTest(j, date, et24.getText().toString(), String.format("%.1f", Double.parseDouble(et24.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 523: {
                                    dm.updateTest(j, date, et25.getText().toString(), String.format("%.1f", Double.parseDouble(et25.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 524: {
                                    dm.updateTest(j, date, et26.getText().toString(), String.format("%.1f", Double.parseDouble(et26.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 525: {
                                    dm.updateTest(j, date, et27.getText().toString(), String.format("%.1f", Double.parseDouble(et27.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 526: {
                                    dm.updateTest(j, date, et28.getText().toString(), String.format("%.1f", Double.parseDouble(et28.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 527: {
                                    dm.updateTest(j, date, et29.getText().toString(), String.format("%.1f", Double.parseDouble(et29.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 528: {
                                    dm.updateTest(j, date, et30.getText().toString(), String.format("%.1f", Double.parseDouble(et30.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 529: {
                                    dm.updateTest(j, date, et31.getText().toString(), String.format("%.1f", Double.parseDouble(et31.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 530: {
                                    dm.updateTest(j, date, et32.getText().toString(), String.format("%.1f", Double.parseDouble(et32.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 531: {
                                    dm.updateTest(j, date, et33.getText().toString(), String.format("%.1f", Double.parseDouble(et33.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 532: {
                                    dm.updateTest(j, date, et34.getText().toString(), String.format("%.1f", Double.parseDouble(et34.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 533: {
                                    dm.updateTest(j, date, et35.getText().toString(), String.format("%.1f", Double.parseDouble(et35.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 534: {
                                    dm.updateTest(j, date, et36.getText().toString(), String.format("%.1f", Double.parseDouble(et36.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 535: {
                                    dm.updateTest(j, date, et37.getText().toString(), String.format("%.1f", Double.parseDouble(et37.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 536: {
                                    dm.updateTest(j, date, et38.getText().toString(), String.format("%.1f", Double.parseDouble(et38.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 537: {
                                    dm.updateTest(j, date, et39.getText().toString(), String.format("%.1f", Double.parseDouble(et39.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 538: {
                                    dm.updateTest(j, date, et40.getText().toString(), String.format("%.1f", Double.parseDouble(et40.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 539: {
                                    dm.updateTest(j, date, et41.getText().toString(), String.format("%.1f", Double.parseDouble(et41.getText().toString()) * 0.0555));
                                    break;
                                }
                                case 540: {
                                    dm.updateTest(j, date, et42.getText().toString(), String.format("%.1f", Double.parseDouble(et42.getText().toString()) * 0.0555));
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
