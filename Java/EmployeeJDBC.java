package Java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Employee {
    private int id;
    private String name;
    private String department;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
}

public class EmployeeJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    // Method to establish connection to the database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE operation
    public void createEmployee(Employee employee) {
        String query = "INSERT INTO employees (name, department) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());
            statement.executeUpdate();
            System.out.println("Employee created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ operation
    public Employee getEmployee(int id) {
        String query = "SELECT * FROM employees WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setDepartment(resultSet.getString("department"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE operation
    public void updateEmployee(Employee employee) {
        String query = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());
            statement.setInt(3, employee.getId());
            statement.executeUpdate();
            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE operation
    public void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EmployeeJDBC employeeJDBC = new EmployeeJDBC();

        // Create
        Employee newEmployee = new Employee();
        newEmployee.setName("John Doe");
        newEmployee.setDepartment("HR");
        employeeJDBC.createEmployee(newEmployee);

        // Read
        Employee employee = employeeJDBC.getEmployee(1);
        if (employee != null) {
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("Employee Name: " + employee.getName());
            System.out.println("Employee Department: " + employee.getDepartment());
        }

        // Update
        employee.setName("John Smith");
        employee.setDepartment("Finance");
        employeeJDBC.updateEmployee(employee);

        // Delete
        employeeJDBC.deleteEmployee(1);
    }
}
