package smartphone_factory.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import smartphone_factory.entity.order_entity.Order;
import smartphone_factory.entity.smartphone_entity.NoNameChinaSmartphone;
import smartphone_factory.factory.SmartphoneFactory;
import smartphone_factory.io.FileSmartphoneManager;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.lines;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
import static smartphone_factory.io.FileSmartphoneManager.setFilePath;

class SmartphoneFactoryTest {

    private static SmartphoneFactory smartphoneFactory;
    private static MethodHandle handle;

    @BeforeAll
    public static void setUp() throws IllegalAccessException, NoSuchMethodException {
        smartphoneFactory = SmartphoneFactory.getInstance();
        MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(SmartphoneFactory.class, MethodHandles.lookup());

        MethodType methodType = MethodType.methodType(void.class);

        handle = lookup.findSpecial(
                SmartphoneFactory.class, "produce", methodType, SmartphoneFactory.class
        );
    }


    @Test
    public void test_when_queue_is_empty() throws Throwable {
        // Given
        final String filePath = "test_when_queue_is_empty.txt";
        setFilePath(filePath);
        // When
        smartphoneFactory.putOrderIntoQueue(null);

        // Then
        assertThrows(NullPointerException.class, () -> {
            handle.bindTo(smartphoneFactory).invoke();
        });
    }

    @Test
    public void when_queue_is_not_empty() throws IOException {
        // Given
        final String filePath = "test_when_queue_is_not_empty.txt";
        setFilePath(filePath);
        final Order order = new Order(new NoNameChinaSmartphone("s", "s"), 11);

        // When
        smartphoneFactory.putOrderIntoQueue(order);
        smartphoneFactory.putOrderIntoQueue(order);
        smartphoneFactory.putOrderIntoQueue(order);
        smartphoneFactory.putOrderIntoQueue(order);
        smartphoneFactory.putOrderIntoQueue(order);
        smartphoneFactory.putOrderIntoQueue(order);
        smartphoneFactory.putOrderIntoQueue(order);
        smartphoneFactory.putOrderIntoQueue(order);

        // Then
        int timeToWait = 20;
        while (timeToWait > 0) {
            try {
                SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            timeToWait--;
        }
        final long countOfLines = lines(Path.of(filePath)).count();
        assertEquals(32, countOfLines);
    }

    @Test
    public void when_file_can_not_be_created() {
        // Given
        final String filePath = null;
        setFilePath(filePath);

        // When
        smartphoneFactory.putOrderIntoQueue(new Order(new NoNameChinaSmartphone("s", "s"), 11));

        // Then
        assertThrows(NullPointerException.class, () -> {
            handle.bindTo(smartphoneFactory).invoke();
        });
    }

}