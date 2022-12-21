package AccessingAndClearing;

import Finalizer.Finalizer;

import java.lang.ref.SoftReference;

public class BigObject {
    private final long[] anArray = new long[20480];
    private final long id;

    public BigObject(long id) {
        this.id = id;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize() called for id: " + id);
    }

    @Override
    public String toString() {
        return "BigObject: id = " + id;
    }

    public static void main(String[] args) {
        BigObject bigObject = new BigObject(101); // Strongly reachable
        SoftReference<BigObject> sr = new SoftReference<>(bigObject); // Strongly reachable
        bigObject = null; // Softly reachable
        BigObject referent = sr.get(); // Strongly reachable

        System.gc();
        System.runFinalization();

        SoftReference<BigObject> sr1 = new SoftReference<>(new BigObject(976)); // Soflty reachable
        sr1.clear(); // Unreachable (finalizer-reachable)

        System.out.println(sr1.get());

        System.gc();
        System.runFinalization();

        for (int i = 1; i <= 5000000; i++) {
//            System.out.println(i);
        }
    }
}
