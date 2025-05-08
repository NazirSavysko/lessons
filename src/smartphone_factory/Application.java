package smartphone_factory;

import smartphone_factory.entity.Order;
import smartphone_factory.entity.Smartphone;
import smartphone_factory.factory.SmartphoneFactory;

import java.util.Scanner;

import static java.lang.System.out;

public final class Application {
    private static final String TEMPLATE_MESSAGE_OF_ORDER_IS_CREATED
            = "The order '%d' is created at %s\n";
    private static final String MESSAGE_IF_SOMETHING_WENT_WRONG_WITH_INPUT_NUMBER
            = "An error occurred with input number\n"
            + "Please try again.";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final SmartphoneFactory factory = SmartphoneFactory.getInstance();
        while (true) {
            try {
                showMenu();
                final int choice = SCANNER.nextInt();
                switch (choice) {
                    case 1 -> makeOrder(factory);
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

    private static void makeOrder(final SmartphoneFactory factory) {
        boolean isValid = false;
        while (!isValid) {
            try {
                out.println("Enter the number of smartphones: ");
                final int numberOfSmartphones = SCANNER.nextInt();
                out.println("Enter the name of smartphone: ");
                final String name = SCANNER.next();
                out.println("Enter the model of smartphone: ");
                final String model = SCANNER.next();
                out.println("Enter the memory capacity of smartphone: ");
                final int memoryCapacity = SCANNER.nextInt();
                out.println("Enter the screen size of smartphone: ");
                final double screenSize = SCANNER.nextDouble();

                final Smartphone smartphone = new Smartphone(name, model, memoryCapacity, screenSize);
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
}