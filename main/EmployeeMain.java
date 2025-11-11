package main;

import model.Employee;
import service.EmployeeService;
import java.util.*;

public class EmployeeMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();
        int choice;

        do {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    service.addEmployee(new Employee(id, name, salary));
                }
                case 2 -> service.viewEmployees();
                case 3 -> {
                    System.out.print("Enter ID: ");
                    service.searchEmployee(sc.nextInt());
                }
                case 4 -> {
                    System.out.print("Enter ID: ");
                    service.deleteEmployee(sc.nextInt());
                }
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        sc.close();
    }
}
