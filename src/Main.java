
public class Main {
    private static final int BUYER_COUNT = 10;
    private static final int BUYER_SLEEP = 1000;
    private static final int AUTOMAKER_SLEEP = 2000;

    public static void main(String[] args) throws InterruptedException {
        final CarDealership carDealership = new CarDealership();

        for (int i = 0; i < BUYER_COUNT; i++) {
            new Thread(null, carDealership::sellAudi, "Номер " + (i + 1)).start();
            Thread.sleep(BUYER_SLEEP);
        }

        for (int i = 0; i < BUYER_COUNT; i++) {
            new Thread(null, carDealership::distillation).start();
            Thread.sleep(AUTOMAKER_SLEEP);
        }
    }
}
