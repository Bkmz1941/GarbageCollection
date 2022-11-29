package Resurrect;

import java.sql.ResultSet;

public class Resurrect {
    private static Resurrect res = null;
    private String name = "";

    public Resurrect(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        for (int count = 1; count <= 1000; count++) {
            new Resurrect("Object #" + count);
            if (count % 100 == 0) {
                System.gc();
                System.runFinalization();
            }
        }
    }

    public void sayHello() {
        System.out.println("Hello from " + name);
    }

    public static void resurrectIt(Resurrect r) {
        res = r;
        res.sayHello();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void finalize() {
        System.out.println("Inside finalize(): " + name);
        Resurrect.resurrectIt(this);
    }
}
