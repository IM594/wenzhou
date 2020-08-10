package com.example.wenzhou;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import static android.Manifest.permission.CALL_PHONE;


public class HelpActivity extends AppCompatActivity {
    ImageView ibCall;
    ImageView ibMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ibCall = findViewById(R.id.phone);
        ibMessage = findViewById(R.id.message);

        ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent();
                intentCall.setAction(Intent.ACTION_CALL);
                intentCall.setData(Uri.parse("tel:5556"));
                if (ContextCompat.checkSelfPermission(HelpActivity.this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intentCall);
                    //判断相关权限是否已经被授予
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
            }
        });

        ibMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSms = new Intent();
                intentSms.setAction(Intent.ACTION_SENDTO);
                intentSms.setData(Uri.parse("smsto:5556"));
                intentSms.putExtra("sms_body","想要咨询的问题");
                startActivity(intentSms);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){//创建菜单
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.regmenu,menu);
        //调用menuinflater对象的inflate()方法就可以引用定义的菜单资源
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){//响应和处理菜单项的点击
        switch (item.getItemId()){
            case R.id.app_version:
                AlertDialog.Builder builder=new AlertDialog.Builder(HelpActivity.this);
                builder.setTitle("版本信息") ;
                builder.setMessage("开发者：赵婧卉\n日期：2020年1月1日") ;
                builder.setPositiveButton("确定",null);
                builder.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
};