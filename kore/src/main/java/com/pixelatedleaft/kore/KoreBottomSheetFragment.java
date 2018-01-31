package com.pixelatedleaft.kore;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;

import com.pixelatedleaft.kore.eventbus.GhostEvent;
import com.pixelatedleaft.kore.lifecycle.LifecycleObserverDisposables;
import com.pixelatedleaft.kore.lifecycle.LifecycleObserverEventBus;

import org.greenrobot.eventbus.Subscribe;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Julian Cardona on 9/5/17.
 */

public abstract class KoreBottomSheetFragment extends BottomSheetDialogFragment implements LifecycleRegistryOwner {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private final LifecycleObserverDisposables lifecycleObserverDisposables = new LifecycleObserverDisposables();
    private final LifecycleObserverEventBus lifecycleObserverEventBus = new LifecycleObserverEventBus(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return this.lifecycleRegistry;
    }

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
    public void onEventGhost(GhostEvent event) {}
}
