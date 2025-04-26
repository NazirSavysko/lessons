package employee_manager.entity.department;

import java.io.Serializable;

public enum Department implements Serializable {
    DEVELOPMENT("Development") {
        public String getName() {
            return this.name();
        }
    },
    HR("Human Resources") {
        public String getName() {
            return this.name();
        }
    },
    MARKETING("Marketing") {
        public String getName() {
            return this.name();
        }
    };

    Department(final String name) {}
    public abstract String getName();
}

