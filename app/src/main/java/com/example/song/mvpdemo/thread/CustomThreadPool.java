package com.example.song.mvpdemo.thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Created by song on 2018/3/13.
 * email：bjay20080613@qq.com
 */

public class CustomThreadPool {
    int NUMBER_OF_CORES=Runtime.getRuntime().availableProcessors();
    int KEEP_ALIVE_TIME=1;
    TimeUnit KEEP_ALIVE_TIME_UNIT=TimeUnit.SECONDS;
   private  BlockingDeque<Runnable> taskQueue=new LinkedBlockingDeque<Runnable>();
   private  ExecutorService executorService=new ThreadPoolExecutor(NUMBER_OF_CORES,NUMBER_OF_CORES*2,KEEP_ALIVE_TIME,KEEP_ALIVE_TIME_UNIT,taskQueue);

    public void execute(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

}
