import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Store {

    private int products = 0;
    private Lock locker;
    private Condition condition;

    public Store() {
        locker = new ReentrantLock(true);
        condition = locker.newCondition();
    }

    public void get() {
        locker.lock();
        try {
            while (products < 1) {
                condition.await();
            }

            System.out.println("Покупатель приобретает продукт");
            products--;
            System.out.println("Остаток в магазине: " + products);

            if (products == 0) {
                condition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void put() {
        locker.lock();
        try {
            while (products >= 5) {
                condition.await();
            }

            System.out.println("Поставщик привез продукт");
            products++;
            System.out.println("Остаток в магазине: " + products);

            if (products == 5) {
                condition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

}
