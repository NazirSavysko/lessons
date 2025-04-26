package employee_manager.entity.position;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Position extends Serializable {

    String getName();

    String getDepartment();

    BigDecimal getMinSalary();
    BigDecimal getMaxSalary();

}
