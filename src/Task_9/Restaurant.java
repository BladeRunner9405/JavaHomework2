package Task_9;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();

        List<String> dishes1 = Arrays.asList("Food 1", "Food 2");
        Order order1 = new Order(1, dishes1, 3000);

        List<String> dishes2 = Arrays.asList("Food 3", "Food 4");
        Order order2 = new Order(2, dishes2, 5000);

        List<String> dishes3 = Arrays.asList("Food 4", "Food 5");
        Order order3 = new Order(3, dishes3, 5000);

        orderQueue.addOrder(order1);
        orderQueue.addOrder(order2);
        orderQueue.addOrder(order3);

        ExecutorService chefExecutor = Executors.newFixedThreadPool(3);
        chefExecutor.submit(new Chef("Chef 1", orderQueue));
        chefExecutor.submit(new Chef("Chef 2", orderQueue));
        chefExecutor.shutdown();
    }
}