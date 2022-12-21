package ReferenceQueueTest;

import AccessingAndClearing.BigObject;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueueTest {
    public static void main(String[] args) {
        ReferenceQueue<BigObject> q = new ReferenceQueue<>();
        BigObject bigObject = new BigObject(131);
        WeakReference<BigObject> wr = new WeakReference(bigObject, q);

        bigObject = null;

        System.gc();

        WeakReference<BigObject> temp = (WeakReference<BigObject>) q.poll();
        System.out.println(temp == wr);
    }
}
