package smartphone_factory.entity;

import java.time.LocalDateTime;

public final class Order {
    private static int maxId = 0;

    private final int id;
    private final LocalDateTime oderDate;
    private final Smartphone smartphone;
    private  OrderStatus orderStatus;
    private final int numberOfSmartphones;

    public Order(LocalDateTime oderDate, Smartphone smartphone, OrderStatus orderStatus, int numberOfSmartphones) {
        this.id = ++maxId;
        this.oderDate = oderDate;
        this.smartphone = smartphone;
        this.orderStatus = orderStatus;
        this.numberOfSmartphones = numberOfSmartphones;
    }
    public Order(Smartphone smartphone, int numberOfSmartphones) {
        this(LocalDateTime.now(), smartphone, OrderStatus.CREATED, numberOfSmartphones);
    }

    public LocalDateTime getOderDate() {
        return this.oderDate;
    }

    public int getNumberOfSmartphones() {
        return this.numberOfSmartphones;
    }

    public Smartphone getSmartphone() {
        return this.smartphone;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public int getId() {
        return this.id;
    }
}
