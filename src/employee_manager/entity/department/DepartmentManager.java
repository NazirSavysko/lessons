package employee_manager.entity.department;

import employee_manager.entity.Employee;
import employee_manager.entity.position.Position;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static employee_manager.entity.accounting.Accounting.changeSalary;
import static employee_manager.entity.accounting.Accounting.giveValidSalary;
import static employee_manager.entity.position.DepartmentPositionRegistry.*;
import static employee_manager.io.FileManager.removeEmployee;
import static employee_manager.io.FileManager.saveEmployees;


public final class DepartmentManager {

    public static void changeDepartment(final Employee employee,final Scanner scanner) {
        final Map<Integer, Department> departmentMap = getDepartmentMapExceptDepartmentWhichAlreadyHaveEmployee(employee);
        final Department department = getDepartment(departmentMap, scanner);

        final Map<Integer, Position> positionMap = getPositionMapByDepartment(department);
        final Position position = getPosition(positionMap, scanner);

        employee.setPosition(position);
        employee.setSalary(position.getMinSalary().doubleValue());
        employee.setDateOfTheLastSalaryChange(java.time.LocalDate.now());
        saveEmployees();
    }

    private static int levelOfPosition(final Map<Integer, Position> positionMap, final Position position) {
        for (Map.Entry<Integer, Position> entry : positionMap.entrySet()) {
            if (entry.getValue().getName().equals(position.getName())) {
                return entry.getKey();
            }
        }
        return -1;
    }

    private static void fireEmployee(final Employee employee) {
        System.out.println("Employee with ID" + employee.getId() + " has been fired because of "
                + "he/she was at the lowest level of position in the department");
        removeEmployee(employee);
    }

    public static void lowerPosition(final Employee employee, final Scanner scanner) {
        final Department department = getDepartmentByName(employee);
        final TreeMap<Integer, Position> positionMap =
                (TreeMap<Integer, Position>) getPositionMapByDepartment(department);

        final int levelOfPosition = levelOfPosition(positionMap, employee.getPosition());
        if (positionMap.firstEntry().getKey() == levelOfPosition) {
            fireEmployee(employee);
        } else {
            final Position position = positionMap.lowerEntry(levelOfPosition).getValue();
            employee.setPosition(position);
            final double salary = giveValidSalary(position, scanner);
            changeSalary(employee, salary);
            System.out.println("Position was changed successfully");
        }
        saveEmployees();
    }

    public static void raisePosition(final Employee employee, final Scanner scanner) {
        final Department department = getDepartmentByName(employee);
        final TreeMap<Integer, Position> positionMap =
                (TreeMap<Integer, Position>) getPositionMapByDepartment(department);

        final int levelOfPosition = levelOfPosition(positionMap, employee.getPosition());
        if (positionMap.lastEntry().getKey() == levelOfPosition) {
            System.out.println("Employee with ID" + employee.getId()
                    + " has been raised to the highest level of position in the department");
        }else {
            final Position position = positionMap.higherEntry(levelOfPosition).getValue();
            employee.setPosition(position);
            final double salary = giveValidSalary(position, scanner);
            changeSalary(employee, salary);
            System.out.println("Position was changed successfully");
            saveEmployees();
        }
    }

}
