package com.pixelatedleaft.library.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Julian Cardona on 9/5/17.
 */

public class LifecycleObserverDisposables implements LifecycleObserver{

    private CompositeDisposable disposables;
    private CompositeDisposable disposablesForever;

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        clearDisposables();
        clearDisposablesForever();
    }

    public CompositeDisposable getDisposables() {
        return disposables;
    }

    public CompositeDisposable getDisposablesForever() {
        return disposablesForever;
    }

    public void addDisposable(@NonNull Disposable disposable) {
        if(isCompositeDisposableEmpty()){
            this.disposables = new CompositeDisposable();
        }
        this.disposables.add(disposable);
    }

    public void addDisposableForever(@NonNull Disposable disposable) {
        if(isCompositeDisposableForeverEmpty()){
            disposablesForever = new CompositeDisposable();
        }
        this.disposablesForever.add(disposable);
    }

    public void clearDisposables(){
        if(!isCompositeDisposableEmpty()) {
            this.disposables.clear();
        }
    }

    public void clearDisposablesForever(){
        if(!isCompositeDisposableForeverEmpty()) {
            this.disposablesForever.clear();
        }
    }

    private boolean isCompositeDisposableEmpty() {
        return disposables == null;
    }

    private boolean isCompositeDisposableForeverEmpty() {
        return disposablesForever == null;
    }

}
