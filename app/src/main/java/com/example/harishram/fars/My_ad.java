package com.example.harishram.fars;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class My_ad extends AppCompatActivity implements Button.OnClickListener {

    final String[] Name = new String[1000];
    final String[] AdvertisementID=new String[1000];
    final String[] Date= new String[1000];
    final String[] AdType= new String[1000];
    final String[] ApType=new String[1000];
    final String[] Location=new String[1000];
    final String[] Gender=new String[1000];
    final String[] Utilities=new String[1000];
    final String[] Availability=new String[1000];
    final int[] Rent_Share= new int[1000];
    final String[] Date_av= new String[1000];
    final String[] status=new String[1000];
    final String[] Description=new String[1000];

    Advertisement ads;
    DatabaseReference dbr;
    Bundle bun,bun1;
    LinearLayout ll;
    RelativeLayout.LayoutParams lp,lp1;
    String ad;
    Button [] delete = new Button[1000];
    Button [] edit = new Button[1000];
    TextView [] tx = new TextView[1000];
    int i=0;
    int ind=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ad);


        for(int j=0;j<1000;j++){
        edit[j] = new Button(this);
            tx[j] = new TextView(this);
        delete[j] = new Button(this);}
        bun = getIntent().getExtras();
        //bun1=new Bundle();
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        ll = (LinearLayout) findViewById(R.id.activity_my_ad);
        dbr = FirebaseDatabase.getInstance().getReference("/Advertisements");
        dbr.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              int i1=0;
              for(DataSnapshot dss1:dataSnapshot.getChildren()) {
                  for (DataSnapshot dss : dss1.getChildren()) {
                      ads = dss.getValue(Advertisement.class);
                      Log.d("tag", dss.getKey());
                      if (((ads.Name).equals(bun.getString("Name")) && ((ads.status).equals("Active")))) {
                          if (ads.AdType.equals("Apartment")) {
                              ad = "Posted on " + ads.Date + System.getProperty("line.separator");
                              ad += "House for Rent ";
                              ad += ads.ApType + " type ";
                              ad += ads.Location + " " + ads.Gender + " only" + " " + ads.Utilities + " included" + " " + ads.Availability + " availability" + " Available from " + ads.Date_av;
                              ad += " Rent Share: $" + ads.Rent_Share + " Phone No:" + " " + bun.getLong("Phone No") + " " + "Extra Description:" + ads.Description + System.getProperty("line.separator");
                              tx[i1].setId(i1);
                              tx[i1].setText(ad);
                              Log.d("textview" + i, Integer.toString(tx[i1].getId()));
                              tx[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));

                              if (tx[i1].getParent() != null) {
                                  ((ViewGroup) tx[i1].getParent()).removeView(tx[i1]);
                              }
                              Name[i1] = ads.Name;
                              AdvertisementID[i1] = ads.AdvertisementID;
                              Date[i1] = ads.Date;
                              AdType[i1] = ads.AdType;
                              ApType[i1] = ads.ApType;
                              Location[i1] = ads.Location;
                              Gender[i1] = ads.Gender;
                              Utilities[i1] = ads.Utilities;
                              Availability[i1] = ads.Availability;
                              Rent_Share[i1] = ads.Rent_Share;
                              status[i1] = ads.status;
                              Date_av[i1] = ads.Date_av;
                              Description[i1] = ads.Description;
                              ll.addView(tx[i1]);
                              edit[i1].setId(i1);
                              edit[i1].setText("Edit");
                              edit[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                              edit[i1].setTag(i1);

                              Log.d("button" + i1, Integer.toString(edit[i1].getId()));

                              if (edit[i1].getParent() != null) {
                                  ((ViewGroup) edit[i1].getParent()).removeView(edit[i1]);
                              }
                              //tx.append(edit[i]);
                              i=i1;
                              edit[i1].setOnClickListener(new View.OnClickListener() {

                                                              @Override
                                                              public void onClick(View view) {
                                                                  bun1 = new Bundle();
                                                                  for(int i3=0;i3<=i;i3++){
                                                                   if(edit[i3].getId()==view.getId()){
                                                                  Log.d("myterm",AdType[i3]);
                                                                  bun1.putString("Name", Name[i3]);
                                                                  bun1.putString("AdvertisementID",AdvertisementID[i3]);
                                                                  bun1.putString("Date", Date[i3]);
                                                                  bun1.putString("AdType", AdType[i3]);
                                                                  bun1.putString("ApType",ApType[i3]);
                                                                  bun1.putString("Location",Location[i3]);
                                                                  bun1.putString("Gender",Gender[i3]);
                                                                  bun1.putString("Utilities",Utilities[i3]);
                                                                  bun1.putString("Availability",Availability[i3]);
                                                                  bun1.putInt("Rent Share",Rent_Share[i3]);
                                                                  bun1.putString("Date_av",Date_av[i3]);
                                                                  bun1.putString("status", status[i3]);
                                                                  bun1.putString("Description",Description[i3]);
                                                                  Intent in = new Intent(getApplicationContext(), Edit_ad.class);
                                                                  in.putExtra("BunUser", bun);
                                                                  in.putExtra("Bunad", bun1);
                                                                  startActivity(in);}}
                                                              }
                              });
                              ll.addView(edit[i1]);
                              if (delete[i1].getParent() != null) {
                                  ((ViewGroup) delete[i1].getParent()).removeView(delete[i1]);
                              }
                              delete[i1].setId((i1*1000)+1000);
                              delete[i1].setText("Delete");
                              delete[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                              delete[i1].setOnClickListener(new View.OnClickListener(){

                                  @Override
                                  public void onClick(View view){
                                      for(int i3=0;i3<=i;i3++){
                                          if(delete[i3].getId()==view.getId()) {
                                              FirebaseDatabase fbd = FirebaseDatabase.getInstance();
                                              DatabaseReference dbr = fbd.getReference("/Advertisements/" + Date[i3] + "/" + AdvertisementID[i3] + "/status");
                                              dbr.setValue("Blocked");
                                              Intent i = new Intent(getApplicationContext(), My_ad.class);
                                              i.putExtras(bun);
                                              startActivity(i);
                                          }
                                      }

                                  }
                              });
                              ll.addView(delete[i1]);


                          } else if (((ads.AdType).equals("Roommate")) && ((ads.status).equals("Active"))) {
                              ad = "Posted on " + ads.Date + System.getProperty("line.separator");
                              ad += "Roommate Required";
                              ad += ads.ApType + " type";
                              ad += ads.Location + " " + ads.Gender + " only" + " " + ads.Utilities + " included" + " " + ads.Availability + " availability" + " Available from " + ads.Date_av;
                              ad += " Rent Share: $" + ads.Rent_Share + " Phone No:" + " " + bun.getLong("Phone No")+" Extra Description:"+ads.Description + System.getProperty("line.separator");
                              tx[i1].setId(i1);
                              tx[i1].setText(ad);
                              tx[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));

                              if (tx[i1].getParent() != null) {
                                  ((ViewGroup) tx[i1].getParent()).removeView(tx[i1]);
                              }


                              ll.addView(tx[i1]);
                              Name[i1] = ads.Name;
                              AdvertisementID[i1] = ads.AdvertisementID;
                              Date[i1] = ads.Date;
                              AdType[i1] = ads.AdType;
                              ApType[i1] = ads.ApType;
                              Location[i1] = ads.Location;
                              Gender[i1] = ads.Gender;
                              Utilities[i1] = ads.Utilities;
                              Availability[i1] = ads.Availability;
                              Rent_Share[i1] = ads.Rent_Share;
                              status[i1] = ads.status;
                              Date_av[i1] = ads.Date_av;
                              Description[i1] = ads.Description;
                              edit[i1].setId(i1);
                              edit[i1].setText("Edit");
                              edit[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

                              if (edit[i1].getParent() != null) {
                                  ((ViewGroup) edit[i1].getParent()).removeView(edit[i1]);
                              }
                              edit[i1].setTag(i1);
                              i=i1;
                              edit[i1].setOnClickListener(new View.OnClickListener() {

                                  @Override
                                  public void onClick(View view) {
                                      bun1 = new Bundle();
                                      for(int i3=0;i3<=i;i3++){
                                          if(edit[i3].getId()==view.getId()){
                                              Log.d("myterm",AdType[i3]);
                                              bun1.putString("Name", Name[i3]);
                                              bun1.putString("AdvertisementID",AdvertisementID[i3]);
                                              bun1.putString("Date", Date[i3]);
                                              bun1.putString("AdType", AdType[i3]);
                                              bun1.putString("ApType",ApType[i3]);
                                              bun1.putString("Location",Location[i3]);
                                              bun1.putString("Gender",Gender[i3]);
                                              bun1.putString("Utilities",Utilities[i3]);
                                              bun1.putString("Availability",Availability[i3]);
                                              bun1.putInt("Rent Share",Rent_Share[i3]);
                                              bun1.putString("Date_av",Date_av[i3]);
                                              bun1.putString("status", status[i3]);
                                              bun1.putString("Description",Description[i3]);
                                              Intent in = new Intent(getApplicationContext(), Edit_ad.class);
                                              in.putExtra("BunUser", bun);
                                              in.putExtra("Bunad", bun1);
                                              startActivity(in);}}
                                  }
                              });


                              ll.addView(edit[i1]);
                              if (delete[i1].getParent() != null) {
                                  ((ViewGroup) delete[i1].getParent()).removeView(delete[i1]);
                              }
                              delete[i1].setId((i1*1000)+1000);
                              delete[i1].setText("Delete");
                              delete[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                              delete[i1].setOnClickListener(new View.OnClickListener(){

                                  @Override
                                  public void onClick(View view){
                                    for(int i3=0;i3<=i;i3++){
                                      if(delete[i3].getId()==view.getId()) {
                                          FirebaseDatabase fbd = FirebaseDatabase.getInstance();
                                          DatabaseReference dbr = fbd.getReference("/Advertisements/" + Date[i3] + "/" + AdvertisementID[i3] + "/status");
                                          dbr.setValue("Blocked");
                                          Intent i = new Intent(getApplicationContext(), My_ad.class);
                                          i.putExtras(bun);
                                          startActivity(i);
                                      }
                                    }

                                  }
                              });
                              ll.addView(delete[i1]);


                              Log.d("textview" + i, Integer.toString(tx[i1].getId()));
                              Log.d("button" + i, Integer.toString(edit[i1].getId()));
                          }
                          i1++;
                      }
                  }
              }
              i=i1;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            }

    @Override
    public void onClick(View view) {
       int ind1;
       for(int i2=0;i2<i;i2++){
          if(edit[i2].getId()==view.getId()){
            ind1=i2;
              bun1 = new Bundle();
              bun1.putString("Name",Name[ind1]);
              bun1.putString("AdvertisementID",AdvertisementID[ind1]);
              bun1.putString("Date",Date[ind1]);
              bun1.putString("AdType",AdType[ind1]);
              bun1.putString("ApType",ApType[ind1]);
              bun1.putString("Location",Location[ind1]);
              bun1.putString("Gender",Gender[ind1]);
              bun1.putString("Utilities",Utilities[ind1]);
              bun1.putString("Availability",Availability[ind1]);
              bun1.putInt("Rent Share",Rent_Share[ind1]);
              bun1.putString("Date_av",Date_av[ind1]);
              bun1.putString("status",status[ind1]);
              bun1.putString("Description",Description[ind1]);
              Intent in = new Intent(getApplicationContext(),Edit_ad.class);
              in.putExtra("BunUser",bun);
              in.putExtra("Bunad",bun1);
              startActivity(in);
          }
          else if(delete[i2].getId()==view.getId()){
              ind1=i2;
              FirebaseDatabase fbd = FirebaseDatabase.getInstance();
              DatabaseReference dbr = fbd.getReference("/Advertisements/"+Date[ind1]+"/"+AdvertisementID[ind1]+"/status");
              dbr.setValue("Blocked");
              Intent in = new Intent(getApplicationContext(),My_ad.class);
              in.putExtras(bun);
              startActivity(in);

          }

       }
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(),Options.class);
        i.putExtras(bun);
        startActivity(i);
    }
}
