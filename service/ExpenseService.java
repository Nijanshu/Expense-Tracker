package service;

import model.Expense;
import java.io.*;
import java.util.*;

public class ExpenseService {
    private final String FILE_PATH = "expenses.txt";
    private ArrayList<Expense> expenses = new ArrayList<>();

    public ExpenseService() {
        loadFromFile();
    }

    public void addExpense(Expense e) {
        expenses.add(e);
        saveToFile();
        System.out.println("✅ Expense added successfully!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        System.out.println("\n--- Expense List ---");
        for (Expense e : expenses) System.out.println(e);
    }

    public void viewTotalExpense() {
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        System.out.println("💰 Total Expenses: ₹" + total);
    }

    public void viewByCategory(String category) {
        boolean found = false;
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) System.out.println("No expenses found for category: " + category);
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            expenses = (ArrayList<Expense>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}
