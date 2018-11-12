package com.example.shadabkhan.healthcare;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.Serializable;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    ProgressBar pb1,pb2,pb3;
   // final TextView t8= (TextView) findViewById(R.id.textview);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

       // DatabaseReference myRef = database.getReferenceFromUrl("https://experiment-a6425.firebaseio.com");




        place markerobject = (place) getIntent().getParcelableExtra("MyClass");
        setTitle(""+markerobject.place_name+","+markerobject.admin_name1);
        setContentView(R.layout.activity_info);

//getActionBar().setTitle(""+markerobject.place_name+","+markerobject.admin_name1);
//getSupportActionBar().setTitle(""+markerobject.place_name);
      int a= (int )Math.ceil((Double.parseDouble(markerobject.malaria_risk_index)*100)/432);
      int b=(int )Math.ceil((Double.parseDouble(markerobject.tuberculosis_risk_index)*100)/2591);
      int c=(int )Math.ceil((Double.parseDouble(markerobject.malnutrition_risk_index)*100)/160);
      String causeTB=markerobject.tuberculosis_cause;
      String causeMal=markerobject.malaria_cause;
      String causeMnut=markerobject.malnutrition_cause;
      Double vultb=(Double.parseDouble(markerobject.tuberculosis_deaths)/Double.parseDouble(causeTB));
      Double vulMal=(Double.parseDouble(markerobject.malaria_deaths)/Double.parseDouble(causeMal));;
      Double vulMnut=(Double.parseDouble(markerobject.malnutrition_deaths)/Double.parseDouble(causeMnut));;
        pb1= (ProgressBar) findViewById(R.id.progressBar);

        pb2= (ProgressBar) findViewById(R.id.progressBar2);

        pb3= (ProgressBar) findViewById(R.id.progressBar3);
        choosecolor(a,pb1);
        choosecolor(b,pb2);
        choosecolor(c,pb3);
        TextView t4=(TextView) findViewById(R.id.textview4);
        TextView t5=(TextView) findViewById(R.id.textview5);
        TextView t6=(TextView) findViewById(R.id.textview6);
        TextView t7=(TextView) findViewById(R.id.textview7);

        t4.setText("Risk factor="+a+"%\n"+"Vulnerability="+String.format("%.3f",vulMal/2));

        t5.setText("Risk factor="+b+"%\n"+"Vulnerability="+String.format("%.3f",vultb/2));

        t6.setText("Risk factor="+c+"%\n"+"Vulnerability="+String.format("%.3f",vulMnut/2));
