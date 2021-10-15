package rs.ac.bg.etf.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class MyLifecycleAwareComponent implements DefaultLifecycleObserver {

    private static final String LOG_TAG = "activity-lifecycle";

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.d(LOG_TAG, "myOnCreate() called");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.d(LOG_TAG, "onStart() called");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Log.d(LOG_TAG, "onResume() called");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Log.d(LOG_TAG, "onPause() called");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.d(LOG_TAG, "onStop() called");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.d(LOG_TAG, "onDestroy() called");
    }
}
