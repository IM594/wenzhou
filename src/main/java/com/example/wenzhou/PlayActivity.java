package com.example.wenzhou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayActivity extends AppCompatActivity {
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_top,new quanbuFragment())
                .commit();
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_top,new quanbuFragment())
                        .commit();
                b1.setTextColor(Color.parseColor("#FFFAFA"));
                b2.setTextColor(Color.parseColor("#4F4F4F"));
                b3.setTextColor(Color.parseColor("#4F4F4F"));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_top,new shanshuiFragment())
                        .commit();
                b2.setTextColor(Color.parseColor("#FFFAFA"));
                b1.setTextColor(Color.parseColor("#4F4F4F"));
                b3.setTextColor(Color.parseColor("#4F4F4F"));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_top,new chiheFragment())
                        .commit();
                b3.setTextColor(Color.parseColor("#FFFAFA"));
                b1.setTextColor(Color.parseColor("#4F4F4F"));
                b2.setTextColor(Color.parseColor("#4F4F4F"));
            }
        });
    }
}
