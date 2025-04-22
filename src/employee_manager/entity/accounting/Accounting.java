package employee_manager.entity.accounting;

import employee_manager.entity.Employee;
import employee_manager.entity.position.Position;

import java.util.Scanner;

public final class Accounting {
    public static void changeSalary(final Employee employee,final double salary) {
        employee.setSalary(salary);
        employee.setDateOfTheLastSalaryChange(java.time.LocalDate.now());
        System.out.println("Salary was changed successfully");
    }

    public static double giveValidSalary( final Position position,final Scanner scanner) {
        boolean isValidSalary = false;
        double validSalary = 0;

        while (!isValidSalary) {
            try {
                System.out.println("Enter employee salary: ");
                validSalary = scanner.nextDouble();
                if (validSalary >= position.getMinSalary().doubleValue() && validSalary <= position.getMaxSalary().doubleValue()) {
                    isValidSalary = true;
                } else {
                    System.out.println("Invalid salary. Salary must be greater than or equal to " + position.getMinSalary()
                            + " and less than or equal to " + position.getMaxSalary());
                }
            }catch (Exception e){
                System.out.println("An error occurred with input number");
                System.out.println("Please try again.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }

        return validSalary;
    }
}
