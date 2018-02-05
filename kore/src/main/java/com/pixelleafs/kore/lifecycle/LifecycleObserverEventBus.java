package com.pixelleafs.kore.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Julian Cardona on 9/5/17.
 */

public class LifecycleObserverEventBus implements LifecycleObserver {

    private Object subscriber;

    public LifecycleObserverEventBus(Object subscriber){
        this.subscriber = subscriber;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        EventBus.getDefault().register(subscriber);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        EventBus.getDefault().unregister(subscriber);
    }

}