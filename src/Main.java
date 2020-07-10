import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        /*Store ourStore = new Store();
        new Thread(new Consumer(ourStore)).start();
        new Thread(new Producer(ourStore)).start();
         */

        Semaphore sem = new Semaphore(2);
        for (int i = 0; i < 10; ++i) {
            new Thread(new Philosopher(sem, i)).start();
        }
    }
}
