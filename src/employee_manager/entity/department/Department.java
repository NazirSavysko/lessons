package employee_manager.entity.department;

public enum Department {
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

