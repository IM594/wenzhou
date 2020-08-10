package com.example.wenzhou;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class LoveActivity extends AppCompatActivity {
    MyDBOpenHelper dbOpenHelper;
    ListView listView;
    SQLiteDatabase dbReader,dbWriter;
    SimpleCursorAdapter adapter;
    String[] args;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        listView = findViewById(R.id.list_content);
        dbOpenHelper = new MyDBOpenHelper(getApplicationContext(), "S_Database.db", null, 1);
        dbReader = dbOpenHelper.getReadableDatabase();
        dbWriter = dbOpenHelper.getWritableDatabase();
        Cursor c=dbReader.query("love",null,null,null,null,null,null,null);
        c.moveToFirst();
        String[] from = {"name", "content", "kind"};
        int[] to = {R.id.list_name, R.id.list_content, R.id.kind};
        adapter = new SimpleCursorAdapter(LoveActivity.this, R.layout.list,
                c, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            public boolean onItemLongClick(AdapterView<?>parent, View view, int position, long id) {
                TextView name = view.findViewById(R.id.list_name);
                dbWriter.delete("love", "name=?", new String[]{name.getText().toString()});
                Cursor c=dbReader.query("love",null,null,null,null,null,null,null);
                c.moveToFirst();
                String[] from = {"name", "content", "kind"};
                int[] to = {R.id.list_name, R.id.list_content, R.id.kind};
                adapter = new SimpleCursorAdapter(LoveActivity.this, R.layout.list,
                        c, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                listView.setAdapter(adapter);
                return true;
            }
        });
    }
}