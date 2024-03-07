/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labka11.pkg12;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Головний клас виконання програми з меню.
 *
 * @author Admin
 */
public class Labka1112 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Створити нового працівника");
            System.out.println("2. Видалити існуючого працівника");
            System.out.println("3. Переглянути інформацію про всіх працівників");
            System.out.println("4. Додати професію існуючому працівнику.");
            System.out.println("5. Вийти");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createEmployee(scanner, employees);
                        break;
                    case 2:
                        removeEmployee(employees);
                        break;
                    case 3:
                        displayEmployees(employees);
                        break;
                    case 4:
                        addProfession(scanner, employees);
                        break;
                    case 5:
                        System.out.println("Дякую за користування!");
                        return;

                    default:
                        System.out.println("Неправильний вибір.");
                }
            } else {
                System.out.println("Введіть ціле число.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Метод яким створюється працівник і задається професія та посада.
     *
     * @param scanner
     * @param employees
     */
    public static void createEmployee(Scanner scanner, List<Employee> employees) {
        System.out.println("Введіть ім'я працівника:");
        String name = scanner.nextLine();

        Employee employee = new Employee(name);
        int numProfessions = 0;
        boolean validdigit = false;
        System.out.println("Введіть кількість професій працівника");

        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Помилка. Введіть ціле число.");
                scanner.next();
            }

            numProfessions = scanner.nextInt();

            if (numProfessions > 4) {
                System.out.println("Помилка. Професій повинно бути не більше 4.");
                scanner.nextLine();
            }
        } while (numProfessions > 4);
        List<String> EmployeProfessions = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < numProfessions; i++) {
            String professionName;
            boolean iftrue = false;
            boolean duplicate = false;
            do {
                System.out.println("Введіть професію працівника:");
                professionName = scanner.nextLine().toLowerCase();
                if (EmployeProfessions.contains(professionName)) {
                    System.out.println("Помилка, працівник вже має таку професію.");
                    duplicate = true;
                } else {
                    switch (professionName) {
                        case "programmer":
                            employee.addProfession(new Programmer());
                            EmployeProfessions.add(professionName);
                            iftrue = true;
                            break;
                        case "database administrator":
                            employee.addProfession(new DatabaseAdministrator());
                            EmployeProfessions.add(professionName);
                            iftrue = true;
                            break;
                        case "engineer":
                            employee.addProfession(new Engineer());
                            EmployeProfessions.add(professionName);
                            iftrue = true;
                            break;
                        case "computer scientist":
                            employee.addProfession(new Informatician());
                            EmployeProfessions.add(professionName);
                            iftrue = true;
                            break;
                        default:
                            System.out.println("Такої професії не існує.");
                            break;
                    }
                }
            } while (!iftrue);

        }

        String positionName;
        do {
            System.out.println("Введіть посаду працівника:");
            positionName = scanner.nextLine();

            switch (positionName.toLowerCase()) {
                case "system administrator":
                    employee.setPosition(new SystemAdministrator());
                    break;
                case "software engineer":
                    employee.setPosition(new SoftwareEngineer());
                    break;
                case "database engineer":
                    employee.setPosition(new DatabaseEngineer());
                    break;
                default:
                    System.out.println("Такої посади не існує.");
            }
        } while (employee.getPosition() == null);

        employees.add(employee);
        System.out.println("Працівник створений.");

    }

    /**
     * Метод для виведення всіх працівників і їх посад та професій.
     *
     * @param employees
     */
    public static void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("Немає працівників у списках.");
            return;
        }
        System.out.println("===========================");
        System.out.println("Інформація про працівників:");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println("Ім'я: " + employee.getName());
            System.out.println("Посада: " + employee.getPosition().getPosition());
            System.out.println("Професії:");
            for (Profession profession : employee.getProfessions()) {
                System.out.println("- " + profession.getProfession());
            }
            System.out.println("===========================");
            System.out.println();
        }
    }

    /**
     * Метод для видалення інсуючого працівника.
     *
     * @param employees
     */
    public static void removeEmployee(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("Немає працівників у списках.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        boolean found = false;

        do {
            System.out.println("Введіть працівника, якого хочете видалити:");
            String removeName = scanner.nextLine();

            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                if (employee.getName().equalsIgnoreCase(removeName)) {
                    employees.remove(i);
                    found = true;
                    System.out.println("Працівника успішно видалено.");
                    break;
                }
            }
            if (!found) {
                System.out.println("Працівника не знайдено");
            }
        } while (!found);

    }

    public static void addProfession(Scanner scanner, List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("Немає працівників у списках.");
            return;
        }

        System.out.println("Введіть ім'я працівника, якому хочете додати професію:");
        String employeeName = scanner.nextLine();

        Employee addProffesionEmployee = null;
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(employeeName)) {
                addProffesionEmployee = employee;
                break;
            }
        }

        if (addProffesionEmployee == null) {
            System.out.println("Працівника не знайдено.");
            return;
        }

        if (addProffesionEmployee.getProfessions().size() >= 4) {
            System.out.println("Працівник має всі професії.");
            return;
        }

        System.out.println("Введіть нову професію:");
        String newProfession = scanner.nextLine().toLowerCase();

        List<Profession> professions = addProffesionEmployee.getProfessions();
        for (Profession profession : professions) {
            if (profession.getProfession().equalsIgnoreCase(newProfession)) {
                System.out.println("Працівник вже має таку професію.");
                return;
            }
        }

        switch (newProfession) {
            case "programmer":
                addProffesionEmployee.addProfession(new Programmer());
                break;
            case "database administrator":
                addProffesionEmployee.addProfession(new DatabaseAdministrator());
                break;
            case "engineer":
                addProffesionEmployee.addProfession(new Engineer());
                break;
            case "computer scientist":
                addProffesionEmployee.addProfession(new Informatician());
                break;
            default:
                System.out.println("Такої професії не існує.");
                break;
        }
        System.out.println("Професія успішно додана.");
    }
}
