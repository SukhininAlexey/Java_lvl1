package ru.geekbrains.oop.humanResources;

import java.util.Calendar;

public class Employee {

    private String fullName;
    private int birthYear;
    private double salary;
    private int id;

    Employee(int id, String fullName, int birthYear, double salary){ // Стандартный модификатор, чтобы экземпляр только из пакета создавался при помощи класса - фабрики.
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.salary = salary;
    }

    public String getFullName(){
        return this.fullName;
    }

    public int getBirthYear(){
        return this.birthYear;
    }

    public int getId(){
        return this.id;
    }

    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - this.birthYear; // нагуглил объект, работающий с датами
    }

    public double getSalary(){
        return this.salary;
    }

    public void setSalary(double newSalary){
        this.salary = newSalary;
    }
}
