package employee_manager.entity.department;

import employee_manager.entity.Employee;
import employee_manager.entity.position.Position;

import java.util.Map;

import static employee_manager.entity.position.DepartmentPositionRegistry.*;


public final class DepartmentManager {

    public static void changeDepartment(final Employee employee) {
        final Map<Integer, Department> departmentMap = getDepartmentMapExceptDepartmentWhichAlreadyHaveEmployee(employee);
        final Department department = getDepartment(departmentMap);

        final Map<Integer, Position> positionMap = getPositionMapByDepartment(department);
        final Position position = getPosition(positionMap);

        employee.setPosition(position);
        employee.setSalary(position.getMinSalary().doubleValue());
    }

}
