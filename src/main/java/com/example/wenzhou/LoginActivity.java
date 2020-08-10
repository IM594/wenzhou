package com.example.wenzhou;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView zhuce;
    Button login;
    EditText userAccount,password;
    private MyDBOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        zhuce = (TextView) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        userAccount = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        dbOpenHelper = new MyDBOpenHelper(getApplicationContext(), "WZ_Database.db", null, 2);

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击跳转到ResigterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbReader = dbOpenHelper.getReadableDatabase();
                //调用本方法可以得到对数据库具有读的权限的SQLiteDatabase实例对象 该方法返回一个Cursor对象
                Cursor c = dbReader.query("users", null, "_account=? and keyword=?",
                        new String[]{userAccount.getText().toString(), password.getText().toString()}, null, null, null);
                //query方法将select语句的内容定义为各参数，除了数据表名，其他参数都可以是null
                //table:查询数据表的表名 columns：按查询要求返回的列名数组 selection：相当于where语句，可以是_id=？
                //selectionArgs：查寻条件所需的参数值 groupby：定义查询是否分组 having:分组条件表达式 orderby：查询结果排序依据的列 limit:限定查询返回的行数
                if (c != null && c.getCount() >= 1) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor=getSharedPreferences("UserInfo",MODE_PRIVATE).edit();
                    //使用Activity类的getSharedPreferences方法获得SharedPreferences对象，其中存储key-value的文件的名称由getSharedPreferences方法的第一个参数指定，第二个参数指定访问应用程序私有文件的权限。
                    //使用SharedPreferences接口的edit获得SharedPreferences.Editor对象。
                    editor.putString("user",userAccount.getText().toString());
                    editor.putString("password",password.getText().toString());
                    //通过SharedPreferences.Editor接口的putXxx方法保存key-value对。其中Xxx表示不同的数据类型。
                    editor.apply();//提交 在device file explorer中找data-data-本文件夹-shared_preferences

                    Intent intent = new Intent();
                    intent.putExtra("user", userAccount.getText().toString());
                    //putExtra方法可以将数据参数加入到Intent对象中，采用键值对的形式保存数据。
                    setResult(2, intent);
                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(LoginActivity.this, "输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}









