package main;

import model.Expense;
import service.ExpenseService;
import java.util.*;

public class ExpenseTrackerMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseService service = new ExpenseService();
        int choice;

        do {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Expenses by Category");
            System.out.println("4. View Total Expense");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Amount: ₹");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Category (Food/Travel/Other): ");
                    String category = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();
                    service.addExpense(new Expense(amount, category, desc));
                }
                case 2 -> service.viewExpenses();
                case 3 -> {
                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();
                    service.viewByCategory(category);
                }
                case 4 -> service.viewTotalExpense();
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        sc.close();
    }
}
