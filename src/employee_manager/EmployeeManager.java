package employee_manager;

import employee_manager.entity.Employee;
import employee_manager.entity.department.Department;
import employee_manager.entity.position.Position;
import employee_manager.io.FileManager;

import java.util.Scanner;

import static employee_manager.entity.accounting.Accounting.changeSalary;
import static employee_manager.entity.accounting.Accounting.giveValidSalary;
import static employee_manager.entity.department.DepartmentManager.changeDepartment;
import static employee_manager.entity.position.DepartmentPositionRegistry.*;
import static employee_manager.io.FileManager.*;

public final class EmployeeManager {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String IF_REMOVED = "Employee Employee with ID %d removed successfully.\n";
    private static final String IF_NOT_REMOVED = "Employee with ID %d not found. Please try again.\n";
    private static final String IF_FOUND = "Employee with ID %d found successfully.\n";
    private static final String IF_NOT_FOUND = "Employee with ID %d not found. Please try again.\n";
    private static final String MESSAGE_FOR_REMOVE = "Enter employee ID to remove: ";
    private static final String MESSAGE_FOR_FIND = "Enter employee ID to find: ";
    private static final String FORMAT_HEADER = "%4s  %-10s  %-10s  %-12s  %-17s  %10s     %-10s%n";
    private static final String FORMAT_FOR_EMPLOYEE = "%4d  %-10s  %-10s  %-12s  %-17s  %10.2f     %-6s%n";


    public static void main(String[] args) {
        FileManager.readEmployees();
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
                    case 4 -> changeDepartment(findEmployeeById(MESSAGE_FOR_FIND, IF_FOUND, IF_NOT_FOUND), SCANNER);
                    case 5 -> printAllEmployeesGroupingByDepartment(FORMAT_FOR_EMPLOYEE, FORMAT_HEADER);
                    case 6 -> printEmployeesByDepartmentAndSortedByDescendingSalary(FORMAT_FOR_EMPLOYEE,FORMAT_HEADER);
                    case 7 -> printEmployeesHavingTheBiggestPositionInEachDepartment(FORMAT_FOR_EMPLOYEE, FORMAT_HEADER);
                    case 8 -> {
                        final Employee employee = findEmployeeById(MESSAGE_FOR_FIND, IF_FOUND, IF_NOT_FOUND);
                        final double salary = giveValidSalary(employee.getPosition(), SCANNER);
                        changeSalary(employee, salary);
                    }
                    case 9 -> {
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
        System.out.printf(FORMAT_HEADER,
                "ID", "Name", "Surname", "Date", "Position", "Salary","Date of last salary change");
        EMPLOYEES.forEach(employee -> System.out.println(employee.toString(FORMAT_FOR_EMPLOYEE)));
    }

    private static void showMainMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. print all employees");
        System.out.println("2. create employee");
        System.out.println("3. remove employee");
        System.out.println("4. change employee department");
        System.out.println("5. print employees grouping by department");
        System.out.println("6. print employees grouping by department and sorted by descending salary");
        System.out.println("7. print employees having the biggest position in each department");
        System.out.println("8. change employee salary");
        System.out.println("9. Exit");
    }

    private static void createEmployee() {
        System.out.println("Creating a new employee...");
        SCANNER.nextLine();
        System.out.println("Enter employee name: ");
        final String name = SCANNER.nextLine();
        System.out.println("Enter employee surname: ");
        final String surname = SCANNER.nextLine();
        final Department department = getDepartment(getDepartmentMap(), SCANNER);
        final Position position = getPosition(getPositionMapByDepartment(department), SCANNER);
        final double salary = giveValidSalary(position, SCANNER);

        final int id =  EMPLOYEES.isEmpty() ? 1 :  EMPLOYEES.getLast().getId() + 1;
        final Employee employee = new Employee(id, name, surname, position, salary);
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

