package com.pixelleafs.kore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;

import com.pixelleafs.kore.eventbus.EventGhost;
import com.pixelleafs.kore.lifecycle.LifecycleObserverDisposables;
import com.pixelleafs.kore.lifecycle.LifecycleObserverEventBus;

import org.greenrobot.eventbus.Subscribe;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Julian Cardona on 9/5/17.
 */

public abstract class KoreBottomSheetFragment extends BottomSheetDialogFragment{

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
        initLifeCycleObservers();
    }

    private void initLifeCycleObservers(){
        getLifecycle().addObserver(lifecycleObserverDisposables);
        getLifecycle().addObserver(lifecycleObserverEventBus);
    }

    @Subscribe
    public void onEventGhost(EventGhost event) {}
}
