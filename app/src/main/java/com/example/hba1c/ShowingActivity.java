package com.example.hba1c;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ShowingActivity extends AppCompatActivity {
    private TextView tv;
    DatabaseManager dm;
    Cursor k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showTests();
    }

    private void showTests(){
        int number=0;
        int imin=0,imax=0;
        int min=0,max=0;
        double[]counter={0,0,0,0,0,0,0};
        int[]quartiles=new int[540];
        double totalmgdl=0,totalmmoll=0;
        double[][]totals=new double[6][2];
        for(int i=0;i<6;i++) for(int j=0;j<2;j++) totals[i][j]=0;
        double HbA1c1,HbA1c2,HbA1c3;
        dm=new DatabaseManager(getApplicationContext());
        tv=findViewById(R.id.tv);
        k=dm.giveAllTheTests();
        CharSequence buffer="";
        while (k.moveToNext()){
            if(!(k.getString(3).equals("0"))&&!(k.getString(4).equals("0"))) {
                number++;
                totalmgdl+=Double.parseDouble(k.getString(3));
                totalmmoll+=Double.parseDouble(k.getString(4));

                if(number==1){
                    min=Integer.parseInt(k.getString(3)); imin=number;
                    max=Integer.parseInt(k.getString(3)); imax=number;
                }else{
                    if(min>Integer.parseInt(k.getString(3))){
                        min=Integer.parseInt(k.getString(3)); imin=number;
                    }
                    if(max<Integer.parseInt(k.getString(3))){
                        max=Integer.parseInt(k.getString(3)); imax=number;
                    }
                }

                if(Integer.parseInt(k.getString(3))<70) counter[0]++;
                else if(Integer.parseInt(k.getString(3))>=70&&
                        Integer.parseInt(k.getString(3))<=135) counter[1]++;
                else if(Integer.parseInt(k.getString(3))>=136&&
                        Integer.parseInt(k.getString(3))<=180) counter[2]++;
                else if(Integer.parseInt(k.getString(3))>=181&&
                        Integer.parseInt(k.getString(3))<=240) counter[3]++;
                else if(Integer.parseInt(k.getString(3))>=241&&
                        Integer.parseInt(k.getString(3))<=299) counter[4]++;
                else if(Integer.parseInt(k.getString(3))>=300) counter[5]++;

                if(Integer.parseInt(k.getString(3))<=160) counter[6]++;

                if(Integer.parseInt(k.getString(2))==1) totals[0][0]+=Integer.parseInt(k.getString(3));
                else if(Integer.parseInt(k.getString(2))==2) totals[1][0]+=Integer.parseInt(k.getString(3));
                else if(Integer.parseInt(k.getString(2))==3) totals[2][0]+=Integer.parseInt(k.getString(3));
                else if(Integer.parseInt(k.getString(2))==4) totals[3][0]+=Integer.parseInt(k.getString(3));
                else if(Integer.parseInt(k.getString(2))==5) totals[4][0]+=Integer.parseInt(k.getString(3));
                else if(Integer.parseInt(k.getString(2))==6) totals[5][0]+=Integer.parseInt(k.getString(3));

                if(number>=499){
                    if(Integer.parseInt(k.getString(2))==1) totals[0][1]+=Integer.parseInt(k.getString(3));
                    else if(Integer.parseInt(k.getString(2))==2) totals[1][1]+=Integer.parseInt(k.getString(3));
                    else if(Integer.parseInt(k.getString(2))==3) totals[2][1]+=Integer.parseInt(k.getString(3));
                    else if(Integer.parseInt(k.getString(2))==4) totals[3][1]+=Integer.parseInt(k.getString(3));
                    else if(Integer.parseInt(k.getString(2))==5) totals[4][1]+=Integer.parseInt(k.getString(3));
                    else if(Integer.parseInt(k.getString(2))==6) totals[5][1]+=Integer.parseInt(k.getString(3));
                }
            }
            int nr=k.getInt(0);
            String date=k.getString(1);
            String time=k.getString(2);
            String mgdL=k.getString(3); quartiles[number-1]=Integer.parseInt(mgdL);
            String mmolL=k.getString(4);

            if(nr<10) buffer=buffer+""+nr+".\t\t";
            else if(nr<100) buffer=buffer+""+nr+".\t";
            else buffer=buffer+""+nr+".";
            if(Double.parseDouble(mgdL)>99) buffer=buffer+"\t"+date+"\t\t"+time+"\t\t"+mgdL+"\t\t("+mmolL+")\r\n";
            else if(Double.parseDouble(mgdL)>9) buffer=buffer+"\t"+date+"\t\t"+time+"\t\t"+mgdL+"\t\t("+mmolL+")\r\n";
            else buffer=buffer+"\t\t"+date+"\t\t"+time+"\t\t"+mgdL+"\t\t("+mmolL+")\r\n";

        }

        if(number==0) HbA1c1=0;
        else if(totalmgdl/number<=76) HbA1c1=5;
        else if(totalmgdl/number<=88) HbA1c1=5.5;
        else if(totalmgdl/number<=100) HbA1c1=6;
        else if(totalmgdl/number<=111.5) HbA1c1=6.5;
        else if(totalmgdl/number<=123) HbA1c1=7;
        else if(totalmgdl/number<=135) HbA1c1=7.5;
        else if(totalmgdl/number<=147) HbA1c1=8;
        else if(totalmgdl/number<=158.5) HbA1c1=8.5;
        else if(totalmgdl/number<=170) HbA1c1=9;
        else if(totalmgdl/number<=181.5) HbA1c1=9.5;
        else if(totalmgdl/number<=193) HbA1c1=10;
        else if(totalmgdl/number<=205) HbA1c1=10.5;
        else if(totalmgdl/number<=217) HbA1c1=11;
        else if(totalmgdl/number<=228.5) HbA1c1=11.5;
        else if(totalmgdl/number<=240) HbA1c1=12;
        else HbA1c1=12.5;

        if(number==0) HbA1c2=0;
        else if(totalmgdl/number<=97) HbA1c2=5;
        else if(totalmgdl/number<=111.5) HbA1c2=5.5;
        else if(totalmgdl/number<=126) HbA1c2=6;
        else if(totalmgdl/number<=140) HbA1c2=6.5;
        else if(totalmgdl/number<=154) HbA1c2=7;
        else if(totalmgdl/number<=168.5) HbA1c2=7.5;
        else if(totalmgdl/number<=183) HbA1c2=8;
        else if(totalmgdl/number<=197.5) HbA1c2=8.5;
        else if(totalmgdl/number<=212) HbA1c2=9;
        else if(totalmgdl/number<=226) HbA1c2=9.5;
        else if(totalmgdl/number<=240) HbA1c2=10;
        else if(totalmgdl/number<=254.4) HbA1c2=10.5;
        else if(totalmgdl/number<=269) HbA1c2=11;
        else if(totalmgdl/number<=283.5) HbA1c2=11.5;
        else if(totalmgdl/number<=298) HbA1c2=12;
        else HbA1c2=12.5;

        if(number==0) HbA1c3=0;
        else if(totalmgdl/number<=120) HbA1c3=5;
        else if(totalmgdl/number<=136) HbA1c3=5.5;
        else if(totalmgdl/number<=152) HbA1c3=6;
        else if(totalmgdl/number<=168.5) HbA1c3=6.5;
        else if(totalmgdl/number<=185) HbA1c3=7;
        else if(totalmgdl/number<=201) HbA1c3=7.5;
        else if(totalmgdl/number<=217) HbA1c3=8;
        else if(totalmgdl/number<=233) HbA1c3=8.5;
        else if(totalmgdl/number<=249) HbA1c3=9;
        else if(totalmgdl/number<=265.5) HbA1c3=9.5;
        else if(totalmgdl/number<=282) HbA1c3=10;
        else if(totalmgdl/number<=298) HbA1c3=10.5;
        else if(totalmgdl/number<=314) HbA1c3=11;
        else if(totalmgdl/number<=330.5) HbA1c3=11.5;
        else if(totalmgdl/number<=347) HbA1c3=12;
        else HbA1c3=12.5;

        //quartiles
        int temp;
        int change = 1;
        while(change > 0){
            change = 0;
            for(int i=0; i<quartiles.length-1; i++){
                if(quartiles[i]>quartiles[i+1]){
                    temp = quartiles[i+1];
                    quartiles[i+1] = quartiles[i];
                    quartiles[i] = temp;
                    change++;
                }
            }
        }
        //end

        buffer= "STATISTICS"+
                "\r\n\r\nHbA1c (Hard):\t\t\t\t\t\t\t\t\t\t\t"+String.format("%.1f",HbA1c1)+
                "\r\nHbA1c (Medium):\t\t\t\t\t\t\t\t\t"+String.format("%.1f",HbA1c2)+
                "\r\nHbA1c (Easy):\t\t\t\t\t\t\t\t\t\t\t"+String.format("%.1f",HbA1c3)+

                "\r\n\r\nHbA1c (Real):\t\t\t\t\t\t\t\t\t\t\t"+String.format("%.1f",(HbA1c2+(HbA1c1-HbA1c2)/2))+

                "\r\n\r\nNeutral (x<=135)\t\t\t\t\t\t\t\t\t\t"+(int)(counter[0]+counter[1])+"/"+(int)((counter[0]+counter[1])/540*100)+"%"+
                "\r\nLowered (x<70)\t\t\t\t\t\t\t\t\t\t"+(int)counter[0]+"/"+(int)(counter[0]/540*100)+"%"+
                "\r\nCorrect (70<=x<=135)\t\t\t\t\t\t\t"+(int)counter[1]+"/"+(int)(counter[1]/540*100)+"%"+
                "\r\nElevated (136<=x<=180)\t\t\t\t\t\t"+(int)counter[2]+"/"+(int)(counter[2]/540*100)+"%"+
                "\r\nGood (x<=160)\t\t\t\t\t\t\t\t\t\t\t"+(int)counter[6]+"/"+(int)(counter[6]/540*100)+"%"+
                "\r\n\r\nCritical (x>180)\t\t\t\t\t\t\t\t\t\t"+(int)(counter[3]+counter[4]+counter[5])+"/"+(int)((counter[3]+counter[4]+counter[5])/540*100)+"%"+
                "\r\nCritical 1st degree (181<=x<=240)\t\t"+(int)counter[3]+"/"+(int)(counter[3]/540*100)+"%"+
                "\r\nCritical 2nd degree (241<=x<=299)\t"+(int)counter[4]+"/"+(int)(counter[4]/540*100)+"%"+
                "\r\nCritical 3rd degree (x>=300)\t\t\t\t\t"+(int)counter[5]+"/"+(int)(counter[5]/540*100)+"%"+

                "\r\n\r\nCritical 1st degree of degree\t\t\t\t"+(int)(counter[3]/(counter[3]+counter[4]+counter[5])*100)+"%"+
                "\r\nCritical 2nd degree of degree\t\t\t\t"+(int)(counter[4]/(counter[3]+counter[4]+counter[5])*100)+"%"+
                "\r\nCritical 3rd degree of degree\t\t\t\t"+(int)(counter[5]/(counter[3]+counter[4]+counter[5])*100)+"%"+

                "\r\n\r\nAverage [mg/dL]:\t\t\t\t\t\t\t\t\t"+String.format("%.1f",(totalmgdl/number))+
                "\r\nTotal [mg/dL]:\t\t\t\t\t\t\t\t\t\t\t"+(int)totalmgdl+

                "\r\n\r\nMinimum and maximum range:\t\t\t"+(max-min)+

                "\r\n\r\nQuartile 0 (minimum):\t\t\t\t\t\t\t"+quartiles[0]+" ["+imin+"]"+
                "\r\nQuartile I:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(quartiles[134]+quartiles[135])/2+
                "\r\nQuartile II (Median):\t\t\t\t\t\t\t\t"+(quartiles[269]+quartiles[270])/2+
                "\r\nQuartile III:\t\t\t\t\t\t\t\t\t\t\t\t"+(quartiles[404]+quartiles[405])/2+
                "\r\nQuartile IV (maximum):\t\t\t\t\t\t\t"+quartiles[quartiles.length-1]+" ["+imax+"]"+

                "\r\n\r\nDATA FOR ALL TESTS\r\n"+
                "\r\nAverage for 1:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[0][0]/90)+
                "\r\nAverage for 2:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[1][0]/90)+
                "\r\nAverage for 3:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[2][0]/90)+
                "\r\nAverage for 4:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[3][0]/90)+
                "\r\nAverage for 5:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[4][0]/90)+
                "\r\nAverage for 6:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[5][0]/90)+

                "\r\n\r\nTotal for 1:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[0][0]+
                "\r\nTotal for 2:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[1][0]+
                "\r\nTotal for 3:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[2][0]+
                "\r\nTotal for 4:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[3][0]+
                "\r\nTotal for 5:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[4][0]+
                "\r\nTotal for 6:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[5][0]+

                "\r\n\r\nDATA FOR THE LAST WEEK\r\n"+
                "\r\nAverage for 1:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[0][1]/7)+
                "\r\nAverage for 2:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[1][1]/7)+
                "\r\nAverage for 3:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[2][1]/7)+
                "\r\nAverage for 4:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[3][1]/7)+
                "\r\nAverage for 5:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[4][1]/7)+
                "\r\nAverage for 6:\t\t\t\t\t\t\t\t\t\t  "+String.format(" % .1f ",totals[5][1]/7)+

                "\r\n\r\nTotal for 1:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[0][1]+
                "\r\nTotal for 2:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[1][1]+
                "\r\nTotal for 3:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[2][1]+
                "\r\nTotal for 4:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[3][1]+
                "\r\nTotal for 5:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[4][1]+
                "\r\nTotal for 6:\t\t\t\t\t\t\t\t\t\t\t\t\t"+(int)totals[5][1]+

                "\r\n\r\nAverage [mmol/L]:\t\t\t\t\t\t\t\t\t"+String.format("%.1f",(totalmmoll/number))+
                "\r\nTotal [mmol/L]:\t\t\t\t\t\t\t\t\t\t"+String.format("%.1f",totalmmoll)+

                "\r\n\r\nQuantity:\t\t\t\t\t\t\t\t\t\t\t\t\t"+number+"\r\n\r\n\r\n"+buffer;

                tv.setText(buffer);
    }
}
