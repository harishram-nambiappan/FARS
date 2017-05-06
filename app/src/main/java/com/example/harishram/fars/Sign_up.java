package com.example.harishram.fars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;


public class Sign_up extends AppCompatActivity {

    DatabaseReference mDatabase;
    Firebase fbu;
    int Sid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Firebase.setAndroidContext(this);
        fbu = new Firebase("https://fars-6636e.firebaseio.com").child("Student");
        final EditText Stid = (EditText) findViewById(R.id.editText3);
        final EditText netid = (EditText) findViewById(R.id.editText18);
        final EditText Name = (EditText) findViewById(R.id.editText4);
        final EditText email = (EditText) findViewById(R.id.editText5);
        final EditText password = (EditText) findViewById(R.id.editText8);
        final Spinner Security_Question = (Spinner) findViewById(R.id.Security_Question);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Security_Questions, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Security_Question.setAdapter(adapter);
        final EditText Security_Answer = (EditText) findViewById(R.id.editText10);
        final EditText Phone_No = (EditText) findViewById(R.id.editText11);
        Button Submit = (Button) findViewById(R.id.button5);
        Submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
             if((Stid.getText().toString().equals(""))||(netid.getText().toString().equals(""))||(Name.getText().toString().equals(""))||(email.getText().toString().equals(""))||(password.getText().toString().equals(""))||(Security_Answer.getText().toString().equals(""))||(Phone_No.getText().toString().equals(""))) {
                 Toast toast2 = Toast.makeText(getApplicationContext(), "Enter all the required details", Toast.LENGTH_LONG);
                 toast2.setDuration(Toast.LENGTH_LONG);
                 toast2.show();
             }
             else{
                if(email.getText().toString().endsWith("@mavs.uta.edu")){
                   String Sid = Stid.getText().toString();
                   Sid1 = Integer.parseInt(Sid);
                   String NetID = netid.getText().toString();
                   String name = Name.getText().toString();
                   String em = email.getText().toString();
                   String pass = password.getText().toString();
                   String sq = Security_Question.getSelectedItem().toString();
                   String sa = Security_Answer.getText().toString();
                   String pn = Phone_No.getText().toString();
                   Long pn1 = Long.parseLong(pn);
                   Student Sd = new Student(Sid1, NetID, name, em, pass, sq, sa, pn1, "Active", "Student");
                   Firebase fbnu = fbu.child(NetID);
                   fbnu.setValue(Sd);
                   Toast toast1 = Toast.makeText(getApplicationContext(), "New user successfully registered", Toast.LENGTH_LONG);
                   toast1.setDuration(Toast.LENGTH_LONG);
                   toast1.show();
                   Intent i = new Intent(getApplicationContext(), Login.class);
                   startActivity(i);
               }
               else{
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Email id should end with '@mavs.uta.edu'", Toast.LENGTH_LONG);
                    toast1.setDuration(Toast.LENGTH_LONG);
                    toast1.show();
                }

             }

            }
        });

    }

}

