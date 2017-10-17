package com.sun.pd_mvp_clean;

/**
 * Created by 孙毅 on 2017/10/16.
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 * 调度者的接口
 */

public interface UseCaseScheduler {
    void execute(Runnable runable);
    /** public <T>  void ……  这个T是个修饰符的功能，表示是个泛型方法，就像有static修饰的方法是个静态方法一样。
        <T> 不是返回值，表示传入参数有泛型
     */
    <V extends UseCase.ResponseValue> void notifyResponse(final V response, final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(final UseCase.UseCaseCallback<V> useCaseCallback);
}
