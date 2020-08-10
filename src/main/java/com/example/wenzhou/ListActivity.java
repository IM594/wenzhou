package com.example.wenzhou;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    MyDBOpenHelper dbOpenHelper;
    SQLiteDatabase dbReader, dbWriter;
    SimpleCursorAdapter adapter;
    int state;
    String[] args;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.list_content);
        dbOpenHelper = new MyDBOpenHelper(getApplicationContext(), "SC_Database.db", null, 1);
        dbReader = dbOpenHelper.getReadableDatabase();
        dbWriter = dbOpenHelper.getWritableDatabase();
        showAll();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?>parent, View view, int position, long id){
                TextView itemID=view.findViewById(R.id.list_id);
                args=new String[]{itemID.getText().toString()};
                Cursor c=dbReader.query("agenda",null,null,null,null,null,"_id desc","0,1");
                c.moveToFirst();
                String title=c.getString(c.getColumnIndex("title"));
                String content=c.getString(c.getColumnIndex("content"));
                AlertDialog.Builder builder=new AlertDialog.Builder(ListActivity.this);
                builder.setTitle("日期标题:"+title);
                builder.setMessage("内容:"+content);
                builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContentValues cv=new ContentValues();
                        cv.put("state","完成");
                        dbWriter.update("agenda",cv,"_id=?",args);
                        showAll();
                    }
                });
                builder.setNegativeButton("待办", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContentValues cv = new ContentValues();
                        cv.put("state", "待办");
                        dbWriter.update("agenda", cv, "_id=?", args);
                        showAll();
                    }
                });
                builder.show();
            }
        });
    }
    public void showAll() {
        Cursor cs = dbReader.query("agenda", null, null, null, null, null, "date,time");
        cs.moveToFirst();
        String[] from = {"_id", "date", "time", "title", "state"};
        int[] to = {R.id.list_id, R.id.list_date, R.id.list_time, R.id.list_title, R.id.list_state};
        adapter = new SimpleCursorAdapter(ListActivity.this, R.layout.listitem,
                cs, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
    }
    protected  void onDestroy(){
        super.onDestroy();
        dbReader.close();
        dbWriter.close();
    }
}
