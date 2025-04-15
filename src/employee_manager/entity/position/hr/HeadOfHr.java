package employee_manager.entity.position.hr;

import employee_manager.entity.department.Department;
import employee_manager.entity.position.Position;

import java.math.BigDecimal;

public final class HeadOfHr implements Position {
    private static final String NAME = "Head of HR";
    private static final BigDecimal MIN_SALARY = new BigDecimal(800);
    private static final BigDecimal MAX_SALARY = new BigDecimal(1700);
    private static final Department DEPARTMENT = Department.HR;

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
