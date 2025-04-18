package employee_manager;

import employee_manager.entity.Employee;
import employee_manager.entity.department.Department;
import employee_manager.entity.position.Position;
import employee_manager.io.FileManager;

import java.util.Scanner;

import static employee_manager.entity.department.DepartmentManager.*;
import static employee_manager.entity.position.DepartmentPositionRegistry.*;
import static employee_manager.io.FileManager.*;
import static employee_manager.io.FileManager.EMPLOYEES;

public final class EmployeeManager {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String IF_REMOVED = "Employee Employee with ID %d removed successfully.\n";
    private static final String IF_NOT_REMOVED = "Employee with ID %d not found. Please try again.\n";
    private static final String IF_FOUND = "Employee with ID %d found successfully.\n";
    private static final String IF_NOT_FOUND = "Employee with ID %d not found. Please try again.\n";
    private static final String MESSAGE_FOR_REMOVE = "Enter employee ID to remove: ";
    private static final String MESSAGE_FOR_FIND = "Enter employee ID to find: ";

    public static void main(String[] args) {
        while (true) {
            try {
                int choice;

                showMainMenu();
                System.out.println("Enter your choice: ");
                choice = SCANNER.nextInt();
                switch (choice) {
                    case 1 -> showAllEmployees();
                    case 2 -> createEmployee();
                    case 3 -> removeEmployee();
                    case 4 -> changeDepartment(findEmployeeById(MESSAGE_FOR_FIND, IF_FOUND, IF_NOT_FOUND));
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred with input number");
                System.out.println("Please try again.");
                if (SCANNER.hasNextLine()) {
                    SCANNER.nextLine();
                }
            }
        }
    }

    private static void showAllEmployees() {
        System.out.println("All employees:");
        EMPLOYEES.forEach(System.out::println);
    }

    private static void showMainMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. print all employees");
        System.out.println("2. create employee");
        System.out.println("3. remove employee");
        System.out.println("4. change employee department");
        System.out.println("5. Exit");
    }

    private static void createEmployee() {
        System.out.println("Creating a new employee...");
        SCANNER.nextLine();
        System.out.println("Enter employee name: ");
        final String name = SCANNER.nextLine();
        System.out.println("Enter employee surname: ");
        final String surname = SCANNER.nextLine();
        final Department department = getDepartment(getDepartmentMap());
        final Position position = getPosition(getPositionMapByDepartment(department));
        boolean isValidSalary = false;
         double salary = 0;
        while (!isValidSalary) {
            try {
            System.out.println("Enter employee salary: ");
            salary = SCANNER.nextDouble();
            if (salary >= position.getMinSalary().doubleValue() && salary <= position.getMaxSalary().doubleValue()) {
                isValidSalary = true;
            } else {
                System.out.println("Invalid salary. Salary must be greater than or equal to " + position.getMinSalary()
                        + " and less than or equal to " + position.getMaxSalary());
            }
            }catch (Exception e){
                System.out.println("An error occurred with input number");
                System.out.println("Please try again.");
                if (SCANNER.hasNextLine()) {
                    SCANNER.nextLine();
                }
            }
        }

        final Employee employee = new Employee(name, surname, position, salary);
        addEmployee(employee);
        System.out.println("Employee created successfully: " + employee);
    }


    private static void removeEmployee() {
        FileManager.removeEmployee(findEmployeeById(MESSAGE_FOR_REMOVE, IF_REMOVED, IF_NOT_REMOVED));
    }


    private static Employee findEmployeeById(final String purpose, final String ifSuccess, final String ifNotSuccess) {
        Employee employee = null;
        boolean isFound = false;
        while (!isFound) {
            try {
                EMPLOYEES.forEach(System.out::println);
                System.out.println(purpose);
                final int id = SCANNER.nextInt();
                for (Employee emp : EMPLOYEES) {
                    if (emp.getId() == id) {
                        employee = emp;
                        isFound = true;
                    }
                }
                if (isFound) {
                    System.out.printf(ifSuccess, id);
                } else {
                    System.out.printf(ifNotSuccess, id);
                }
            } catch (Exception e) {
                System.out.println("An error occurred with input number");
                System.out.println("Please try again.");
                if (SCANNER.hasNextLine()) {
                    SCANNER.nextLine();
                }
            }
        }
        return employee;
    }
}

