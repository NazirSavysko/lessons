package employee_manager.entity.position.development;

import employee_manager.entity.department.Department;
import employee_manager.entity.position.Position;

import java.math.BigDecimal;

public final class MiddleDeveloper implements Position {
    private static final String NAME = "Middle Developer";
    private static final BigDecimal MIN_SALARY = new BigDecimal(800);
    private static final BigDecimal MAX_SALARY = new BigDecimal(2000);
    private static final Department DEPARTMENT = Department.DEVELOPMENT;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDepartment() {
        return DEPARTMENT.getName();
    }

    @Override
    public BigDecimal getMinSalary() {
        return MIN_SALARY;
    }

    @Override
    public BigDecimal getMaxSalary() {
        return MAX_SALARY;
    }
}
