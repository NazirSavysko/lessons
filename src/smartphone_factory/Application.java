package smartphone_factory;

import smartphone_factory.entity.director.Director;
import smartphone_factory.entity.order_entity.Order;
import smartphone_factory.entity.smartphone_builder.AbstractSmartphoneBuilder;
import smartphone_factory.entity.smartphone_entity.Smartphone;
import smartphone_factory.entity.smartphone_entity.SmartphoneRegistry;
import smartphone_factory.factory.SmartphoneBuilderFactory;
import smartphone_factory.factory.SmartphoneFactory;

import java.util.*;

import static java.lang.System.out;

public final class Application implements Observer  {
    private static final String TEMPLATE_MESSAGE_OF_ORDER_IS_CREATED
            = "The order '%d' is created at %s\n";
    private static final String MESSAGE_IF_SOMETHING_WENT_WRONG_WITH_INPUT_NUMBER
            = "An error occurred with input number\n"
            + "Please try again.";


    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final SmartphoneFactory factory = SmartphoneFactory.getInstance();
        final SmartphoneRegistry registry = SmartphoneRegistry.INSTANCE;
        factory.addObserver(new Application());
        while (true) {
            try {
                showMenu();
                final int choice = SCANNER.nextInt();
                switch (choice) {
                    case 1 -> makeOrder(factory, registry);
                    case 2 -> {
                        out.println("Exiting...");
                        factory.shutdown();
                        return;
                    }
                    default -> out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                out.println(MESSAGE_IF_SOMETHING_WENT_WRONG_WITH_INPUT_NUMBER);
                if (SCANNER.hasNextLine()) {
                    SCANNER.nextLine();
                }
            }
        }
    }

    private static void showMenu() {
        out.println("Choose an option:");
        out.println("1. make an order");
        out.println("2. Exit");
    }

    private static void makeOrder(final SmartphoneFactory factory, final SmartphoneRegistry registry) {
        boolean isValid = false;
        while (!isValid) {
            try {
                out.println("Enter the number of smartphones: ");
                final int numberOfSmartphones = SCANNER.nextInt();

                final Map<String, List<Class<? extends Smartphone>>> stringClassMap =
                        registry.smartphoneMapGroupedByCategory();
                final short[] i = new short[1];
                stringClassMap.forEach((key, _) -> out.println(++i[0] + " - " + key)
                );

                out.println("Enter the smartphone category: ");
                final int smartphoneCategory = SCANNER.nextInt();
                final List<Class<? extends Smartphone>> smartphoneListByCategory =
                        registry.getSmartphoneListByCategory(stringClassMap.keySet()
                                .toArray()[smartphoneCategory - 1].toString());
                i[0] = 0;
                smartphoneListByCategory.forEach(smartphoneClass ->
                        out.println(++i[0] + " - " + smartphoneClass.getSimpleName()));

                out.println("Enter the smartphone class: ");
                final int smartphoneClass = SCANNER.nextInt();
                final Class<? extends Smartphone> smartphoneClassByCategory =
                        smartphoneListByCategory.get(smartphoneClass - 1);

                out.println("Enter the name of smartphone: ");
                final String name = SCANNER.next();

                out.println("Enter the model of smartphone: ");
                final String model = SCANNER.next();

                final Director director = new Director(name, model);
                final AbstractSmartphoneBuilder abstractSmartphoneBuilder = SmartphoneBuilderFactory
                        .getSmartphoneBuilder(smartphoneClassByCategory);
                director.buildSmartphone(abstractSmartphoneBuilder);

                final Smartphone smartphone = director.buildSmartphone(abstractSmartphoneBuilder);
                final Order order = new Order(smartphone, numberOfSmartphones);

                out.printf(TEMPLATE_MESSAGE_OF_ORDER_IS_CREATED,
                        order.getId(),
                        order.getOderDate());

                factory.putOrderIntoQueue(order);
                isValid = true;
            } catch (Exception e) {
                out.println(MESSAGE_IF_SOMETHING_WENT_WRONG_WITH_INPUT_NUMBER);

                if (SCANNER.hasNextLine()) {
                    SCANNER.nextLine();
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        out.println("Order status updated: " + arg);
    }
}