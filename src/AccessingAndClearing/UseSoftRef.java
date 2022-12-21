package AccessingAndClearing;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class UseSoftRef {
    public static void main(String[] args) {
        BigObject bigObject = new BigObject(101);
        SoftReference<BigObject> sr = new SoftReference<>(bigObject);

        bigObject = null;
        System.gc();
        System.out.println(sr.get());

        ArrayList<BigObject> arrayBigObj = new ArrayList<>();
        long counter = 102;
        while (true) {
            arrayBigObj.add(new BigObject(counter++));
        }
    }
}
