package employee_manager.entity.position.marketing;

import employee_manager.entity.department.Department;
import employee_manager.entity.position.Position;

import java.io.Serial;
import java.math.BigDecimal;

public final class Trainee implements Position {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String NAME = "Trainee";
    private static final BigDecimal MIN_SALARY = new BigDecimal(300);
    private static final BigDecimal MAX_SALARY = new BigDecimal(600);
    private static final Department DEPARTMENT = Department.MARKETING;

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
