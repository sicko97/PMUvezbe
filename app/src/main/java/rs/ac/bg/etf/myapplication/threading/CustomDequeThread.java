package rs.ac.bg.etf.myapplication.threading;

import java.util.concurrent.LinkedBlockingDeque;

public class CustomDequeThread extends Thread{

    private LinkedBlockingDeque<Runnable> runnableDeque = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while(true){
            try {
                Runnable runnable = runnableDeque.takeFirst();
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public LinkedBlockingDeque<Runnable> getRunnableDeque() {
        return runnableDeque;
    }
}
