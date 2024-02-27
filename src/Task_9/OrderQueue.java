package Task_9;

import java.util.concurrent.ConcurrentLinkedQueue;

public class OrderQueue {
    private ConcurrentLinkedQueue<Order> orders = new ConcurrentLinkedQueue<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order pollOrder() {
        return orders.poll();
    }
}