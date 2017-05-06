package com.example.harishram.fars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class Find2 extends AppCompatActivity {

    RadioGroup rg1,rg2,rg3;
    int rate=0;
    RadioButton rb1,rb2,rb3;
    SeekBar sb;
    TextView tx;
    Button submit;
    String avl,location,gender;
    Bundle b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find2);
        b1 = getIntent().getExtras();
        rg1 = (RadioGroup) findViewById(R.id.rgf4);
        rg2 = (RadioGroup) findViewById(R.id.rgf5);
        rg3 = (RadioGroup) findViewById(R.id.rgf6);
        sb = (SeekBar) findViewById(R.id.seekBar);
        tx = (TextView) findViewById(R.id.textView12);
        submit = (Button) findViewById(R.id.button16);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tx.setText(Integer.toString(i));
                rate=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int rgf1id = rg1.getCheckedRadioButtonId();
                int rgf2id = rg2.getCheckedRadioButtonId();
                int rgf3id = rg3.getCheckedRadioButtonId();
                rb1 = (RadioButton) findViewById(rgf1id);
                rb2 = (RadioButton) findViewById(rgf2id);
                rb3 = (RadioButton) findViewById(rgf3id);
                avl = rb1.getText().toString();
                location = rb2.getText().toString();
                gender = rb3.getText().toString();
                Bundle b = new Bundle();
                b.putString("Septerm",avl);
                b.putString("Location",location);
                b.putString("Gender",gender);
                b.putInt("Rent Share",rate);
                Intent i = new Intent(getApplicationContext(),Search_R1.class);
                i.putExtra("buninfo",b1);
                i.putExtra("bunterm",b);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(),Options.class);
        i.putExtras(b1);
        startActivity(i);
    }
}
