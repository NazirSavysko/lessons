package employee_manager.entity;

import employee_manager.entity.position.Position;

import java.time.LocalDate;

public final class Employee {
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

    public LocalDate getDateOfTheLastSalaryChange() {
        return dateOfTheLastSalaryChange;
    }

    public void setDateOfTheLastSalaryChange(LocalDate dateOfTheLastSalaryChange) {
        this.dateOfTheLastSalaryChange = dateOfTheLastSalaryChange;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfEmployment=" + dateOfEmployment +
                ", position=" + position.getName() +
                ", salary=" + salary +
                ", dateOfTheLastSalaryChange=" + dateOfTheLastSalaryChange +
                '}';
    }
}
