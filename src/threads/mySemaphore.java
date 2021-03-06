package threads;


public class mySemaphore {

    private boolean free;
    private String acquiredBy;

    public mySemaphore() {
        free = true;
        acquiredBy = SequentalCleaner.class.getName();
    }

    public void acquire(Runnable thread)  {
        synchronized (this) {
            if (!free) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (acquiredBy.equals(thread.getClass().getName())) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
