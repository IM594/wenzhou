package com.example.wenzhou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

public class pnjdActivity extends AppCompatActivity {
    MapView mMapView;
    Double la,lg;
    ImageView like;
    private MyDBOpenHelper dbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_pnjd);
        mMapView = (MapView) findViewById(R.id.map);
        like=findViewById(R.id.like);
        dbOpenHelper = new MyDBOpenHelper(getApplicationContext(), "S_Database.db", null, 1);
        BaiduMap mBaidumap = mMapView.getMap();
        lg=121.09546;
        la=27.459869;
        LatLng latLng = new LatLng(la,lg);//地图坐标
        MapStatus mapStatus = new MapStatus.Builder()
                .target(latLng)
                .zoom(15) //地图放大倍数
                .build();
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaidumap.setMapStatus(msu);
        //在目标位置显示标记
        BitmapDescriptor searchBdA = BitmapDescriptorFactory.fromResource(R.drawable.start);
        MarkerOptions options = new MarkerOptions().icon(searchBdA).position(latLng);
        mBaidumap.addOverlay(options);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbWriter=dbOpenHelper.getWritableDatabase();
                //getWritableDatabase方法可以获得具有写入权限的SQLiteDatabase对象
                ContentValues cv=new ContentValues();//一次性缓存
                cv.put("name","南麂岛");
                cv.put("content","号称贝藻王国、蓝色牧场");
                cv.put("kind","山水");
                if(dbWriter.insert("love",null,cv)<0){
                    //table为想要插入数据的表名字 nullColunmnHack为是否允许有空行 values为要插入的值 第一条为0
                    Toast.makeText(pnjdActivity.this, "收藏失败", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(pnjdActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }  @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }   @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
