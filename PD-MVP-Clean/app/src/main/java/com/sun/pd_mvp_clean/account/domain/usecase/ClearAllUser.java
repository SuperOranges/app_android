package com.sun.pd_mvp_clean.account.domain.usecase;

import android.support.annotation.NonNull;

import com.sun.pd_mvp_clean.UseCase;
import com.sun.pd_mvp_clean.data.source.AccountRepository;
import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Administrator on 2017/11/9.
 */

public class ClearAllUser extends UseCase<ClearAllUser.RequestValues,ClearAllUser.ResponseValue> {

    private final AccountRepository mAccountRepository;

    public ClearAllUser (@NonNull AccountRepository accountRepository){
        mAccountRepository = checkNotNull(accountRepository,"AccountRepository can not be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mAccountRepository.deleteUser();
        getUseCaseCallback().onSuccess(new ResponseValue());
    }

    public static class RequestValues implements UseCase.RequestValues { }

    public static class ResponseValue implements UseCase.ResponseValue { }
}
