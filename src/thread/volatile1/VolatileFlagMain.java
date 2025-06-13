package thread.volatile1;

import static thread.util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag false 변경");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task start");
            while(runFlag) {

            }
            log("task end");
        }
    }
}
