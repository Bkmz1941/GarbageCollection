package Finalizer;

public class Finalizer {
    private final int id;

    public Finalizer(int id) {
        this.id = id;
    }

    @SuppressWarnings("deprecated")
    @Override
    protected void finalize() throws Throwable {
        if (id % 100 == 0) {
            System.out.println("finalize() called for " + id);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 500000; i++) {
            new Finalizer(i);
        }
        System.gc();
    }
}
