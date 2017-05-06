package com.example.harishram.fars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UpdatePasswordActivity extends AppCompatActivity {

    private DatabaseReference db;
    Bundle bun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        bun = getIntent().getExtras();
        SharedPreferences getSharedData = getSharedPreferences("LOGIN_ID", MODE_PRIVATE);
        final String netID = getSharedData.getString("loginID", null);
        final EditText oldPass = (EditText) findViewById(R.id.oldPasswordUpdate);
        final EditText newPass = (EditText) findViewById(R.id.newPasswordUpdate);
        final EditText confirmPass = (EditText) findViewById(R.id.confirmPasswordUpdate);
        final Button reset = (Button) findViewById(R.id.update);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseDatabase.getInstance().getReference("Student/" + bun.getString("NetID"));
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            Toast.makeText(getApplicationContext(), "Fetch Error! Try Again", Toast.LENGTH_SHORT).show();
                        } else {
                            final Student student = dataSnapshot.getValue(Student.class);
                            String oldPassword = oldPass.getText().toString();
                            if (!TextUtils.isEmpty(oldPassword)) {
                                if (oldPassword.equals(student.password)) {
                                    String password = newPass.getText().toString();
                                    String confirm = confirmPass.getText().toString();
                                    if (!TextUtils.isEmpty(password)) {
                                        if (confirm.equals(password)) {
                                            db.child("password").setValue(password);
                                            Toast.makeText(getApplicationContext(), "Password Updated", Toast.LENGTH_SHORT).show();
                                            Intent StudentHome = new Intent(getApplicationContext(), Options.class);
                                            startActivity(StudentHome);
                                        } else {
                                            confirmPass.setError("Passwords don't match");
                                        }
                                    } else {
                                        newPass.setError("Cannot be empty!");
                                    }
                                }
                            } else {
                                oldPass.setError("Cannot be empty!");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });
            }

        });

    }

    @Override
    public void onBackPressed() {
        Intent StudentHome = new Intent(getApplicationContext(), Options.class);
        StudentHome.putExtras(bun);
        startActivity(StudentHome);
    }

}