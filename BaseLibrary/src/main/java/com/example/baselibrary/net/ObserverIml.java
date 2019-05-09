package com.example.baselibrary.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者：$yangxiangrong
 * <p>
 * 2019/5/9 19:53
 */
public abstract class ObserverIml<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onNext(T t) ;
    @Override
    public abstract void onError(Throwable e) ;

    @Override
    public void onComplete() {

    }
}
