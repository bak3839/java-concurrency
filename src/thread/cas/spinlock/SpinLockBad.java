package thread.cas.spinlock;

import static thread.util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class SpinLockBad {

    private volatile boolean lock = false;

    public void lock() {
        log("락 획득 시도");
        while(true) {
            if(!lock) { // 1. 락 사용 여부 확인
                sleep(100);
                lock = true; // 2. 락의 값 변경
                break;
            } else {
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock = false;
        log("락 반납 완료");
    }
}
