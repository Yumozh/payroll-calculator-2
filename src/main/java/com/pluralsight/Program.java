package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Program {
    public static void main(String[] args) {

        try {
            FileReader fileReader = new FileReader("employees.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {

                line = bufferedReader.readLine();
                String[] fields = line.split(Pattern.quote("|"));

                int employeeId = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                double hoursWorked = Double.parseDouble(fields[2].trim());
                double payRate = Double.parseDouble(fields[3].trim());

                Employee currentEmployee = new Employee(employeeId, name, hoursWorked, payRate);
                double grossPay = currentEmployee.getGrossPay(currentEmployee.getHoursWorked(), currentEmployee.getPayRate());

                System.out.printf("Employee ID: %d\nEmployee name: %s\nEmployee gross pay: $%.2f\n\n", currentEmployee.getEmployeeId(), currentEmployee.getName(), grossPay);
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("The file could not be read. Please make sure the file is available and not locked and then try again.");
            e.printStackTrace();
        }
    }
}
