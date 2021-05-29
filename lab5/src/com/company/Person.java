package com.company;


import java.util.Objects;

public class Person {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final int height; //Значение поля должно быть больше 0
    private final double weight; //Значение поля должно быть больше 0

    public Person(String name, int height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, weight);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Person) {
            Person personObj = (Person) obj;
            return name.equals(personObj.getName()) && (height == personObj.getHeight()) && (weight == personObj.getWeight());
        }
        return false;
    }
}

