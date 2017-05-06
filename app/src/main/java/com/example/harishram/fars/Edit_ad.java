package com.example.harishram.fars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.firebase.client.Firebase;

public class Edit_ad extends AppCompatActivity {

    RadioGroup rg1,rg2,rg3,rg4;
    RadioButton rb1,rb2,rb3,rb4;
    CheckBox studio,obf,tbf,laun,eb,wifi;
    String Adttype,Location,Gender,Availability,rent,date;
    String Apttype=" ",Utilities=" ";
    EditText rs,da;
    Bundle bun1,bun2;
    Button sc;
    Firebase fba;
    Advertisement ad;
    String Description;
    EditText desc;
    int rent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ad);
        Firebase.setAndroidContext(this);
        fba = new Firebase("https://fars-6636e.firebaseio.com").child("Advertisements");
        bun1 = getIntent().getBundleExtra("BunUser");
        bun2 = getIntent().getBundleExtra("Bunad");
        rg1 = (RadioGroup) findViewById(R.id.rg5);
        rg2 = (RadioGroup) findViewById(R.id.rg6);
        rg3 = (RadioGroup) findViewById(R.id.rg7);
        rg4 = (RadioGroup) findViewById(R.id.rg8);
        studio = (CheckBox) findViewById(R.id.checkBox5);
        obf = (CheckBox) findViewById(R.id.checkBox7);
        tbf = (CheckBox) findViewById(R.id.checkBox8);
        laun = (CheckBox) findViewById(R.id.checkBox9);
        eb = (CheckBox) findViewById(R.id.checkBox10);
        wifi = (CheckBox) findViewById(R.id.checkBox11);
        rs = (EditText) findViewById(R.id.editText9);
        da = (EditText) findViewById(R.id.editText12);
        desc = (EditText) findViewById(R.id.editText14);
        sc= (Button) findViewById(R.id.button22);
        if(bun2.getString("AdType").equals("Apartment")){
            rg1.check(R.id.radioButton5);
        }
        else if(bun2.getString("AdType").equals("Roommate")){
            rg1.check(R.id.radioButton6);
        }
        if(bun2.getString("ApType").contains("Studio")){
            studio.setChecked(true);
        }
        if(bun2.getString("ApType").contains("One Bedroom-Furnished")){
            obf.setChecked(true);
        }
        if(bun2.getString("ApType").contains("Two Bedroom-Furnished")){
            tbf.setChecked(true);
        }
        if(bun2.getString("Location").equals("On-Campus")){
            rg2.check(R.id.radioButton7);
        }
        else if(bun2.getString("Location").equals("Off-Campus")){
            rg2.check(R.id.radioButton8);
        }
        if(bun2.getString("Gender").equals("Male")){
            rg3.check(R.id.radioButton9);
        }
        else if(bun2.getString("Gender").equals("Female")){
            rg3.check(R.id.radioButton10);
        }
        else if(bun2.getString("Gender").equals("Other")){
            rg3.check(R.id.radioButton11);
        }
        if(bun2.getString("Utilities").contains("Laundry")){
            laun.setChecked(true);
        }
        if(bun2.getString("Utilities").contains("Electricity Bill")){
            eb.setChecked(true);
        }
        if(bun2.getString("Utilities").contains("Wifi")){
            wifi.setChecked(true);
        }
        if(bun2.getString("Availability").equals("Temporary")){
            rg4.check(R.id.radioButton13);
        }
        else if(bun2.getString("Availability").equals("Permanent")){
            rg4.check(R.id.radioButton12);
        }
        rs.setText(Integer.toString(bun2.getInt("Rent Share")));
        da.setText(bun2.getString("Date_av"));
        desc.setText(bun2.getString("Description"));
        sc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
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
              Firebase fb= fba.child(bun2.getString("Date")).child(bun2.getString("AdvertisementID"));
              ad= new Advertisement(bun2.getString("Name"),bun2.getString("AdvertisementID"),bun2.getString("Date"),Adttype,Apttype,Location,Gender,Utilities,Availability,rent1,date,bun2.getString("status"),Description,"","");
              fb.setValue(ad);
                Intent i = new Intent(getApplicationContext(),My_ad.class);
                i.putExtras(bun1);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(),My_ad.class);
        i.putExtras(bun1);
        startActivity(i);
    }
}
