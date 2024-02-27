package Task_9;

import java.util.List;

public class Order {
    private int orderId;
    private List<String> dishes;
    private int preparationTime;

    public Order(int orderId, List<String> dishes, int preparationTime) {
        this.orderId = orderId;
        this.dishes = dishes;
        this.preparationTime = preparationTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<String> getDishes() {
        return dishes;
    }

    public int getPreparationTime() {
        return preparationTime;
    }
}