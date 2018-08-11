package com.example.dell.mynswiper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class showactivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Intent i;
    TextView one, two, three, four, five;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showactivity);
        i = getIntent();
        one = findViewById(R.id.t_one);
        two = findViewById(R.id.t_two);
        three = findViewById(R.id.t_three);
        four = findViewById(R.id.t_four);
        five = findViewById(R.id.t_five);
        sharedPreferences =getSharedPreferences("abc" , MODE_PRIVATE);
        String s1 = sharedPreferences.getString(MainActivity.first_key , "");
        String s2 = sharedPreferences.getString(MainActivity.second_key, "");
        String s3 = sharedPreferences.getString(MainActivity.three_key,"");
        String s4 = sharedPreferences.getString(MainActivity.fourth_key, "");
        String s5 = sharedPreferences.getString(MainActivity.five_key,"");
        if(s1 != "ab") {
            one.setText(s1);
        }
        if(s2!= "ty") {
            two.setText(s2);
        }
        if(s3!= "ad") {
            three.setText(s3);
        }
        if(s4!= "ad") {
            four.setText(s4);
        }
        if(s5!= "rt") {
            five.setText(s5);
        }
        String ty = i.getStringExtra(MainActivity.first_key);
        if(ty != "ab") {
            one.setText(ty);
        }
        String yt =i.getStringExtra(MainActivity.second_key);
        if(yt != "ty") {
            two.setText(yt);
        }
        String rt =i.getStringExtra(MainActivity.three_key);
        if(rt != "ad") {
            three.setText(rt);
        }
        String gh =i.getStringExtra(MainActivity.fourth_key);
        if(gh!= "rt") {
            four.setText(gh);
        }
         String hg =i.getStringExtra(MainActivity.five_key);
        if(hg!= "da") {
            five.setText(hg);
        }
            SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(MainActivity.fifth,hg );
        editor.putString(MainActivity.first_key, ty);
        editor.putString(MainActivity.second_key, yt);
        editor.putString(MainActivity.three_key ,rt );
        editor.putString(MainActivity.fourth_key, gh);
        editor.commit();



    }
}
