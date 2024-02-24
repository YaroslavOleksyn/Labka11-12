/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labka11.pkg12;

import java.util.List;
import java.util.ArrayList;

/**
 * Клас для представлення працівника у програмі.
 * @author Admin
 */
public class Employee {

    private String name;
    private List<Profession> professions;
    private Position position;

    /**
     * Конструктор класу.
     * @param name
     */
    public Employee(String name) {
        this.name = name;
        this.professions = new ArrayList<>();
    }

    /**
     * Метод для додавання у масив професії працівника.
     * @param profession
     */
    public void addProfession(Profession profession) {
        professions.add(profession);
    }

    /**
     * Метод для отримання професії працівника.
     * @return
     */
    public List<Profession> getProfessions() {
        return professions;
    }

    /**
     * Метод для отримання імені працівника.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для встановлення посади працівника.
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Метод для отримання посади працівника.
     * @return
     */
    public Position getPosition() {
        return position;
    }
}
