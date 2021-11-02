package rs.ac.bg.etf.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class LifeCycleAwareLogger implements DefaultLifecycleObserver {

    private final String logTag;
    private final String lifecycleOwner;

    public LifeCycleAwareLogger(String logTag, String lifecycleOwner) {
        this.logTag = logTag;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.d(logTag, "called" + lifecycleOwner +"myOnCreate()");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.d(logTag, "called" + lifecycleOwner +"onStart()");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Log.d(logTag, "called" + lifecycleOwner +"onResume()");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Log.d(logTag, "called" + lifecycleOwner +"onPause()");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.d(logTag, "called" + lifecycleOwner +"onStop()");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.d(logTag, "called" + lifecycleOwner + "onDestroy()");
    }
}
