package AccessingAndClearing;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class BigObjectCache {
    @SuppressWarnings("unchecked")
    private static final SoftReference<BigObject>[] cache = new SoftReference[10];

    public static BigObject getObjectById(int id) {
        if (id < 0 || id >= cache.length) {
            throw new IllegalArgumentException("Invalid id");
        }

        BigObject obj;

        if (cache[id] == null) {
            obj = createCacheForId(id);
            return obj;
        }

        obj = cache[id].get();

        if (obj == null) {
            obj = createCacheForId(id);
        }

        return obj;
    }

    private static BigObject createCacheForId(int id) {
        BigObject obj = null;

        if (id >= 0 && id < cache.length) {
            obj = new BigObject(id);
            cache[id] = new SoftReference<>(obj);
        }

        return obj;
    }

    public static void main(String[] args) throws InterruptedException {
        BigObject bigObject = BigObjectCache.getObjectById(5);
        bigObject = null;

        Runtime.getRuntime().gc();

        System.out.println(BigObjectCache.getObjectById(5));
    }
}
