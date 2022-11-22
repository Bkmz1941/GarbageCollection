package InvokeGC;

public class InvokeGC {
    public static void main(String[] args) {
        long m1, m2, m3;

        Runtime rt = Runtime.getRuntime();

        for (int i = 0; i < 4; i++) {
            m1 = rt.freeMemory();

            createObjects(2000);

            m2 = rt.freeMemory();

            System.gc();

            m3 = rt.freeMemory();

            System.out.println("m1 = " + m1);
            System.out.println("m2 = " + m2);
            System.out.println("m3 = " + m3);
            System.out.println("Freed = " + (m3 - m2));
            System.out.println("--------------------------");
        }
    }

    public static void createObjects(int count) {
        for (int i = 0; i < count; i++) {
            new Object();
        }
    }

}
