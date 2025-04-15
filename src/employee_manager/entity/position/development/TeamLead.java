package employee_manager.entity.position.development;

import employee_manager.entity.department.Department;
import employee_manager.entity.position.Position;

import java.math.BigDecimal;

public final class TeamLead implements Position {
    private static final String NAME = "Junior Developer";
    private static final BigDecimal MIN_SALARY = new BigDecimal(2500);
    private static final BigDecimal MAX_SALARY = new BigDecimal(7000);
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
