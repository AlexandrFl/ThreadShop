import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Automaker {
    private final CarDealership carDealership;
    public final int SELL_SLEEP = 500;
    public final int DISTILLATION_SLEEP = 2000;
    private final ReentrantLock LOCK = new ReentrantLock(true);
    private final Condition condition = LOCK.newCondition();

    public Automaker(CarDealership carDealership) {
        this.carDealership = carDealership;
    }

    public Audi sellCar() {
        try {
            LOCK.lock();
            while (carDealership.getAudi().size() == 0) {
                System.out.println("❌ Авто нет в наличии. Покупатель " + Thread.currentThread().getName() + " ушел без авто ❌");
                condition.await();
            }
            System.out.println("Старт продаж");
            Thread.sleep(SELL_SLEEP);
            System.out.println("✔ Авто продано покупателю " + Thread.currentThread().getName() + " ✔");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
        return carDealership.getAudi().
                remove(0);
    }

    public void distillation() {
        try {
            LOCK.lock();
            System.out.println("Автосалон принимает авто");
            Thread.sleep(DISTILLATION_SLEEP);
            carDealership.getAudi().add(new Audi());
            System.out.println("Машина в автосалоне. Общее количество авто в салоне - " + carDealership.audiList.size());
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
