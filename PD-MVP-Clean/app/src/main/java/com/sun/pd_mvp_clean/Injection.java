package com.sun.pd_mvp_clean;

import android.content.Context;
import static com.google.common.base.Preconditions.checkNotNull;
import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.account.domain.usecase.ClearAllUser;
import com.sun.pd_mvp_clean.account.domain.usecase.FindUser;
import com.sun.pd_mvp_clean.account.domain.usecase.SaveUser;
import com.sun.pd_mvp_clean.account.domain.usecase.VerifyUser;
import com.sun.pd_mvp_clean.data.source.AccountRepository;
import com.sun.pd_mvp_clean.data.source.TasksRepository;
import com.sun.pd_mvp_clean.data.source.local.AccountLocalDataSource;
import com.sun.pd_mvp_clean.data.source.remote.AccountRemoteDataSource;
import com.sun.pd_mvp_clean.data.source.remote.FakeAccoutRemoteDataSource;
import com.sun.pd_mvp_clean.data.source.remote.TasksRemoteDataSource;

/**
 * Created by Administrator on 2017/11/8.
 * 该类负责把资源注入
 */

public class Injection {

    public static AccountRepository provideAccountRepository(@NonNull Context context){
        checkNotNull(context);
        return AccountRepository.getINSTANCE(FakeAccoutRemoteDataSource.getIntance(),
                AccountLocalDataSource.getInstance(context));
    }
    public static TasksRepository provideTasksRepository(@NonNull Context context){
        checkNotNull(context);
        return TasksRepository.getINSTANCE(TasksRemoteDataSource.getInstance());
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public  static FindUser provideFindUser(@NonNull Context context){
        return  new FindUser(Injection.provideAccountRepository(context));
    }
    public  static VerifyUser provideVerifyUser(@NonNull Context context){
        return new VerifyUser(Injection.provideAccountRepository(context));
    }

    public  static SaveUser provideSaveUser(@NonNull Context context){
        return  new SaveUser(Injection.provideAccountRepository(context));
    }
    public  static ClearAllUser provideClearAllUser(@NonNull Context context){
        return new ClearAllUser(Injection.provideAccountRepository(context));
    }
}
