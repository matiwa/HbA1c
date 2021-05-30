package com.example.hba1c;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.RadioButton;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

public class Searching extends AppCompatActivity {
    private EditText etmin,etmax;
    private RadioButton rb1,rb2;
    private ImageButton imgbtnsearching,imgbtnfinish;
    private TextView tv;
    DatabaseManager dm;
    Cursor k;

    private int[]address=new int[540];
    private int min,max,quantity;
    private CharSequence buffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
        initializecomponents();
    }

    private void initializecomponents(){
        dm=new DatabaseManager(getApplicationContext());
        k=dm.giveAllTheTests();
        tv=findViewById(R.id.tvsearching);
        quantity=0;
        while(k.moveToNext()) {
            address[quantity] = Integer.parseInt(k.getString(3)); quantity++;
        }
        rb1=findViewById(R.id.rB1);
        rb1.setChecked(true);
        rb2=findViewById(R.id.rB2);
        rb2.setChecked(false);
        etmin=findViewById(R.id.etmin);
        etmin.setHint("Enter the value");
        etmax=findViewById(R.id.etmax);
        etmax.setVisibility(View.GONE);
        imgbtnsearching=findViewById(R.id.imgbtnsearching);
        imgbtnsearching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searching();
            }
        });
        imgbtnfinish=findViewById(R.id.imgbtnfinish);
        imgbtnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void searching(){
        buffer=""; quantity=0;
        if(rb1.isChecked()){
            if(etmin.getText().toString().equals("")) etmin.setText("0");
            min=Integer.parseInt(etmin.getText().toString());
            for(int i=0;i<address.length;i++){
                if(min==address[i]) {
                    quantity++;
                    buffer=buffer +""+quantity+".";
                    if(quantity<10) buffer=buffer+"\t\t";
                    else if(quantity<100) buffer=buffer+"\t";
                    buffer=buffer+"\t"+min+"\t\t\t["+ (i + 1) + "]\r\n";
                }
            }
        }else if(rb2.isChecked()){
            if(etmin.getText().toString().equals("")) etmin.setText("0");
            if(etmax.getText().toString().equals("")) etmax.setText("0");
            min=Integer.parseInt(etmin.getText().toString());
            max=Integer.parseInt(etmax.getText().toString());
            if(min>max){
                int tmp=min;
                min=max;
                max=tmp;
            }
            for(int i=0;i<address.length;i++){
                if(address[i]>=min && address[i]<=max) {
                    quantity++;
                    buffer=buffer + "" + quantity + ".";
                    if(quantity<10) buffer=buffer+"\t\t";
                    else if(quantity<100) buffer=buffer+"\t";
                    buffer=buffer+"\t"+ address[i] + "\t\t\t[" + (i + 1) + "]\r\n";
                }
            }
        }
        etmin.setText("");
        etmax.setText("");
        tv.setText(buffer);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rB1: {
                if (checked) {
                    etmin.setHint("Enter the value");
                    etmin.setText("");
                    etmin.setVisibility(View.VISIBLE);
                    etmax.setHint("");
                    etmax.setText("");
                    etmax.setVisibility(View.GONE);
                }
                break;
            }
            case R.id.rB2: {
                if (checked) {
                    etmin.setHint("Enter the minimum");
                    etmin.setText("");
                    etmax.setHint("Enter the maximum");
                    etmax.setText("");
                    etmin.setVisibility(View.VISIBLE);
                    etmax.setVisibility(View.VISIBLE);
                }
                break;
            }
        }
    }

}
