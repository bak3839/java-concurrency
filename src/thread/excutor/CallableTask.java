package thread.excutor;

import java.util.concurrent.Callable;

import static thread.util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class CallableTask implements Callable<Integer> {
    private String name;
    private int sleepMs;

    public CallableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public Integer call() throws Exception {
        log(name + "실행");
        sleep(sleepMs);
        log(name + "완료");
        return sleepMs;
    }
}
