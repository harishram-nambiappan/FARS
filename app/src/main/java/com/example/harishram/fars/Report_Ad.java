package com.example.harishram.fars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Report_Ad extends AppCompatActivity {
    DatabaseReference dbr;
    Advertisement ads;
    Bundle bun,bun1,bun2,bun3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report__ad);
        bun = getIntent().getExtras();
        bun1 = bun.getBundle("bunterm");
        bun2 = bun.getBundle("buninfo");
        bun3 = bun.getBundle("bunad");
        final Spinner Reason_report = (Spinner) findViewById(R.id.Report_reason);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Report_reason,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Reason_report.setAdapter(adapter);
        final EditText comments = (EditText) findViewById(R.id.editText6);
        Button submit = (Button) findViewById(R.id.button17);
        Button cancel = (Button) findViewById(R.id.button18);
        dbr = FirebaseDatabase.getInstance().getReference("/Advertisements/"+bun3.getString("Date")+"/"+bun3.getString("AdvertisementID"));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference db1 = dbr.child("status");
                db1.setValue("Reported");
                DatabaseReference db2 = dbr.child("comment");
                db2.setValue(comments.getText().toString());
                DatabaseReference db3 = dbr.child("reason");
                db3.setValue(Reason_report.getSelectedItem().toString());
                Log.d("reterm",bun1.getString("Septerm"));
                Intent i = new Intent(getApplicationContext(),Search_R1.class);
                i.putExtra("bunterm",bun1);
                i.putExtra("buninfo",bun2);
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Search_R1.class);
                i.putExtra("bunterm",bun1);
                i.putExtra("buninfo",bun2);
                startActivity(i);
            }
        });
    }
}
