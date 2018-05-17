package com.pixelleafs.kore;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.pixelleafs.kore.eventbus.EventGhost;
import com.pixelleafs.kore.lifecycle.LifecycleObserverDisposables;
import com.pixelleafs.kore.lifecycle.LifecycleObserverEventBus;

import org.greenrobot.eventbus.Subscribe;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Julian Cardona on 9/5/17.
 */

public abstract class KoreFragment extends Fragment{

    private final LifecycleObserverDisposables lifecycleObserverDisposables = new LifecycleObserverDisposables();
    private final LifecycleObserverEventBus lifecycleObserverEventBus = new LifecycleObserverEventBus(this);

    public void addDisposable(Disposable disposable){
        lifecycleObserverDisposables.addDisposable(disposable);
    }

    public void addDisposableForever(Disposable disposable){
        lifecycleObserverDisposables.addDisposableForever(disposable);
    }

    public CompositeDisposable getDisposables() {
        return lifecycleObserverDisposables.getDisposables();
    }

    public CompositeDisposable getDisposablesForever() {
        return lifecycleObserverDisposables.getDisposablesForever();
    }

    abstract protected void initVars();

    @CallSuper
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        initLifeCycleObservers();
        initVars();
    }

    private void initLifeCycleObservers(){
        getLifecycle().addObserver(lifecycleObserverDisposables);
        getLifecycle().addObserver(lifecycleObserverEventBus);
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    abstract protected void initViews();

    @Subscribe
    public void onEventGhost(EventGhost event) {}

}
