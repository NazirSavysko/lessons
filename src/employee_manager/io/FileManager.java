package employee_manager.io;

import employee_manager.entity.Employee;
import employee_manager.entity.position.development.TeamLead;
import employee_manager.entity.position.hr.HeadOfHr;
import employee_manager.entity.position.marketing.HeadOfMarketing;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableMap;


public final class FileManager {
    public static final List<Employee> EMPLOYEES = new ArrayList<>();
    private static final String FILE_NAME = "employees.data";

    public static void addEmployee(final Employee employee) {
        EMPLOYEES.add(employee);
        saveEmployees();
    }

    public static void removeEmployee(final Employee employee) {
        EMPLOYEES.remove(employee);
        saveEmployees();
    }

    public static void readEmployees() {
        createFileIfNotExists();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            EMPLOYEES.addAll((List<Employee>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employees from file: " + e.getMessage());
        }
    }

    private static void createFileIfNotExists() {
        final File file = new File(FILE_NAME);
        if (!file.exists()) {
            ObjectOutputStream objectOutputStream = null;
            try {
                file.createNewFile();
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(EMPLOYEES);

            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            } finally {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                        System.out.println("Error closing file: " + e.getMessage());
                    }
                }
            }
        }
    }

    public static void saveEmployees() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            objectOutputStream.writeObject(EMPLOYEES);
        } catch (IOException e) {
            System.out.println("Error saving employees to file: " + e.getMessage());
        }

    }

    private static Map<String, List<Employee>> getMapEmployeesGroupingByDepartment() {
        final Map<String, List<Employee>> mapEmployeesGroupingByDepartment = EMPLOYEES
                .stream()
                .collect(Collectors.groupingBy(e -> e.getPosition().getDepartment()));

        return unmodifiableMap(mapEmployeesGroupingByDepartment);
    }

    public static void printAllEmployeesGroupingByDepartment(final String formatForEmployee, final String formatForHeader) {
        printMap(getMapEmployeesGroupingByDepartment(), formatForEmployee, formatForHeader);
    }

    public static void printEmployeesByDepartmentAndSortedByDescendingSalary(final String formatForEmployee,final String formatForHeader) {
        final Map<String, List<Employee>> mapOfEmployeesByDepartmentAndSortedByDescendingSalary =
                getMapEmployeesGroupingByDepartment()
                        .entrySet()
                        .stream()
                        .peek(entry -> {
                            List<Employee> listSortedEmployeesBySalary = entry.getValue()
                                    .stream()
                                    .sorted((Comparator.comparingDouble(Employee::getSalary).reversed()))
                                    .toList();
                            entry.getValue().clear();
                            entry.getValue().addAll(listSortedEmployeesBySalary);
                        }).
                        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        printMap(mapOfEmployeesByDepartmentAndSortedByDescendingSalary, formatForEmployee, formatForHeader);
    }

    public static void printEmployeesHavingTheBiggestPositionInEachDepartment(final String formatForEmployee, final String formatHeader) {
        final Map<String, List<Employee>> mapOfEmployeesHavingTheBiggestPositionInEachDepartment =
                getMapEmployeesGroupingByDepartment()
                        .entrySet()
                        .stream()
                        .peek(entry -> {
                            List<Employee> listSortedEmployeesBySalary = entry.getValue()
                                    .stream()
                                    .filter(FileManager::checkIfEmployeeIsTheBiggestPositionInDepartment)
                                    .toList();
                            entry.getValue().clear();
                            entry.getValue().addAll(listSortedEmployeesBySalary);
                        }).
                        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        printMap(mapOfEmployeesHavingTheBiggestPositionInEachDepartment, formatForEmployee, formatHeader);
    }

    private static boolean checkIfEmployeeIsTheBiggestPositionInDepartment(final Employee employee) {
        Set<String> setOfBiggestPositions = new HashSet<>();
        setOfBiggestPositions.add(new TeamLead().getName());
        setOfBiggestPositions.add(new HeadOfMarketing().getName());
        setOfBiggestPositions.add(new HeadOfHr().getName());

        return setOfBiggestPositions.contains(employee.getPosition().getName());
    }

    private static void printMap(final Map<String, List<Employee>> mapOfEmployees,final String formatForEmployee,final String formatForHeader) {
     mapOfEmployees.forEach((key, value) -> {
         System.out.println("Department: " + key);
         System.out.println();
            System.out.printf(formatForHeader,
                    "ID", "Name", "Surname", "Date", "Position", "Salary","Date of last salary change");
            value.forEach(employee -> System.out.println(employee.toString(formatForEmployee)));
     });
    }
}
