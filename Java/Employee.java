package Java;

import java.util.ArrayList;

class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

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

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department='" + department + "'}";
    }
}

class EmployeeCRUD {
    private ArrayList<Employee> employees = new ArrayList<>();

    public void createEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee readEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    public void updateEmployee(int id, String name, String department) {
        Employee emp = readEmployee(id);
        if (emp != null) {
            emp.setName(name);
            emp.setDepartment(department);
        }
    }

    public void deleteEmployee(int id) {
        Employee emp = readEmployee(id);
        if (emp != null) {
            employees.remove(emp);
        }
    }

    public void listEmployees() {
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    public static void main(String[] args) {
        EmployeeCRUD crud = new EmployeeCRUD();
        Employee emp1 = new Employee(1, "A", "Engineering");
        Employee emp2 = new Employee(2, "C", "Marketing");

        crud.createEmployee(emp1);
        crud.createEmployee(emp2);

        System.out.println("List of employees:");
        crud.listEmployees();

        System.out.println("Read Employee with ID 1:");
        System.out.println(crud.readEmployee(1));

        System.out.println("Updating Employee with ID 2:");
        crud.updateEmployee(2, "B", "Sales");
        crud.listEmployees();

        System.out.println("Deleting Employee with ID 1:");
        crud.deleteEmployee(1);
        crud.listEmployees();
    }
}
