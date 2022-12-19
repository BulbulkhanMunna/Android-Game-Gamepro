package com.example.gamepro;

public class ScoreCounterThread extends Thread{

    DrawingThread drawingThread;
    boolean threadRunningFlag = false;

    public ScoreCounterThread(DrawingThread drawingThread) {
        this.drawingThread = drawingThread;
    }

    @Override
    public void run() {
        threadRunningFlag = true;
        while (threadRunningFlag){
             float temporaryMax = 0;

            for (Robot robot :drawingThread.allRobots) {
                if (robot.centerY<temporaryMax) {
                    temporaryMax = robot.centerY;
                }
            }

            drawingThread.maxScore = drawingThread.maxScore>(-temporaryMax)?drawingThread.maxScore: (int) (-temporaryMax);

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void  stopThread(){
        threadRunningFlag=false;
    }
}
