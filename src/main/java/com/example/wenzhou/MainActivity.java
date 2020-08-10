package com.example.wenzhou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton ImbUser,ImbEat,ImbPlay,Imbou,Imbyi;
    TextView a1,a2,a3,a4,a5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImbEat = findViewById(R.id.Fragment_b2);
        ImbUser = findViewById(R.id.Fragment_b5);
        ImbPlay=findViewById(R.id.Fragment_b3);
        Imbou = findViewById(R.id.Fragment_b1);
        Imbyi=findViewById(R.id.Fragment_b4);
        a1=findViewById(R.id.a1);
        a2=findViewById(R.id.a2);
        a3=findViewById(R.id.a3);
        a4=findViewById(R.id.a4);
        a5=findViewById(R.id.a5);


        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_top,new wzFragment())
                .commit();


        ImbUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();//该类用于开启一个事务
                fragmentManager.beginTransaction()//开启一个Fragment事务
                        .replace(R.id.fragment_top,new UserFragment())//参数1：占位容器的ID a2：传入Fragment的文件名
                        .commit();//提交事务
                a5.setTextColor(Color.parseColor("#009ACD"));
                a1.setTextColor(Color.parseColor("#363636"));
                a2.setTextColor(Color.parseColor("#363636"));
                a3.setTextColor(Color.parseColor("#363636"));
                a4.setTextColor(Color.parseColor("#363636"));
            }
        });
        ImbEat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_top,new eatFragment())
                        .commit();
                a3.setTextColor(Color.parseColor("#009ACD"));
                a1.setTextColor(Color.parseColor("#363636"));
                a2.setTextColor(Color.parseColor("#363636"));
                a5.setTextColor(Color.parseColor("#363636"));
                a4.setTextColor(Color.parseColor("#363636"));
            }
        });
        ImbPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_top,new PlayFragment())
                        .commit();
                a2.setTextColor(Color.parseColor("#009ACD"));
                a1.setTextColor(Color.parseColor("#363636"));
                a3.setTextColor(Color.parseColor("#363636"));
                a5.setTextColor(Color.parseColor("#363636"));
                a4.setTextColor(Color.parseColor("#363636"));
            }
        });
        Imbou.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_top,new wzFragment())
                        .commit();
                a1.setTextColor(Color.parseColor("#009ACD"));
                a3.setTextColor(Color.parseColor("#363636"));
                a2.setTextColor(Color.parseColor("#363636"));
                a5.setTextColor(Color.parseColor("#363636"));
                a4.setTextColor(Color.parseColor("#363636"));
            }
        });
        Imbyi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_top,new yiFragment())
                        .commit();
                a4.setTextColor(Color.parseColor("#009ACD"));
                a1.setTextColor(Color.parseColor("#363636"));
                a2.setTextColor(Color.parseColor("#363636"));
                a5.setTextColor(Color.parseColor("#363636"));
                a3.setTextColor(Color.parseColor("#363636"));
            }
        });
    }
}
