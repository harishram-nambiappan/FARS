package com.example.harishram.fars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText netid;
    EditText pass;
    Button enter,signup,forgot;
    String nid,pwd,nid1,pwd1,status,name,email,sq,sa,type;
    Long pn;
    FirebaseDatabase fbd;
    DatabaseReference dbr,dbr1;
    DataSnapshot dss;
    Student sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        netid = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
        Button new_user = (Button) findViewById(R.id.button3);
        signup = (Button) findViewById(R.id.button3);
        forgot = (Button) findViewById(R.id.button4);
        new_user.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i= new Intent(getApplicationContext(),Sign_up.class);
                startActivity(i);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
        enter = (Button) findViewById(R.id.button2);
        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               nid = netid.getText().toString();
               pwd = pass.getText().toString();
               fbd = FirebaseDatabase.getInstance();
               if(nid.startsWith("admin")){
                  dbr=fbd.getReference("/Admin/"+nid);
               }
               else{
               dbr = fbd.getReference("/Student/"+nid);}
               dbr.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       sd = dataSnapshot.getValue(Student.class);
                       nid1 = sd.NetID;
                       pwd1 = sd.password;
                       status = sd.status;
                       name=sd.Name;
                       email=sd.email;
                       sq=sd.Sq;
                       sa=sd.Sa;
                       pn=sd.pn;
                       type=sd.type;
                       Log.d("tag1",nid1);
                       Log.d("tag2",pwd1);
                       if((nid1.equals(nid))&&(pwd1.equals(pwd))&&(status.equals("Active"))){
                           Toast toast1 = Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG);
                           toast1.setDuration(Toast.LENGTH_LONG);
                           toast1.show();
                           if(type.equals("Student")) {
                               Intent i = new Intent(getApplicationContext(), Options.class);
                               Bundle bun = new Bundle();
                               bun.putString("NetID",nid1);
                               bun.putString("Password",pwd1);
                               bun.putString("Status",status);
                               bun.putString("Name",name);
                               bun.putString("Email",email);
                               bun.putString("Security Question",sq);
                               bun.putString("Security Answer",sa);
                               bun.putLong("Phone No",pn);
                               bun.putString("Type",type);
                               i.putExtras(bun);
                               startActivity(i);
                           }
                           else if(type.equals("Admin")){
                               Intent i = new Intent(getApplicationContext(), Options2.class);
                               Bundle bun = new Bundle();
                               bun.putString("NetID",nid1);
                               bun.putString("Password",pwd1);
                               bun.putString("Status",status);
                               bun.putString("Name",name);
                               bun.putString("Email",email);
                               bun.putString("Security Question",sq);
                               bun.putString("Security Answer",sa);
                               bun.putLong("Phone No",pn);
                               bun.putString("Type",type);
                               i.putExtras(bun);
                               startActivity(i);
                           }
                       }
                       else{
                           Toast toast1 = Toast.makeText(getApplicationContext(),"Invalid NetID or password.Try again",Toast.LENGTH_LONG);
                           toast1.setDuration(Toast.LENGTH_LONG);
                           toast1.show();

                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Sign_up.class);
                startActivity(i);
            }
        });
    }
}
