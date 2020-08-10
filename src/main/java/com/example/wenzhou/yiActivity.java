package com.example.wenzhou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class yiActivity extends AppCompatActivity {
    ImageView ox,gc,ousu,kq,md,ms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi);
        ox =findViewById(R.id.ouxiu);
        gc =findViewById(R.id.guci);
        ousu =findViewById(R.id.ousu);
        kq =findViewById(R.id.kunqu);
        md =findViewById(R.id.mudiao);
        ms =findViewById(R.id.misu);
        ox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yiActivity.this, ouxiuActivity.class);
                startActivity(intent);
            }
        });
        gc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yiActivity.this, guciActivity.class);
                startActivity(intent);
            }
        });
        ousu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yiActivity.this, ousuActivity.class);
                startActivity(intent);
            }
        });
        kq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yiActivity.this, kunquActivity.class);
                startActivity(intent);
            }
        });
        md.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yiActivity.this, mudiaoActivity.class);
                startActivity(intent);
            }
        });
        ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(yiActivity.this, misuActivity.class);
                startActivity(intent);
            }
        });


}
}
