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

            ignoreHeaderLine(bufferedReader);
            String line = bufferedReader.readLine();

            while (line != null) {

                String[] fields = line.split(Pattern.quote("|"));

//                int employeeId = Integer.parseInt(fields[0].trim());
//                String name = fields[1].trim();
//                double hoursWorked = Double.parseDouble(fields[2].trim());
//                double payRate = Double.parseDouble(fields[3].trim());

                Employee currentEmployee = generateEmployeeAndFill(fields);
                double grossPay = currentEmployee.getGrossPay();

                System.out.printf("Employee ID: %d\nEmployee name: %s\nEmployee gross pay: $%.2f\n\n", currentEmployee.getEmployeeId(), currentEmployee.getName(), grossPay);

                line = bufferedReader.readLine();
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("The file could not be read. Please make sure the file is available and not locked and then try again.");
            e.printStackTrace();
        }
    }

    private static void ignoreHeaderLine(BufferedReader bufferedReader) throws IOException {
        bufferedReader.readLine(); //ignore first row because it is a header and not data
    }

    private static Employee generateEmployeeAndFill(String[]fields){
        Employee currentEmployee = new Employee();

        currentEmployee.setEmployeeId(Integer.parseInt(fields[0].trim()));
        currentEmployee.setName(fields[1].trim());
        currentEmployee.setHoursWorked(Double.parseDouble(fields[2].trim()));
        currentEmployee.setPayRate(Double.parseDouble(fields[3].trim()));
        return currentEmployee;
    }
}
