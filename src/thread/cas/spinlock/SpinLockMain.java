package thread.cas.spinlock;

import static thread.util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class SpinLockMain {

    public static void main(String[] args) throws InterruptedException {
//        SpinLockBad spinLock = new SpinLockBad();
        SpinLock spinLock = new SpinLock();

        Runnable task = () -> {
            spinLock.lock();
            try {
                // critical section
                log("비즈니스 로직 실행");
                sleep(1);
            } finally {
                spinLock.unlock();
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
