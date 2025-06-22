package thread.excutor.future;

import java.util.concurrent.*;

import static thread.util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class FutureCancelMain {
    private static boolean mayInterruptIfRunning = false;
//    private static boolean mayInterruptIfRunning = true;

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("Future.state = " + future.state());

        sleep(3000);

        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("cancel(" + mayInterruptIfRunning + ") result = " + cancelResult);

        try {
            log("Future result = " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } catch (CancellationException e) {
            log("Future는 이미 취소 되었습니다.");
        }

    }

    static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업 중: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생");
                return "Interrupted";
            }

            return "Completed";
        }
    }
}
