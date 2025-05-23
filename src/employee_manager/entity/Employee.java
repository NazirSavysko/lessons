package employee_manager.entity;

import employee_manager.entity.position.Position;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public final class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    private final int id;
    private final String name;
    private final String surname;
    private final LocalDate dateOfEmployment;
    private Position position;
    private double salary;
    private LocalDate dateOfTheLastSalaryChange;

    public Employee(int id, String name, String surname, LocalDate dateOfEmployment, Position position, double salary, LocalDate dateOfTheLastSalaryChange) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfEmployment = dateOfEmployment;
        this.position = position;
        this.salary = salary;
        this.dateOfTheLastSalaryChange = dateOfTheLastSalaryChange;
    }
    public Employee(int id, String name, String surname, Position position, double salary) {
        this(id, name, surname, LocalDate.now(), position, salary, LocalDate.now());
    }
    public  int getId() {
        return id;
    }

    public double getSalary() {
        return salary;

    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public void setDateOfTheLastSalaryChange(LocalDate dateOfTheLastSalaryChange) {
        this.dateOfTheLastSalaryChange = dateOfTheLastSalaryChange;
    }


    public String toString(final String format) {
       return String.format(format,
                id, name, surname, dateOfEmployment, position.getName(), salary, dateOfTheLastSalaryChange);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfEmployment=" + dateOfEmployment +
                ", position=" + position +
                ", salary=" + salary +
                ", dateOfTheLastSalaryChange=" + dateOfTheLastSalaryChange +
                '}';
    }
}
