package service;

import model.Employee;
import java.io.*;
import java.util.*;

public class EmployeeService {
    private final String FILE_PATH = "employees.txt";
    private ArrayList<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        loadFromFile();
    }

    public void addEmployee(Employee e) {
        employees.add(e);
        saveToFile();
        System.out.println("✅ Employee added successfully!");
    }

    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public void searchEmployee(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                System.out.println("Found: " + e);
                return;
            }
        }
        System.out.println("❌ Employee not found!");
    }

    public void deleteEmployee(int id) {
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            Employee e = it.next();
            if (e.getId() == id) {
                it.remove();
                saveToFile();
                System.out.println("✅ Employee deleted successfully!");
                return;
            }
        }
        System.out.println("❌ Employee not found!");
    }

    // Save data to file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data from file
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            employees = (ArrayList<Employee>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
