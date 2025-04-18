package employee_manager.io;

import employee_manager.entity.Employee;
import employee_manager.entity.position.development.JuniorDeveloper;
import employee_manager.entity.position.development.MiddleDeveloper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class FileManager {
   public static final List<Employee> EMPLOYEES = new ArrayList<>(
            List.of(
                    new Employee(1, "John", "Doe", LocalDate.of(2020, 1, 1), new JuniorDeveloper(), 1000, LocalDate.now()),
                    new Employee(2, "Jane", "Smith", LocalDate.of(2021, 2, 2), new MiddleDeveloper(), 2000, LocalDate.now())
            )
   );

    public static void addEmployee(final Employee employee) {
        EMPLOYEES.add(employee);
    }

    public static void removeEmployee(final Employee employee) {
        EMPLOYEES.remove(employee);
    }
}
