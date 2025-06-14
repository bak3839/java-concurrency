package thread.bounded;

public interface BoundedQueue {
    void put(String data); // 생산자 스레드가 데이터를 저장
    String take(); // 소비자 스레드가 데이터를 컨슘
}
