package com.example.wenzhou;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    EditText  mima,userAccount, userBirth;
    Button btSubmit, quxiao;
    private MyDBOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btSubmit = findViewById(R.id.zhuce);
        userAccount = findViewById(R.id.e2);
        userBirth = findViewById(R.id.e4);
        mima=findViewById(R.id.e3);
        quxiao = findViewById(R.id.quxiao);
        //实例化数据库 创建SC_Database.db数据库文件 参数为数据库名称 游标工厂 版本号
        dbOpenHelper=new MyDBOpenHelper(getApplicationContext(),"WZ_Database.db",null,2);

        userBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//datepicker日期选择器
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override//OnDateSetListener 是用户日期设置完成后的回调监听器
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {//
                        // 该接口的void onDateSet(DatePicker view, int year, int month, int dayOfMonth);方法内可以获取到用户设置的日期信息，在该方法内我们可以对用户输入的日期信息做进一步的操作。
                        userBirth.setText(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth));
                    }
                }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                //参数year、month、dayOfMonth用来初始化DatePickerDialog默认显示的日期。不过要注意参数month与Calendar类的month类似，都是从0开始表示，即1-12月依次对应0-11。通常我们可以用Calendar.getInstance();获取到一个表示当前系统日期的Calendar对象，用该对象来初始化DatePickerDialog
                datePickerDialog.show();
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbReader = dbOpenHelper.getReadableDatabase();
                Cursor c = dbReader.query("users", null, "_account=?",
                        new String[]{userAccount.getText().toString()}, null, null, null);
                if(c != null && c.getCount() >= 1){
                    Toast.makeText(RegisterActivity.this, "账号重复", Toast.LENGTH_SHORT).show();
                }
                if(mima.getText().toString().trim().equals("")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                    dialog.setTitle("警告")//alertdialog 对话框
                            .setMessage("请输入密码")
                            .setPositiveButton("确定", null)
                            .show();
                }
                else if(userAccount.getText().toString().trim().equals("")){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(RegisterActivity.this);
                    dialog.setTitle("警告")//alertdialog 对话框
                            .setMessage("请输入账号")
                            .setPositiveButton("确定",null)
                            .show();
                }
                else {
                    Intent intent = new Intent();
                    SQLiteDatabase dbWriter=dbOpenHelper.getWritableDatabase();
                    //getWritableDatabase方法可以获得具有写入权限的SQLiteDatabase对象
                    ContentValues cv=new ContentValues();//一次性缓存
                    cv.put("_account",userAccount.getText().toString());
                    cv.put("keyword",mima.getText().toString());
                    cv.put("birth",userBirth.getText().toString());
                    if(dbWriter.insert("users",null,cv)<0){
                        //table为想要插入数据的表名字 nullColunmnHack为是否允许有空行 values为要插入的值 第一条为0
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        finish();
                        //结束当前的Activity，返回
                    }
                }
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finish();
                //结束当前的Activity，返回
            }
        });
    }
}
