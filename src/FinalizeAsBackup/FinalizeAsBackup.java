package FinalizeAsBackup;

/**
 * Skeleton
 */
public class FinalizeAsBackup {
    SomeResource sr;

    public void aMethod() {
        sr = new SomeResource("Obtain the resource here");

        /* Do some processing */

        /* Note the conditional freeing of resource */
        if (true) {
            this.finalize();
        }
    }

    public void finalize() {
        /* Free the resources if they have not been freed yet */
        if (true) {
            /* Free resources now */
        }
    }
}

class SomeResource {
    public SomeResource(String text) {

    }
}
