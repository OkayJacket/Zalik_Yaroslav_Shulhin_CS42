import java.util.ArrayList;
import java.util.List;

abstract class OrganizationComponent {
    protected String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public abstract double calculateSalary();
    public abstract void showStructure(String indent);
}

class Employee extends OrganizationComponent {
    private double salary;

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public void showStructure(String indent) {
        System.out.println(indent + "Employee: " + name + ", Salary: " + salary);
    }
}

class Department extends OrganizationComponent {
    private List<OrganizationComponent> components;

    public Department(String name) {
        super(name);
        this.components = new ArrayList<>();
    }

    public void addComponent(OrganizationComponent component) {
        components.add(component);
    }
	// Обчислення загального фонду зарплати для всіх підлеглих компонентів
    @Override
    public double calculateSalary() {
        double totalSalary = 0;
        for (OrganizationComponent component : components) {
            totalSalary += component.calculateSalary();
        }
        return totalSalary;
    }

    @Override
    public void showStructure(String indent) {
        System.out.println(indent + "Department: " + name);
        for (OrganizationComponent component : components) {
            component.showStructure(indent + "    ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        
        Employee accountant1 = new Employee("Бухгалтер1", 3000);
        Employee accountant2 = new Employee("Бухгалтер2", 3000);
        Employee manager1 = new Employee("Менеджер1", 4000);
        Employee manager2 = new Employee("Менеджер2", 4000);

       
        Department mainDepartment = new Department("Головний відділ");
        Department financeDepartment = new Department("Фінансовий відділ");
        Department salesDepartment = new Department("Відділ продажів");

        // Описано додавання співробітників у відділи
        financeDepartment.addComponent(accountant1);
        financeDepartment.addComponent(accountant2);

        salesDepartment.addComponent(manager1);
        salesDepartment.addComponent(manager2);

        // Також додавання підлеглих відділів
        mainDepartment.addComponent(financeDepartment);
        mainDepartment.addComponent(salesDepartment);

        // Розрахунок загального фонду зарплати
        double totalSalary = mainDepartment.calculateSalary();
        System.out.println("Загальний фонд зарплати: " + totalSalary);

        // Відображення структури компанії
        mainDepartment.showStructure("");
    }
}