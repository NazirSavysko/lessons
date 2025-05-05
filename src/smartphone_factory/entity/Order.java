package smartphone_factory.entity;

import java.time.LocalDateTime;

public final class Order {
    private final LocalDateTime oderDate;
    private final Smartphone smartphone;
    private  OrderStatus orderStatus;
    private final int numberOfSmartphones;

    public Order(LocalDateTime oderDate, Smartphone smartphone, OrderStatus orderStatus, int numberOfSmartphones) {
        this.oderDate = oderDate;
        this.smartphone = smartphone;
        this.orderStatus = orderStatus;
        this.numberOfSmartphones = numberOfSmartphones;
    }
    public Order(Smartphone smartphone, int numberOfSmartphones) {
        this(LocalDateTime.now(), smartphone, OrderStatus.CREATED, numberOfSmartphones);
    }

    public LocalDateTime getOderDate() {
        return oderDate;
    }

    public int getNumberOfSmartphones() {
        return numberOfSmartphones;
    }

    public Smartphone getSmartphone() {
        return smartphone;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
