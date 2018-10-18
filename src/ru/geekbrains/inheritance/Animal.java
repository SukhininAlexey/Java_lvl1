package ru.geekbrains.inheritance;
import java.util.Random;

public class Animal {

    protected String name;

    protected double runDistance;
    protected double swimDistance;
    protected double jumpHeight;

    // Максимальные показатели характерны для каждого вида живодного, а не для конкретного экземпляра
    // По умолчанию животное не может ничего, т.к. мы не знаем, что это это за животное
    protected static double maxRunDistance = 0;
    protected static double maxSwimDistance = 0;
    protected static double maxJumpHeight = 0;

    protected static double spread = 10; // Значение в процентах от максимума
    Random rnd = new Random(System.currentTimeMillis()); // Рандомизатор

    public Animal(String name, double runDistance, double swimDistance, double jumpHeight){
        this.name = name;
        this.setRunDistance(runDistance);
        this.setSwimDistance(swimDistance);
        this.setJumpHeight(jumpHeight);
    }

    // Бизнес-логика. Так это стоит назвать, наверное.
    // Решил возвращать строку. Получается нет связанности с консолью, но можно вернуть имя и результат, как строку.
    // По-моему ООПшно.
    public String run(double distance){
        String result = "Животное " + name;
        if(maxRunDistance <= 0) return result + " не умеет бегать.";
        return result + (distance <= this.runDistance ? " пробежало!" : " не пробежало.");
    }

    public String swim(double distance){
        String result = "Животное " + name;
        if(maxSwimDistance <= 0) return result + " не умеет плавать.";
        return result + (distance <= this.swimDistance ? " проплыло!" : " не проплыло.");
    }

    public String jump(double height){
        String result = "Животное " + name;
        if(maxJumpHeight <= 0) return result + " не умеет прыгать.";
        return result + (height <= this.jumpHeight ? " перепрыгнуло!" : " не перепрыгнуло.");
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public double getRunDistance() {
        return runDistance;
    }

    public double getSwimDistance() {
        return swimDistance;
    }

    public double getJumpHeight() {
        return jumpHeight;
    }

    // Сеттеры
    public void setRunDistance(double runDistance){
        if(runDistance > maxRunDistance) runDistance = maxRunDistance; // Урезаем значение до максимального
        runDistance += maxRunDistance * (-spread + rnd.nextDouble() * spread * 2.0) / 100; // Добавляем разброс
        this.runDistance = runDistance <= maxRunDistance ? runDistance : maxRunDistance; // Учитываем ограничение по максимуму после внедрения разброса
        if(this.runDistance < 0) this.runDistance = 0; // Неотрицательное значение
    }

    public void setSwimDistance(double swimDistance){
        if(swimDistance > maxSwimDistance) swimDistance = maxSwimDistance; // Урезаем значение до максимального
        swimDistance += maxSwimDistance * (-spread + rnd.nextDouble() * spread * 2.0) / 100; // Добавляем разброс
        this.swimDistance = swimDistance <= maxSwimDistance ? swimDistance : maxSwimDistance; // Учитываем ограничение по максимуму
        if(this.runDistance < 0) this.runDistance = 0; // Неотрицательное значение
    }

    public void setJumpHeight(double jumpHeight){
        if(jumpHeight > maxJumpHeight) jumpHeight = maxJumpHeight; // Урезаем значение до максимального
        jumpHeight += maxJumpHeight * (-spread + rnd.nextDouble() * spread * 2.0) / 100; // Добавляем разброс
        this.jumpHeight = jumpHeight <= maxJumpHeight ? jumpHeight : maxJumpHeight; // Учитываем ограничение по максимуму
        if(this.runDistance < 0) this.runDistance = 0; // Неотрицательное значение
    }
}
