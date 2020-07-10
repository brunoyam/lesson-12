import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {

    private Semaphore semaphore;
    private int num = 0;
    private int id;

    public Philosopher(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run() {
         try {
             while (num < 3) {
                 semaphore.acquire();

                 System.out.println("Философ " + id + " сел за стол и обедает");
                 Thread.sleep(1000);
                 num++;
                 System.out.println("Философ " + id + " поел");
                 Thread.sleep(1000);

                 semaphore.release();
             }
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
    }
}
