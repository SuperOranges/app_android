package com.sun.pd_mvp_clean;

import android.os.Handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by 孙毅 on 2017/10/16.
 *使用 ThreadPoolExecutor 执行异步任务；
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * <p>
 *
 * 另请参阅为不同场景创建通用的工厂方法清单
 * See also {@link Executors} for a list of factory methods to create common
 * {@link java.util.concurrent.ExecutorService}s for different scenarios.
 */

public class UseCaseThreadPoolScheduler implements UseCaseScheduler {
    private final Handler mHandler = new Handler();

    //线程池大小
    public static final int POOL_SIZE = 2;

    //最大线程池
    public static  final int MAX_POOL_SIZE = 4;

    //最大超时时间
    public static final int TIMEOUT = 30;

    ThreadPoolExecutor mThreadPoolExecutor;

    public UseCaseThreadPoolScheduler(){
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE,MAX_POOL_SIZE,TIMEOUT,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(POOL_SIZE));
        // ArrayBlockingQueue 是一个由数组支持的有界阻塞队列。此队列按 FIFO（先进先出）原则对元素进行排序。
    }

    @Override
    public void execute(Runnable runnable){
        mThreadPoolExecutor.execute(runnable);
    }

    @Override
    public  <V extends UseCase.ResponseValue> void notifyResponse(
            final V response, final UseCase.UseCaseCallback<V> useCaseCallback){

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    useCaseCallback.onSuccess(response);
                }
            });
    }

    @Override
    public  <V extends UseCase.ResponseValue> void onError(
            final UseCase.UseCaseCallback<V> useCaseCallback){

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    useCaseCallback.onError();
                }
            });
    }
}
