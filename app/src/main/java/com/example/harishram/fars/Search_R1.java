package com.example.harishram.fars;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Search_R1 extends AppCompatActivity {

    String[] Name = new String[1000];
    String[] AdvertisementID=new String[1000];
    String[] Date= new String[1000];
    String[] AdType= new String[1000];
    String[] ApType=new String[1000];
    String[] Location=new String[1000];
    String[] Gender=new String[1000];
    String[] Utilities=new String[1000];
    String[] Availability=new String[1000];
    String[] Description=new String[1000];
    int[] Rent_Share= new int[1000];
    String[] Date_av= new String[1000];
    String[] status=new String[1000];
    Bundle bun1,bun2;
    Advertisement ads;
    DatabaseReference dbr;
    Bundle bun;
    LinearLayout ll;
    String ad;
    Button[] report = new Button[1000];
    TextView [] tx = new TextView[1000];
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__r1);

        for(int j=0;j<1000;j++){
            tx[j] = new TextView(this);
            report[j] = new Button(this);}
        bun = getIntent().getExtras();
        bun1 = bun.getBundle("bunterm");
        bun2 = bun.getBundle("buninfo");
        ll = (LinearLayout) findViewById(R.id.activity_search__r1);
        dbr = FirebaseDatabase.getInstance().getReference("/Advertisements");
        Log.d("term",bun1.getString("Septerm"));
            dbr.addValueEventListener(new ValueEventListener() {
                int i1 = 0;

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot dss1:dataSnapshot.getChildren()) {
                        for (DataSnapshot dss : dss1.getChildren()) {
                            ads = dss.getValue(Advertisement.class);
                            Log.d("term1", bun2.getString("Name"));
                            if (!(ads.Name).equals(bun2.getString("Name"))) {
                                if ((bun1.getString("Septerm").contains("Temporary")) || (bun1.getString("Septerm").contains("Permanent"))) {
                                    if ((ads.Availability.equals(bun1.getString("Septerm"))) && (ads.Location.equals(bun1.getString("Location"))) && (ads.Gender.equals(bun1.getString("Gender"))) && (ads.Rent_Share <= bun1.getInt("Rent Share")) && (ads.status.equals("Active"))) {
                                        ad = "Posted on " + ads.Date + System.getProperty("line.separator");
                                        ad += "Roommate Required ";
                                        ad += ads.ApType + " type";
                                        ad += ads.Location + " " + ads.Gender + " only" + " " + ads.Utilities + " included" + " " + ads.Availability + " availability" + " Available from " + ads.Date_av;
                                        ad += " Rent Share: $" + ads.Rent_Share + " Phone No:" + " " + bun2.getLong("Phone No") + System.getProperty("line.separator");
                                        tx[i1].setId(i1);
                                        tx[i1].setText(ad);
                                        tx[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
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
                                        Description[i1]=ads.Description;
                                        report[i1].setId(i1);
                                        report[i1].setText("Report Advertisements");
                                        report[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                                        i=i1;
                                        report[i1].setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View view) {
                                                Bundle bun3 = new Bundle();
                                                for(int i3=0;i3<=i;i3++){
                                                    if(report[i3].getId()==view.getId()){
                                                        Log.d("myterm",AdType[i3]);
                                                        bun3.putString("Name", Name[i3]);
                                                        bun3.putString("AdvertisementID",AdvertisementID[i3]);
                                                        bun3.putString("Date", Date[i3]);
                                                        bun3.putString("AdType", AdType[i3]);
                                                        bun3.putString("ApType",ApType[i3]);
                                                        bun3.putString("Location",Location[i3]);
                                                        bun3.putString("Gender",Gender[i3]);
                                                        bun3.putString("Utilities",Utilities[i3]);
                                                        bun3.putString("Availability",Availability[i3]);
                                                        bun3.putInt("Rent Share",Rent_Share[i3]);
                                                        bun3.putString("Date_av",Date_av[i3]);
                                                        bun3.putString("status", status[i3]);
                                                        bun3.putString("Description",Description[i3]);
                                                        Intent in = new Intent(getApplicationContext(), Report_Ad.class);
                                                        in.putExtra("bunterm", bun1);
                                                        in.putExtra("bunad",bun3);
                                                        in.putExtra("buninfo", bun2);
                                                        startActivity(in);}}
                                            }
                                        });
                                        ll.addView(report[i1]);

                                    }
                                }
                                if ((bun1.getString("Septerm").contains("Studio")) || (bun1.getString("Septerm").contains("One Bedroom-Furnished")) || (bun1.getString("Septerm").contains("Two Bedroom-Furnished"))) {
                                    Log.d("term2", ads.Location);
                                    Log.d("term3", " " + bun1.getString("Septerm"));
                                    if ((ads.ApType.contains(bun1.getString("Septerm"))) && (ads.Location.equals(bun1.getString("Location"))) && (ads.Gender.equals(bun1.getString("Gender"))) && (ads.Rent_Share <= bun1.getInt("Rent Share")) && (ads.status.equals("Active"))) {
                                        ad = "Posted on " + ads.Date + System.getProperty("line.separator");
                                        ad += "House for Rent ";
                                        ad += ads.ApType + " type";
                                        ad += ads.Location + " " + ads.Gender + " only" + " " + ads.Utilities + " included" + " " + ads.Availability + " availability" + " Available from " + ads.Date_av;
                                        ad += " Rent Share: $" + ads.Rent_Share + " Phone No:" + " " + bun2.getLong("Phone No") + System.getProperty("line.separator");
                                        //Log.d("term3",ad);
                                        tx[i1].setId(i1);
                                        tx[i1].setText(ad);
                                        tx[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
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
                                        Description[i1]=ads.Description;
                                        report[i1].setId(i1);
                                        report[i1].setText("Report Advertisements");
                                        report[i1].setLayoutParams(new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                                        i=i1;
                                        report[i1].setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View view) {
                                                Bundle bun3 = new Bundle();
                                                for(int i3=0;i3<=i;i3++){
                                                    if(report[i3].getId()==view.getId()){
                                                        Log.d("myterm",AdType[i3]);
                                                        bun3.putString("Name", Name[i3]);
                                                        bun3.putString("AdvertisementID",AdvertisementID[i3]);
                                                        bun3.putString("Date", Date[i3]);
                                                        bun3.putString("AdType", AdType[i3]);
                                                        bun3.putString("ApType",ApType[i3]);
                                                        bun3.putString("Location",Location[i3]);
                                                        bun3.putString("Gender",Gender[i3]);
                                                        bun3.putString("Utilities",Utilities[i3]);
                                                        bun3.putString("Availability",Availability[i3]);
                                                        bun3.putInt("Rent Share",Rent_Share[i3]);
                                                        bun3.putString("Date_av",Date_av[i3]);
                                                        bun3.putString("status", status[i3]);
                                                        bun3.putString("Description",Description[i3]);
                                                        Intent in = new Intent(getApplicationContext(), Report_Ad.class);
                                                        in.putExtra("bunterm",bun1);
                                                        in.putExtra("bunad",bun3);
                                                        in.putExtra("buninfo", bun2);
                                                        startActivity(in);}}
                                            }
                                        });
                                        ll.addView(report[i1]);

                                    }
                                }
                            }
                            i1++;
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
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(),Options.class);
        i.putExtras(bun2);
        startActivity(i);
    }
}
