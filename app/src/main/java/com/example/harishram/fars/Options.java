package com.example.harishram.fars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    Bundle bun;
    Button fup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        bun = getIntent().getExtras();
        final Button updatePass = (Button) findViewById(R.id.button6);
        TextView tv = (TextView) findViewById(R.id.textView18);
        final Button logout = (Button) findViewById(R.id.button13);
        final Button pad = (Button) findViewById(R.id.button7);
        final Button myad = (Button) findViewById(R.id.button8);
        final Button sap = (Button) findViewById(R.id.button12);
        final Button srm = (Button) findViewById(R.id.button11);
        logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
               Intent i = new Intent(getApplicationContext(),Login.class);
               Toast toast = Toast.makeText(getApplicationContext(),"Logout Successfull",Toast.LENGTH_LONG);
               toast.setDuration(Toast.LENGTH_LONG);
               toast.show();
               startActivity(i);
            }
        });
        updatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),UpdatePasswordActivity.class);
                i.putExtras(bun);
                startActivity(i);
            }
        });

        pad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(getApplicationContext(),Post_ad.class);
               i.putExtras(bun);
               startActivity(i);
            }
        });

        myad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),My_ad.class);
                i.putExtras(bun);
                startActivity(i);
            }
        });
        sap.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Find.class);
                i.putExtras(bun);
                startActivity(i);
            }
        });
        srm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Find2.class);
                i.putExtras(bun);
                startActivity(i);
            }
        });
    }
}
