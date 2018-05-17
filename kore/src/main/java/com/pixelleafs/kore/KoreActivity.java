package com.pixelleafs.kore;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.pixelleafs.kore.eventbus.EventAlertDialog;
import com.pixelleafs.kore.eventbus.EventProgressDialog;
import com.pixelleafs.kore.eventbus.EventSnackbar;
import com.pixelleafs.kore.lifecycle.LifecycleObserverDisposables;
import com.pixelleafs.kore.lifecycle.LifecycleObserverEventBus;
import com.pixelleafs.kore.navigation.KoreActivityNavigationController;
import com.pixelleafs.kore.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Julian Cardona on 9/5/17.
 */

public abstract class KoreActivity  extends AppCompatActivity{

    private final LifecycleObserverDisposables lifecycleObserverDisposables = new LifecycleObserverDisposables();
    private final LifecycleObserverEventBus lifecycleObserverEventBus = new LifecycleObserverEventBus(this);

    private Object navigationController;
    private ProgressDialog progress;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLifeCycleObservers();
        initVars();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();
    }

    abstract protected void initViews();

    private void initLifeCycleObservers(){
        getLifecycle().addObserver(lifecycleObserverDisposables);
        getLifecycle().addObserver(lifecycleObserverEventBus);
    }

    @CallSuper
    @Subscribe
    public void onProgressDialogEvent(EventProgressDialog event) {
        clearKeyboardFromScreen();
        buildAndShowProgressDialogFromEvent(event);
    }

    private void buildAndShowProgressDialogFromEvent(EventProgressDialog event){
        getProgressDialog().dismiss();
        if(event.isDismiss()){
            return;
        }
        getProgressDialog().setCancelable(event.isCancelable());
        getProgressDialog().setMessage(event.getMessage());
        getProgressDialog().show();
    }

    private ProgressDialog getProgressDialog() {
        if(progress == null){
            progress = new ProgressDialog(this);
        }
        return progress;
    }

    @CallSuper
    @Subscribe
    public void onAlertDialogEvent(EventAlertDialog alert) {
        clearKeyboardFromScreen();
        buildAndShowAlertDialogFromEvent(alert);
    }

    private void buildAndShowAlertDialogFromEvent(EventAlertDialog alert){
        String title = alert.getTitle();
        String message = alert.getMessage();
        String positive = alert.getPositiveButtonText();
        if(StringUtils.isEmpty(positive)){
            positive = getString(android.R.string.ok);
        }

        String negative = alert.getNegativeButtonText();
        if(StringUtils.isEmpty(negative)){
            negative = getString(android.R.string.cancel);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(alert.isCancellable())
                .setPositiveButton(positive, alert.getPositiveListener() == null ? new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                } : alert.getPositiveListener());
        if (alert.getNegativeListener() != null) {
            builder.setNegativeButton(negative, alert.getNegativeListener() == null ? new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            } : alert.getNegativeListener());
        }
        builder.show();
    }

    @CallSuper
    @Subscribe
    public void onSnackbarMessageEvent(EventSnackbar event) {
        clearKeyboardFromScreen();
        buildAndShowSnackbarFromEvent(event);
    }

    private void buildAndShowSnackbarFromEvent(EventSnackbar event){
        Snackbar snackbar = Snackbar.make(
                getSnackbarRootView(),
                event.getMessage(),
                Snackbar.LENGTH_LONG
        );
        if(event.getActionListener() != null){
            snackbar.setAction(event.getActionText(), event.getActionListener());
        }
        if(event.getCallback() != null){
            snackbar.addCallback(event.getCallback());
        }
        snackbar.show();

    }

    protected View getSnackbarRootView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }

    @CallSuper
    @Override
    public void onBackPressed() {
        clearKeyboardFromScreen();
        super.onBackPressed();
    }

    public void clearKeyboardFromScreen() {
        if (getWindow().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected <N extends KoreActivityNavigationController> N getNavigationController(){
        if(navigationController == null){
            navigationController = new KoreActivityNavigationController(getSupportFragmentManager());
        }
        return (N) navigationController;
    }

    protected <N extends KoreActivityNavigationController> void setNavigationController(N navigationController) {
        this.navigationController = navigationController;
    }

}
