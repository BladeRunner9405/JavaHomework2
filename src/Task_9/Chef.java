package Task_9;

import java.util.concurrent.TimeUnit;

public class Chef implements Runnable {
    private String name;
    private OrderQueue orderQueue;

    public Chef(String name, OrderQueue orderQueue) {
        this.name = name;
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (true) {
            Order order = orderQueue.pollOrder();
            if (order != null) {
                System.out.println(name + " is preparing order " + order.getOrderId());
                try {
                    TimeUnit.MILLISECONDS.sleep(order.getPreparationTime());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(name + " has finished preparing order " + order.getOrderId());
            }
        }
    }
}