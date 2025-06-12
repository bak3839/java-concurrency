package thread.problem.section4;

import static thread.util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class Problem1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Task(), "thread-1");
        Thread thread2 = new Thread(new Task(), "thread-2");
        Thread thread3 = new Thread(new Task(), "thread-3");

        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();
    }


    static class Task implements Runnable {

        @Override
        public void run() {
            for(int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        }
    }
}
