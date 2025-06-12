package thread.problem.section2;

import static util.MyLogger.log;

/**
 * 방금 작성한 문제2를 익명 클래스로 구현해라.
 */
public class Problem3 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    log("value: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread thread = new Thread(runnable, "counter");
        thread.start();
    }
}
