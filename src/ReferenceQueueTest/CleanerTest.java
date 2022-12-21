package ReferenceQueueTest;

import java.lang.ref.Cleaner;

public class CleanerTest implements AutoCloseable {
    private final long[] anArray = new long[20480];
    private final long id;
    public static Cleaner cleaner = Cleaner.create();
    private final Cleaner.Cleanable cleanable;

    public CleanerTest(long id) {
        this.id = id;

        this.cleanable = cleaner.register(this, new ObjectToClear(id));
    }

    private static class ObjectToClear implements Runnable {
        private final long id;

        public ObjectToClear(long id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Cleaning up ObjectToClear: id = " + this.id);
        }
    }

    @Override
    public void close() {
        cleanable.clean();
    }

    @Override
    public String toString() {
        return "CleanerTest: id = " + id;
    }

    public static void main(String[] args) throws InterruptedException {
        try (CleanerTest cbo1 = new CleanerTest(1994)) {
            System.out.println(cbo1 + " created inside a try-with-resoures block");
        }

//        CleanerTest cbo2 = new CleanerTest(2004);
//        System.out.println("cbo2  created");
//        cbo2.close();
//        cbo2 = null;
//
//        new CleanerTest(2014);
//        System.gc();
//
//        Thread.sleep(20000);
    }
}
