package com.example.wenzhou;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class SQliteActivity extends AppCompatActivity {
    EditText et_date,et_time,et_title,et_content;
    private MyDBOpenHelper dbOpenHelper;
    Button bt_insert,bt_all,bt_clear;
    TextView tv_infor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        et_date=findViewById(R.id.textView3);
        et_time=findViewById(R.id.textView4);
        et_content=findViewById(R.id.textView2);
        et_title=findViewById(R.id.textView);
        bt_insert=findViewById(R.id.button2);
        tv_infor=findViewById(R.id.textView5);
        bt_all=findViewById(R.id.button3);
        bt_clear=findViewById(R.id.button);

        //实例化数据库 创建SC_Database.db数据库文件 参数为数据库名称 游标工厂 版本号
        dbOpenHelper=new MyDBOpenHelper(getApplicationContext(),"SC_Database.db",null,1);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//datepicker日期选择器
                DatePickerDialog datePickerDialog=new DatePickerDialog(SQliteActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override//OnDateSetListener 是用户日期设置完成后的回调监听器
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        et_date.setText(new StringBuilder().append(year).append("-").append(month+1).append("-").append(dayOfMonth));
                    }
                }, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(SQliteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        et_time.setText(new StringBuilder().append(hourOfDay).append(":").append(minute));
                    }
                },0,0,true);
                timePickerDialog.show();
            }
        });

        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state="待办";
                SQLiteDatabase dbWriter=dbOpenHelper.getWritableDatabase();
                //getWritableDatabase方法可以获得具有写入权限的SQLiteDatabase对象
                ContentValues cv=new ContentValues();//一次性缓存
                cv.put("title",et_title.getText().toString());
                cv.put("date",et_date.getText().toString());
                cv.put("time",et_time.getText().toString());
                cv.put("content",et_content.getText().toString());
                cv.put("state",state);
                if(dbWriter.insert("agenda",null,cv)<0){
                    //table为想要插入数据的表名字 nullColunmnHack为是否允许有空行 values为要插入的值 第一条为0
                    tv_infor.setText("创建失败");
                }else{
                    tv_infor.setText("创建成功");
                }
            }
        });
        bt_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SQliteActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                et_title.setText("");
                et_content.setText("");
                et_date.setText("");
                et_time.setText("");
                tv_infor.setText("");
            }
        });
    }
}
