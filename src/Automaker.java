public class Automaker {
    private CarDealership carDealership;
    public final int SELL_SLEEP = 1000;
    public final int DISTILLATION_SLEEP = 2000;

    public Automaker(CarDealership carDealership) {
        this.carDealership = carDealership;
    }

    public synchronized Audi sellCar() {
        try {
            while (carDealership.getAudi().size() == 0) {
                System.out.println("❌ Авто нет в наличии. Покупатель " + Thread.currentThread().getName() + " ушел без авто ❌");
                wait();
            }
            System.out.println("Старт продаж");
            Thread.sleep(SELL_SLEEP);
            System.out.println("✔ Авто продано покупателю " + Thread.currentThread().getName() + " ✔");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return carDealership.getAudi().remove(0);
    }


    public synchronized void distillation() {
        try {
            System.out.println("Автосалон принимает авто");
            Thread.sleep(DISTILLATION_SLEEP);
            carDealership.getAudi().add(new Audi());
            System.out.println("Машина в автосалоне. Общее количество авто в салоне - " + carDealership.audiList.size());
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
