package com.example.harishram.fars;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class admin_view_report extends AppCompatActivity {

    private DatabaseReference db;
    private ListView lv;
    ArrayList<Advertisement> advertisements = new ArrayList<Advertisement>();
    AdvertisementAdapter adapter;
    Bundle bun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_report);
        bun = getIntent().getExtras();
        lv = (ListView) findViewById(R.id.list);
        adapter = new AdvertisementAdapter(this,R.layout.activity_view_advertisements_list_template,advertisements);
        lv.setAdapter(adapter);
        db = FirebaseDatabase.getInstance().getReference("Advertisements/");

        db.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dss:dataSnapshot.getChildren()){
                   for(DataSnapshot dss1:dss.getChildren()){
                      Advertisement ad = dss1.getValue(Advertisement.class);
                       if(("Reported").equals(ad.status)){
                           adapter.add(ad);
                       }
                   }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        DatabaseReference.goOffline();
    }


    @Override
    public void onBackPressed() {
        Intent AdminHome = new Intent(getApplicationContext(), Options2.class);
        AdminHome.putExtras(bun);
        startActivity(AdminHome);
    }

}

