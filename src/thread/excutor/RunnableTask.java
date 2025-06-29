package thread.excutor;

import static thread.util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class RunnableTask implements Runnable {
    private String name;
    private int sleepMs = 1000;

    public RunnableTask(String name) {
        this.name = name;
    }

    public RunnableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public void run() {
        log(name + "실행");
        sleep(sleepMs);
        log(name + "완료");
    }
}
