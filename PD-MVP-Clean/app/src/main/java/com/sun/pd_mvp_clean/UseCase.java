package com.sun.pd_mvp_clean;

/**
 * Created by 孙毅 on 2017/10/16.
 * 泛型
 * Use cases are the entry points to the domain layer.
 * @param <Q> the request type
 * @param <P> the response type
 */

public abstract class UseCase <Q extends UseCase.RequestValues,P extends UseCase.ResponseValue>{
        private Q mRequestValues;
        private UseCaseCallback<P> mUseCaseCallback;

        //设置请求参数对象
        public  void setRequestValues(Q requestValues){
                mRequestValues = requestValues;
        }

        //获取请求参数对象
        public  Q getRequestValues(){
             return mRequestValues;
        }

        //获取UseCaseCallback（回调）对象
        public  UseCaseCallback<P> getUseCaseCallback(){
            return mUseCaseCallback;
        }

        //设置UseCaseCallback（回调）对象
        public  void setUseCaseCallback(UseCaseCallback<P> useCaseCallback){
            mUseCaseCallback = useCaseCallback;
        }

        //UseCase的运行执行方法
        void run(){
            executeUseCase(mRequestValues);
        }

        protected  abstract void executeUseCase(Q requestValues);

        /**
         *   Data passed to a request.数据传递给请求
         */

        public interface RequestValues{

        }

        /**
         *   Data received from a request.从请求中接收数据？？？不是响应吗？
         */

        public interface ResponseValue{

        }

        public interface UseCaseCallback<R>{
                void onSuccess(R response);
                void onError();
    }
}
