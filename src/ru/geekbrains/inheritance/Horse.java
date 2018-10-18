package ru.geekbrains.inheritance;

public class Horse extends Animal {

    // Необходимо задать общеклассовые характеристики до того, как отрабоатет динамический конструктор.
    // Я сначала думал переопределить статические поля в теле класса, НО в джаве нельзя так сделать.
    // Мы слово static прошли. Статический блок здесь подходит просто идеально. Почитал, как инициализировать
    // статические переменные. Оказалось, что статический конструктор отрабатывает при создании самого класса, до
    // обращения даже к статике, гораздо раньше динамики. Ну прям здорово ложится. Всё ещё тащусь от Джавы (^_^)
    static{
        maxRunDistance = 1500;
        maxSwimDistance = 100;
        maxJumpHeight = 3;
    }

    // Задача динамического конструктора - просто пробросить через себя состояния. Логики базового класса будет достаточно.
    public Horse(String name, double runDistance, double swimDistance, double jumpHeight){
        super(name, runDistance, swimDistance, jumpHeight);
    }

    // Далее бизнес - логика. Теперь мы знаем, что за животное у нас тут - значит можно более точное описание сделать.
    // Можно убрать условие на нулевые максимальные показатели, т.к. речь идет о конкретном животном. Например,
    // все коты умеют прыгать, но конкретный кот может иметь нулевую высолту прыжка и не будет справляться с прыжками вообще.
    public String run(double distance){
        String result = "Лошадка " + name;
        return result + (distance <= this.runDistance ? " пробежала!" : " не пробежала.");
    }

    public String swim(double distance){
        String result = "Лошадка " + name;
        return result + (distance <= this.swimDistance ? " проплыла!" : " не проплыла.");
    }

    public String jump(double height){
        String result = "Лошадка " + name;
        return result + (height <= this.jumpHeight ? " перепрыгнула!" : " не перепрыгнула.");
    }
}
