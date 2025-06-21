package thread.excutor.future;

import java.util.Random;
import java.util.concurrent.*;

import static thread.util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class CallableMainV1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new MyCallable());
        Integer result = future.get();
        log("result: " + result);
        es.close();
    }

    static class MyCallable implements Callable<Integer> {
        int value;

        @Override
        public Integer call() throws Exception {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("create value: " + value);
            log("Callable 완료");
            return value;
        }
    }
}
