package com.example.gamepro;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    Context context;
    SurfaceHolder surfaceHolder;
    DrawingThread drawingThread;
    VelocityTracker velocityTracker;


    public GameView(Context context) {
        super(context);
        this.context = context;

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        drawingThread = new DrawingThread(this,context);
        velocityTracker = VelocityTracker.obtain();
    }



    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;


        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        drawingThread = new DrawingThread(this,context);
        velocityTracker = VelocityTracker.obtain();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;


        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        drawingThread = new DrawingThread(this,context);
        velocityTracker = VelocityTracker.obtain();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {


        try {
            drawingThread.start();

        }catch (Exception e){
            restartThread();
        }

    }

    private void restartThread() {
        drawingThread.stopThread();
        drawingThread = null;
        drawingThread= new DrawingThread(this,context);
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        drawingThread.stopThread();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (drawingThread.pauseFlag == true) {
            return true;

        }
        Point touchPoint = new Point();
        touchPoint=new Point((int) event.getX(),(int) event.getY());
        Random random =new Random();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawingThread.touchFlag=true;
                drawingThread.allRobots.add(new Robot(drawingThread.allPosibleRobots.get(random.nextInt(5)),touchPoint));


                break;

            case MotionEvent.ACTION_UP:
                velocityTracker.computeCurrentVelocity(40);
                drawingThread.allRobots.get(drawingThread.allRobots.size()-1).setVelocity(velocityTracker);

                drawingThread.touchFlag=false;

                break;

            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                drawingThread.allRobots.get(drawingThread.allRobots.size()-1).setCenter(touchPoint);

                break;

            default:
                break;
        }
        return true;
    }
}
