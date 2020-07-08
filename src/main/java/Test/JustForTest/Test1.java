package Test.JustForTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    @Test
    public void testHashMap() {
        int n = 997;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n + 1);
    }

    @Test
    public void testHashMap1() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(1, 2);
        System.out.println(map.get(1));

        double a = 3.14;
        System.out.println(a / 2);
    }

    @Test
    public void testTime() {
        final long firstInTimeMillis = System.currentTimeMillis();
        final long setHour = 2;
        int MINUTES = 1;
        int HOUES = 1;
        long finishTimeMillis = firstInTimeMillis + setHour * HOUES * 60 * 60 * 1000;

        System.out.println(finishTimeMillis);
    }
}