package com.example.gamepro;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.VelocityTracker;

public class Robot {
    float centerX,centerY;
    int height, width;
    float velocityX,velocityY;

    Bitmap robotBitmap;
    Paint robotPaint;

    boolean robotFelldown=false;


    public Robot(Bitmap bitmap){
        robotBitmap= bitmap;
        centerX = centerY =0;
        height=robotBitmap.getHeight();
        width=robotBitmap.getWidth();
        robotPaint=new Paint();
        velocityX=velocityY=0;

    }

    public Robot(Bitmap bitmap, int cX,int cY){
        this(bitmap);
        centerX=cX;
        centerY=cY;

    }

    public Robot(Bitmap bitmap, Point center){
        this(bitmap,center.x,center.y);

    }

    public void setCenter(Point centerpoint){
        centerX=centerpoint.x;
        centerY=centerpoint.y;


    }

    public void setVelocity(VelocityTracker velocityTracker){
        velocityX = velocityTracker.getXVelocity();
        velocityY = velocityTracker.getYVelocity();

    }


}
