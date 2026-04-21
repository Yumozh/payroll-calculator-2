package com.pluralsight;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the file employee file to process:");


        try {
            FileReader fileReader = new FileReader("employees.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter("payroll-sept-2023.csv");
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);


            ignoreHeaderLine(bufferedReader);
            String line = bufferedReader.readLine();

            while (line != null) {

                String[] fields = line.split(Pattern.quote("|"));

                Employee currentEmployee = generateEmployeeAndFill(fields);
                double grossPay = currentEmployee.getGrossPay();

                String text = String.format("Employee ID: %d\nEmployee name: %s\nEmployee gross pay: $%.2f\n\n", currentEmployee.getEmployeeId(), currentEmployee.getName(), grossPay);
                bufWriter.write(text);

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            bufWriter.close();

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
