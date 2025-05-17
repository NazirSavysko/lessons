package smartphone_factory.factory;

import smartphone_factory.entity.order_entity.Order;
import smartphone_factory.entity.smartphone_entity.Smartphone;
import smartphone_factory.io.FileSmartphoneManager;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

import static java.lang.String.format;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.IntStream.range;

public class SmartphoneFactory extends Observable {
    private static SmartphoneFactory INSTANCE;

    private static final int SECONDS_DURATION_TO_SLEEP = 1;

    private static final String TEMPLATE_MESSAGE_OF_ORDER_IS_COMPLETED
            = "Order '%d' from %s in quantity %d completed %s\n";

    private static final String TEMPLATE_MESSAGE_OF_ORDER_IS_TAKEN
            = "Order '%d' in quantity %d taken\n";

    private final Queue<Order> orders;
    private final ExecutorService executorService;
    private final FileSmartphoneManager fileSmartphoneManage;


    SmartphoneFactory() {
        this.fileSmartphoneManage = FileSmartphoneManager.INSTANCE;
        this.orders = new LinkedList<>();
        int numberOfContainers = Runtime.getRuntime().availableProcessors();
        this.executorService = newFixedThreadPool(numberOfContainers);
    }

    public static SmartphoneFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SmartphoneFactory();
        }
        return INSTANCE;
    }

    private void produce() {
        final Order order = getAndRemoveOrderFromQueue();
        final String messageOfOrderIsTaken = format(TEMPLATE_MESSAGE_OF_ORDER_IS_TAKEN,
                order.getId(), order.getNumberOfSmartphones());
        fileSmartphoneManage.writeInformationAboutOrderIntoFile(messageOfOrderIsTaken);

        final Smartphone smartphone = order.getSmartphone();
        final int numberOfSmartphones = order.getNumberOfSmartphones();

        range(0, numberOfSmartphones).forEach(_ -> createSmartphone(smartphone));

        final String messageOfOrderIsCompleted = format(TEMPLATE_MESSAGE_OF_ORDER_IS_COMPLETED,
                order.getId(), order.getOderDate(), numberOfSmartphones, LocalDateTime.now());

        fileSmartphoneManage.writeInformationAboutOrderIntoFile(messageOfOrderIsCompleted);
        this.setChanged();
        this.notifyObservers(messageOfOrderIsCompleted);
    }

    private synchronized Order getAndRemoveOrderFromQueue() {
        return this.orders.poll();
    }

    public void putOrderIntoQueue(final Order order) {
        synchronized (this) {
            this.orders.add(order);
        }

        this.executorService.execute(this::produce);
    }

    private void createSmartphone(final Smartphone smartphone) {
        try {
            final Smartphone _ = smartphone.clone();
            SECONDS.sleep(SECONDS_DURATION_TO_SLEEP);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    public void shutdown() {
        this.executorService.shutdown();
    }
}
