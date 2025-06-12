package thread.problem.section2;

import static util.MyLogger.log;

/**
 * 문제 4: 여러 스레드 사용
 * - Thread-A , Thread-B 두 스레드를 만들어라
 * - Thread-A 는 1초에 한 번씩 "A"를 출력한다.
 * - Thread-B 는 0.5초에 한 번씩 "B"를 출력한다.
 * - 이 프로그램은 강제 종료할 때 까지 계속 실행된다.
 */
public class Problem4 {
    public static void main(String[] args) {
//        Thread threadA = new Thread(() -> {
//            try {
//                while(true) {
//                    log("A");
//                    Thread.sleep(1000);
//                }
//            }catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, "Thread-A");
//
//        Thread threadB = new Thread(() -> {
//            try {
//                while(true) {
//                    log("B");
//                    Thread.sleep(500);
//                }
//            }catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, "Thread-B");
//
//        threadA.start();
//        threadB.start();

        Thread threadA = new Thread(new PrintWork("A", 1000), "Thread-A");
        Thread threadB = new Thread(new PrintWork("B", 500), "Thread-B");
        threadA.start();
        threadB.start();
    }

    static class PrintWork implements Runnable {
        private String content;
        private int sleepMs;

        public PrintWork(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true) { // 무한 루프
                log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
