package com.example.wenzhou;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //必须重写构造方法 参数分别为上下文环境、数据库名字、游标工厂、正在使用的数据库模型版本的参数
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //+换行的意思 id一定要有下划线 本方法用来创建数据表和初始化数据 execSQL来执行sql语句
        db.execSQL("CREATE TABLE users(" +
                "_account INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "keyword TEXT NOT NULL,"+
                "birth TEXT)");
        db.execSQL("CREATE TABLE agenda(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "_account INTEGER,"+
                "title TEXT NOT NULL,"+
                "date TEXT NOT NULL,"+
                "time TEXT NOT NULL,"+
                "content TEXT,"+
                "state TEXT NOT NULL)");
        db.execSQL("CREATE TABLE love(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT NOT NULL,"+
                "content TEXT NOT NULL,"+
                "kind TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新 三个参数分别为SQLiteDataBase对象 旧的版本号 新的版本号
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS agenda");
        db.execSQL("DROP TABLE IF EXISTS love");
        onCreate(db);
    }
}
