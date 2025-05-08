package smartphone_factory.factory;


import smartphone_factory.entity.Order;
import smartphone_factory.entity.Smartphone;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.IntStream.range;

public final class SmartphoneFactory {
    private static SmartphoneFactory INSTANCE;

    private static final int NUMBER_OF_CONTAINERS = Runtime.getRuntime().availableProcessors();
    private static final int SECONDS_DURATION_TO_SLEEP = 1;

    private static final String TEMPLATE_MESSAGE_OF_ORDER_IS_COMPLETED
            = "Order '%d' from %s in quantity %d completed %s\n";

    private final Queue<Order> orders;
    private final ExecutorService executorService;


    private SmartphoneFactory() {
        this.orders = new LinkedList<>();
        this.executorService = newFixedThreadPool(NUMBER_OF_CONTAINERS);
    }

    public static SmartphoneFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SmartphoneFactory();
        }
        return INSTANCE;
    }

    private void produce() {
        final Order order = getAndRemoveOrderFromQueue();
        final Smartphone smartphone = order.getSmartphone();
        final int numberOfSmartphones = order.getNumberOfSmartphones();

        range(0, numberOfSmartphones).forEach(_ -> createSmartphone(smartphone));

        out.printf(TEMPLATE_MESSAGE_OF_ORDER_IS_COMPLETED,
                order.getId(),
                order.getOderDate(),
                order.getNumberOfSmartphones(),
                LocalDateTime.now());
    }

    private synchronized Order getAndRemoveOrderFromQueue() {
        while (this.orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }

        return this.orders.poll();
    }

    public void putOrderIntoQueue(final Order order) {
        synchronized (this) {
            this.orders.add(order);
            notify();
        }

        this.executorService.execute(this::produce);
    }

    private void createSmartphone(final Smartphone smartphone) {
        try {
            new Smartphone(smartphone.getName(),
                    smartphone.getModel(),
                    smartphone.getMemoryCapacity(),
                    smartphone.getScreenSize());

            SECONDS.sleep(SECONDS_DURATION_TO_SLEEP);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    public void shutdown() {
        this.executorService.shutdown();
    }
}
