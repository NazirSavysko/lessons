package employee_manager.entity.position;

import java.math.BigDecimal;

public interface Position {

    String getName();

    String getDepartment();

    BigDecimal getMinSalary();
    BigDecimal getMaxSalary();

}
