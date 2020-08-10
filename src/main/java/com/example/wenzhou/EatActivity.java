package com.example.wenzhou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class EatActivity extends AppCompatActivity {
    private int[] resId = new int[]{
            R.drawable.zhuzangfen,R.drawable.yubing,R.drawable.nuomifan, R.drawable.yashe};
    //图片下标序号
    public int count = 0;
    //定义手势监听对象
    private GestureDetector gestureDetector;
    //定义ImageView对象
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);
        iv = (ImageView)findViewById(R.id.imageView);//获取ImageView控件id
        gestureDetector = new GestureDetector(onGestureListener);   //设置手势监听由onGestureListener处理
    }

    //当Activity被触摸时回调

    public boolean onTouchEvent(MotionEvent event){

        return gestureDetector.onTouchEvent(event);

    }

    //自定义GestureDetector的手势识别监听器
    private GestureDetector.OnGestureListener onGestureListener
            = new GestureDetector.SimpleOnGestureListener(){
        //当识别的手势是滑动手势时回调onFinger方法
        public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
            //得到手触碰位置的起始点和结束点坐标 并进行计算
            float x = e2.getX()-e1.getX();

            //通过计算判断是向左还是向右滑动
            if(x > 0){
                count++;
                count%=(resId.length);
            }else if(x < 0){
                count--;
                count=(count+(resId.length))%(resId.length);
            }
            iv.setImageResource(resId[count]);  //切换imageView的图片

            return false;
        }
    };
}
