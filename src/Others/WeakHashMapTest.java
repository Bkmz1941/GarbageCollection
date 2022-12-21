package Others;

import AccessingAndClearing.BigObject;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap<BigObject, String> map = new WeakHashMap<>();
        BigObject bigObject = new BigObject(10);
        String str = new String("test");
        map.put(bigObject, str);
        System.out.println(1 + " / " + map);
        bigObject = null;

        ReferenceQueue<BigObject> q = new ReferenceQueue<>();
        BigObject bigObject2 = new BigObject(131);
        WeakReference<BigObject> wr = new WeakReference(bigObject2, q);
        bigObject2 = null;

        for (int i = 0; i < 1000000000; i++) {
            new Object();
        }

        System.gc();

        System.out.println(1 + " / " + map);
        WeakReference<BigObject> temp = (WeakReference<BigObject>) q.poll();
        System.out.println(temp.get());
    }
}