t7.setText("Risk Factor:The chances of being infected by this disease\nVulnerability: The chances of demise of patient suffering from the disease");
    SetPieChart((Double.parseDouble(causeMal)),Double.parseDouble(causeTB),Double.parseDouble(causeMnut));

    }
    public void choosecolor(int riskp,ProgressBar pb1)
    {
        int a = riskp;
        if(a>=0&&a<=5)
        {
            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(1,255,0)));
        }

        else if(a>=6&&a<=10)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(26,245,10)));
        }

        else if(a>=11&&a<=15)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(51,235,20)));
        }

        else if(a>=16&&a<=20)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(76,225,30)));
        }
        else if(a>=21&&a<=25)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(101,215,40)));
        }
        else if(a>=26&&a<=30)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(126,205,50)));
        }
        else if(a>=31&&a<=35)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(151,195,60)));
        }
        else if(a>=36&&a<=40)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(176,185,70)));
        }
        else if(a>=41&&a<=45)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(201,175,80)));
        }

        else if(a>=46&&a<=50)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(226,165,90)));
        }
        else if(a>=51&&a<=55)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(251,155,100)));
        }
        else if(a>=56&&a<=60)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(255,200,0)));
        }
        else if(a>=61&&a<=65)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(255,201,0)));
        }
        else if(a>=66&&a<=70)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(255,176,0)));
        }
        else if(a>=71&&a<=75)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(255,151,0)));
        }
        else if(a>=76&&a<=80)
        {
            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(255,126,0)));
        }
        else if(a>=81&&a<=85)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(255,101,0)));
        }
        else if(a>=86&&a<=90)
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(255,76,0)));
        }
        else if(a>=91&&a<=95)
        {
            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(255,51,0)));
        }
        else
        {

            pb1.setProgress(a);
            pb1.setProgressTintList(ColorStateList.valueOf(Color.rgb(255,26,0)));

        }
       final TextView t8= (TextView) findViewById(R.id.textview);
        final TextView t9 =(TextView) findViewById(R.id.textview2);
       final  TextView t10 =(TextView) findViewById(R.id.textView3);

        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(InfoActivity.this,InfoDisease.class);
                Info objA=new Info();
                objA.name=(String)t8.getText();
                objA.sym= "    fever, which may come and go, or may be constant\n" +
                        "    chills\n" +
                        "    profuse sweating\n" +
                        "    malaise (feeling of unwellness)\n" +
                        "    muscle and joint pain\n" +
                        "    headache\n" +
                        "    confusion\n" +
                        "    nausea\n" +
                        "    loss of appetite\n" +
                        "    diarrhoea\n" +
                        "    abdominal pain\n" +
                        "    cough\n" +
                        "    anemia.\n";
                objA.pre="Exclusion from childcare, preschool, school or work is not necessary but cases should avoid being bitten by mosquitoes while they are unwell. Travellers to areas with malaria are usually advised to take preventative anti-malarial drugs.\n" +
                        "There is no vaccine to prevent human infection by this parasite.\n" +
                        "Personal protection and the environmental management of mosquitoes are important in preventing illness.";
                intent.putExtra("object",objA);
                startActivity(intent);
            }
        });


        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(InfoActivity.this,InfoDisease.class);
                Info objA=new Info();
                objA.name=(String)t9.getText();
                objA.sym="\n" +
                        "    Coughing that lasts three or more weeks\n" +
                        "    Coughing up blood\n" +
                        "    Chest pain, or pain with breathing or coughing\n" +
                        "    Unintentional weight loss\n" +
                        "    Fatigue\n" +
                        "    Fever\n" +
                        "    Night sweats\n" +
                        "    Chills\n" +
                        "    Loss of appetite\n";
                objA.pre="Take all of your medicines as they’re prescribed, until your doctor takes you off them.\n" +
                        "Keep all your doctor appointments.\n" +
                        "Always cover your mouth with a tissue when you cough or sneeze. Seal the tissue in a plastic bag, then throw it away.\n" +
                        "Wash your hands after coughing or sneezing.";
                intent.putExtra("object",objA);
                startActivity(intent);
            }
        });


        t10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(InfoActivity.this,InfoDisease.class);
                Info objA=new Info();
                objA.name=(String)t10.getText();
                objA.sym="feeling tired all the time and lacking energy\n" +
                        "frequently getting infections \n" +
                        "taking a long time to recover from infections\n" +
                        "delayed wound healing \n" +
                        "poor concentration\n" +
                        "difficulty keeping warm\n" +
                        "depression";
                objA.pre="\n" +
                        "    fruit and vegetables – at least 5 a day\n" +
                        "    bread, rice, potatoes, pasta, cereals and other starchy foods\n" +
                        "    milk and dairy foods – such as cheese and yoghurt\n" +
                        "    meat, fish, eggs, beans, nuts, and other non-dairy sources of protein\n";
                intent.putExtra("object",objA);
                startActivity(intent);
            }
        });
    }

    public void SetPieChart(Double a,Double b,Double c){
Double sum=a+b+c;
a=a/sum*100;
b=b/sum*100;
c=c/sum*100;
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        //pieChart.setUsePercentValues(true);
        ArrayList<Entry> yvalues = new ArrayList<Entry>();

        yvalues.add(new Entry(Float.parseFloat(a+""), 0));
        yvalues.add(new Entry(Float.parseFloat(b+""), 1));
        yvalues.add(new Entry(Float.parseFloat(c+""), 2));


        PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Malaria");
        xVals.add("Tuberculosis");
        xVals.add("Malnutrition");
        // pieChart.setDrawHoleEnabled(false);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        dataSet.setColors(new int[]{ Color.rgb(0, 255, 0), Color.rgb(255, 255, 0),Color.rgb(255, 0, 0)});
        pieChart.setDescription("Risk");
    }
}

class Info implements Serializable
{
    String sym,pre,name;
}