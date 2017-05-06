package com.example.harishram.fars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class Options2 extends AppCompatActivity {

    Button vrp;
    Button vu;
    Button logout;
    Bundle bun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options2);
        //final Button logout = (Button) findViewById(R.id.button14);
        vrp = (Button) findViewById(R.id.button10);
        vu = (Button) findViewById(R.id.button9);
        logout = (Button)findViewById(R.id.button14);
        bun = getIntent().getExtras();
        logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i= new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }});
        vrp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               Intent i = new Intent(getApplicationContext(),admin_view_report.class);
               i.putExtras(bun);
               startActivity(i);
            }
        });
        vu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),View_users.class);
                i.putExtras(bun);
                startActivity(i);
            }
        });
    }
}
