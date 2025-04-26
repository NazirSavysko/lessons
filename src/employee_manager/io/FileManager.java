package employee_manager.io;

import employee_manager.entity.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


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
}
