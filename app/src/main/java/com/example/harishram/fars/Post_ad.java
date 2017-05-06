package com.example.harishram.fars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Post_ad extends AppCompatActivity {

    RadioGroup rg1,rg2,rg3,rg4;
    RadioButton rb1,rb2,rb3,rb4;
    String Adttype,Location,Gender,Availability,rent,date;
    String Apttype=" ",Utilities=" ";
    Button submit;
    int rent1;
    StringBuffer sb;
    String comb="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    String adid,curdate;
    CheckBox studio,obf,tbf,laun,eb,wifi;
    EditText rs,da;
    Bundle bun;
    Advertisement ad;
    Random r;
    Firebase fba;
    String Description;
    EditText desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ad);
        Firebase.setAndroidContext(this);
        fba = new Firebase("https://fars-6636e.firebaseio.com").child("Advertisements");
        bun = getIntent().getExtras();
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        rg3 = (RadioGroup) findViewById(R.id.rg3);
        rg4 = (RadioGroup) findViewById(R.id.rg4);
        studio = (CheckBox) findViewById(R.id.checkBox16);
        obf = (CheckBox) findViewById(R.id.checkBox17);
        tbf = (CheckBox) findViewById(R.id.checkBox18);
        laun = (CheckBox) findViewById(R.id.checkBox23);
        eb = (CheckBox) findViewById(R.id.checkBox24);
        wifi = (CheckBox) findViewById(R.id.checkBox22);
        submit = (Button) findViewById(R.id.button21);
        rs = (EditText) findViewById(R.id.editText20);
        da = (EditText) findViewById(R.id.editText21);
        desc = (EditText) findViewById(R.id.editText13);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                sb=new StringBuffer();
                r= new Random();
                for(int i=0;i<10;i++){
                    char ch = comb.charAt(r.nextInt(comb.length()));
                    sb.append(ch);
                }
                adid = sb.toString();
                Log.d("tag",adid);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                curdate = sdf.format(c.getTime());
                int rg1id = rg1.getCheckedRadioButtonId();
                rb1 = (RadioButton) findViewById(rg1id);
                Adttype = rb1.getText().toString();
                if((!studio.isChecked())&&(!obf.isChecked())&&(!tbf.isChecked())){
                   Apttype = " ";
                }
                if(studio.isChecked()){
                   Apttype+=studio.getText().toString()+" ";
                }
                if(obf.isChecked()){
                   Apttype+=obf.getText().toString()+" ";
                }
                if(tbf.isChecked()){
                   Apttype+=tbf.getText().toString()+" ";
                }
                int rg2id = rg2.getCheckedRadioButtonId();
                rb2 = (RadioButton) findViewById(rg2id);
                Location = rb2.getText().toString();
                int rg3id = rg3.getCheckedRadioButtonId();
                rb3 = (RadioButton) findViewById(rg3id);
                Gender = rb3.getText().toString();
                if((!laun.isChecked())&&(!eb.isChecked())&&(!wifi.isChecked())){
                    Utilities = " ";
                }
                if(laun.isChecked()){
                    Utilities+=laun.getText().toString();
                }
                if(eb.isChecked()){
                    Utilities+=eb.getText().toString();
                }
                if(wifi.isChecked()){
                    Utilities+=wifi.getText().toString();
                }
                int rg4id = rg4.getCheckedRadioButtonId();
                rb4 = (RadioButton) findViewById(rg4id);
                Availability = rb4.getText().toString();
                rent = rs.getText().toString();
                rent1= Integer.parseInt(rent);
                date = da.getText().toString();
                if(desc.getText().toString()==null){
                   Description=" ";
                }
                else{
                Description = desc.getText().toString();}
                ad = new Advertisement(bun.getString("Name"),adid,curdate,Adttype,Apttype,Location,Gender,Utilities,Availability,rent1,date,"Active",Description,"","");
                Firebase fbna = fba.child(curdate).child(adid);
                fbna.setValue(ad);
                Intent i = new Intent(getApplicationContext(),My_ad.class);
                i.putExtras(bun);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(),Options.class);
        i.putExtras(bun);
        startActivity(i);
    }
}
