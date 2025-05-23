package employee_manager.entity.position;

import employee_manager.entity.Employee;
import employee_manager.entity.department.Department;
import employee_manager.entity.position.development.JuniorDeveloper;
import employee_manager.entity.position.development.MiddleDeveloper;
import employee_manager.entity.position.development.SeniorDeveloper;
import employee_manager.entity.position.development.TeamLead;
import employee_manager.entity.position.hr.HeadOfHr;
import employee_manager.entity.position.hr.HrManager;
import employee_manager.entity.position.hr.SeniorManager;
import employee_manager.entity.position.marketing.HeadOfMarketing;
import employee_manager.entity.position.marketing.Specialist;
import employee_manager.entity.position.marketing.Trainee;

import java.util.*;

import static employee_manager.entity.department.Department.*;

public final class DepartmentPositionRegistry {
    private static final Map<Integer, Department> DEPARTMENT_MAP = new TreeMap<>(Map.of(
            1, DEVELOPMENT,
            2, HR,
            3, MARKETING
    ));
    private static final Map<Department,Map<Integer,Position>> POSITION_MAP_BY_DEPARTMENT = new HashMap<>(
            Map.of(
                    DEVELOPMENT, new TreeMap<>(Map.of(
                            1,  new TeamLead(),
                            2, new SeniorDeveloper(),
                            3, new MiddleDeveloper(),
                            4, new JuniorDeveloper()
                    )),
                    HR, new TreeMap<>(Map.of(
                            1, new HeadOfHr(),
                            2, new SeniorManager(),
                            3, new HrManager()
                    )),
                    MARKETING,new TreeMap<>(Map.of(
                            1, new HeadOfMarketing(),
                            2, new Specialist(),
                            3, new Trainee()
                    ))
            )
    );

    public static Map<Integer,Department> getDepartmentMap(){
        return Collections.unmodifiableMap(DEPARTMENT_MAP);
    }
    public static Map<Integer,Department> getDepartmentMapExceptDepartmentWhichAlreadyHaveEmployee(final Employee employee){
        final Map<Integer, Department> departmentMap = new TreeMap<>(DEPARTMENT_MAP);
        departmentMap.entrySet()
                .removeIf(entry -> entry.getValue().getName().equals(employee.getPosition().getDepartment()));
        return Collections.unmodifiableMap(departmentMap);
    }
    public static  Map<Integer,Position> getPositionMapByDepartment(final Department department){
        return Collections.unmodifiableMap(POSITION_MAP_BY_DEPARTMENT.get(department));
    }

    public static Department getDepartment(final Map<Integer, Department> departmentMap,final Scanner scanner) {
        while (true) {
            try {
                System.out.println("Please select a department: ");
                departmentMap.forEach((key, value) -> System.out.println(key + " - " + value.getName()));
                int choiceDepartment = scanner.nextInt();
                final Department department = departmentMap.get(choiceDepartment);
                if (department != null) {
                    return department;
                } else {
                    System.out.println("Invalid department number. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred with input number");
                System.out.println("Please try again.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }
    }
    public static Department getDepartmentByName(final Employee employee) {
        for (Map.Entry<Integer, Department> entry : DEPARTMENT_MAP.entrySet()) {
            if (entry.getValue().getName().equals(employee.getPosition().getDepartment())) {
                return entry.getValue();
            }
        }
       return null;
    }
    public static Position getPosition(final Map<Integer, Position> map,final Scanner scanner) {
        while (true) {
            try {
            System.out.println("Please select a position: ");
            map.forEach((key, value) -> System.out.println(key + " - " + value.getName()));
            int choicePosition = scanner.nextInt();
            final Position position = map.get(choicePosition);
            if (position != null) {
                return position;
            } else {
                System.out.println("Invalid position number. Please try again.");
            }
        }catch (Exception e){
                System.out.println("An error occurred with input number");
                System.out.println("Please try again.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }
    }

}
