package smartphone_factory.factory;


import smartphone_factory.entity.Order;
import smartphone_factory.entity.Smartphone;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

public final class SmartphoneFactory {
    private static SmartphoneFactory INSTANCE;
    private static final int NUMBER_OF_CONTAINERS = Runtime.getRuntime().availableProcessors();
    private static final int SECONDS_DURATION_TO_SLEEP = 1;
    private static final String TEMPLATE_MESSAGE_OF_ORDER_IS_COMPLETED
            = "Order from %s in quantity %d completed %s\n";

    private final Queue<Order> orders;

    private SmartphoneFactory() {
        this.orders = new LinkedList<>();
    }

    public static SmartphoneFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SmartphoneFactory();
        }
        return INSTANCE;
    }


    public void produce() {
        final Order order = orders.poll();
        final Smartphone o = order.getSmartphone();
        for (int i = 0; i < order.getNumberOfSmartphones(); i++) {
            try {
                new Smartphone(o.getName(), o.getModel(), o.getMemoryCapacity(), o.getScreenSize());
                TimeUnit.SECONDS.sleep(SECONDS_DURATION_TO_SLEEP);
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
        out.printf(TEMPLATE_MESSAGE_OF_ORDER_IS_COMPLETED, order.getOderDate(),
                order.getNumberOfSmartphones(), LocalDateTime.now());
    }

    public void addOrder(Order order) {
        orders.add(order);
        if (orders.size() - 1 == 0) {
            this.produce();
        }
    }
}
