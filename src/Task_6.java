import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Task_6 {


    public static void main(String[] args) {
        TreasureChest chest = new TreasureChest();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                chest.add(50);
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("Общее количество золота в сундуке: " + chest.getTotal());
    }
}

class TreasureChest {
    private final AtomicInteger goldCount = new AtomicInteger(0);

    public void add(int amount) {
        goldCount.addAndGet(amount);
    }

    public int getTotal() {
        return goldCount.get();
    }
}