package buildings.threads;


public class mySemaphore {


    private boolean free;
    private String acquiredBy;

    public mySemaphore() {
        free = true;
        acquiredBy = SequentalCleaner.class.getName();
    }

    public void acquire(Runnable thread) throws InterruptedException {
        synchronized (this) {
            if (!free) {
                this.wait();
            }
            if (acquiredBy.equals(thread.getClass().getName())) {
                this.wait();
            }
            free = false;
        }
    }

    public void release(Runnable thread) {
        synchronized (this) {
            free = true;
            acquiredBy = thread.getClass().getName();
            this.notifyAll();
        }
    }

}
