import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Employee class
class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
    }
}

// Main system class
public class EmployeeManagementSystem {
    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public void removeEmployee(int id, PrintWriter writer) {
        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);
            if (e.getId() == id) {
                employees.remove(i);
                writer.println("Employee removed.");
                return;
            }
        }
        writer.println("Employee with ID " + id + " not found.");
    }

    public void displayEmployees(PrintWriter writer) {
        if (employees.isEmpty()) {
            writer.println("No employees to display.");
        } else {
            for (Employee e : employees) {
                writer.println(e);
            }
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        Scanner sc = new Scanner(System.in);

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

            int choice = 0;
            while (choice != 4) {
                writer.println("\n1. Add Employee");
                writer.println("2. Remove Employee");
                writer.println("3. Display Employees");
                writer.println("4. Exit");
                writer.flush(); // Important: make sure it writes now

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // clear newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        System.out.print("Enter Salary: ");
                        double salary = sc.nextDouble();

                        ems.addEmployee(new Employee(id, name, age, salary));
                        writer.println("Employee added: ID=" + id + ", Name=" + name);
                        break;

                    case 2:
                        System.out.print("Enter ID to remove: ");
                        int removeId = sc.nextInt();
                        ems.removeEmployee(removeId, writer);
                        break;

                    case 3:
                        writer.println("\nEmployee List:");
                        ems.displayEmployees(writer);
                        break;

                    case 4:
                        writer.println("Exiting...");
                        break;

                    default:
                        writer.println("Invalid choice. Try again.");
                }

                writer.flush();
            }

            writer.close();
            sc.close();

            System.out.println("All actions saved to output.txt");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }
}
