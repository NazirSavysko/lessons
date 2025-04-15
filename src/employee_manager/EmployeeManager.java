package employee_manager;

import employee_manager.entity.Employee;
import employee_manager.entity.position.development.JuniorDeveloper;
import employee_manager.entity.position.development.MiddleDeveloper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class EmployeeManager {
    public static void main(String[] args) {
        final List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(
                1, "John", "Doe", LocalDate.of(2020, 1, 1),
                new JuniorDeveloper(), 600, LocalDate.of(2023, 1, 1)));
        employees.add(new Employee(
                2, "Jane", "Smith", LocalDate.of(2021, 2, 2),
                new MiddleDeveloper(), 800, LocalDate.of(2023, 2, 2)));
        employees.add(new Employee(
                3, "Alice", "Johnson", LocalDate.of(2022, 3, 3),
                new JuniorDeveloper(), 700, LocalDate.of(2023, 3, 3)));

        int choice;
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. print all employees");
            System.out.println("2. Exit");

            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1 -> employees.forEach(System.out::println);
                case 2 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
