package thread.excutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.excutor.ExecutorUtils.*;
import static util.MyLogger.log;

public class ExecutorShutdownMain {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new RunnableTask("taskA"));
        executor.execute(new RunnableTask("taskB"));
        executor.execute(new RunnableTask("taskC"));
        executor.execute(new RunnableTask("longTask", 100_000)); // 100s
        printState(executor);

        log("== shutdown 시작");
        shutdownAndAwaitTermination(executor);
        log("== shutdown 완료");
        printState(executor);
    }

    private static void shutdownAndAwaitTermination(ExecutorService executor) {
        executor.shutdown(); // non-blocking, 새로운 작업을 받지 않는다. 처리 중이거나 큐에 대기중인 작업은 처리

        try {
            // 이미 대기중인 작업들을 모두 완료할 때까지 10초 대기
            if(!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                // 정상 종료가 너무 오래 걸리면
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                executor.shutdownNow();
                // 작업이 취소될 때까지 대기
                if(!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination() -> 대기중인 현재 스레드가 인터럽트 될 수 있다.
            executor.shutdownNow();
        }
    }
}
