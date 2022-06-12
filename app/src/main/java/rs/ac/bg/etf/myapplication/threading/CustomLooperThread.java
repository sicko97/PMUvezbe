package rs.ac.bg.etf.myapplication.threading;

import android.os.Looper;

public class CustomLooperThread extends Thread {

    private Looper looper = null;

    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        Looper.loop();

    }

    public Looper getLooper() {
        return looper;
    }
}
