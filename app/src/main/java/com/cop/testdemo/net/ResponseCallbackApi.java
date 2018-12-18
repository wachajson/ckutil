package com.cop.testdemo.net;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class ResponseCallbackApi<T>  implements Observer<T>{

    public abstract void onSuccess(T value);

    public abstract void onFailture(String e);

    private Disposable mDisposable;

    @Override
    public void onNext(T value) {
        onSuccess(value);
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onError(Throwable e) {
        onFailture(e.getMessage());
        mDisposable.dispose();
    }

    @Override
    public void onComplete() {
        mDisposable.dispose();
    }
}
