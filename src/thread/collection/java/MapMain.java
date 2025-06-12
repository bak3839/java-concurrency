package thread.collection.java;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;

public class MapMain {
    public static void main(String[] args) {
        Map<Integer, String> map1 = new ConcurrentHashMap<>();
        map1.put(1, "data1");
        map1.put(2, "data2");
        map1.put(3, "data3");
        System.out.println("map1 = " + map1);

        // 키 값이 없으면 함수 수행
        map1.computeIfAbsent(4, K -> "data4");

        // 키가 존재하면 해당 값 반환 존재하지 않으면 값을 put -> null 반환
        map1.putIfAbsent(5, "data5");

        Map<Integer, String> map2 = new ConcurrentSkipListMap<>();
        map2.put(2, "data2");
        map2.put(3, "data3");
        map2.put(1, "data1");
        System.out.println("map2 = " + map2);

        ConcurrentHashMap<Integer, ReentrantLock> lockMap = new ConcurrentHashMap<>();

        ReentrantLock lock = lockMap.computeIfAbsent(1, K -> new ReentrantLock());
        
        lock.lock();
        try {
            map1.put(4, "data4");
        } finally {
            lock.unlock();
        }
    }
}
