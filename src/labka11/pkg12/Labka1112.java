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
            System.out.println("2. Переглянути інформацію про всіх працівників");
            System.out.println("3. Вийти");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        createEmployee(scanner, employees);
                        break;
                    case 2:
                        displayEmployees(employees);
                        break;
                    case 3:
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

        System.out.println("Введіть кількість професій працівника");

        while (!scanner.hasNextInt()) {
            System.out.println("Помилка. Введіть ціле число.");
            scanner.next();
        }

        numProfessions = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numProfessions; i++) {
            String professionName;
            boolean isValidProfession = false;
            do {
                System.out.println("Введіть професію працівника:");
                professionName = scanner.nextLine().toLowerCase();

                switch (professionName) {
                    case "programmer":
                        employee.addProfession(new Programmer());
                        isValidProfession = true;
                        break;
                    case "database administrator":
                        employee.addProfession(new DatabaseAdministrator());
                        isValidProfession = true;
                        break;
                    case "engineer":
                        employee.addProfession(new Engineer());
                        isValidProfession = true;
                        break;
                    case "computer scientist":
                        employee.addProfession(new Informatician());
                        isValidProfession = true;
                        break;
                    default:
                        System.out.println("Такої професії не існує.");
                        break;
                }
            } while (!isValidProfession);
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

        System.out.println("Інформація про працівників:");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println("Ім'я: " + employee.getName());
            System.out.println("Посада: " + employee.getPosition().getPosition());
            System.out.println("Професії:");
            for (Profession profession : employee.getProfessions()) {
                System.out.println("- " + profession.getProfession());
            }
            System.out.println();
        }
    }
}
