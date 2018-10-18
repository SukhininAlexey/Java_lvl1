package ru.geekbrains.inheritance;

public class Main {
    public static void main(String[] args) {

        // Вся настройка ниже в трех переменных
        double sprintDistance = 300;
        double barrierHeight = 1.5;
        double swimDistance = 4;

        Cat catBars = new Cat("Барсик", 150, 1.5);
        Cat catMurz = new Cat("Мурзик", 190, 1.99);

        Dog dogShar = new Dog("Шарик", 100,2, 0);
        Dog dogDrug = new Dog("Дружок", 450, 9.5, 0.4);

        Horse horseGrey = new Horse("Серогрив", 2000, 150, 4); // Специально более высокие значения задаю4
        Horse horseBlack = new Horse("Тенегрив", 1000, 75, 2);

        Bird birdNiko = new Bird("Нико", 2, 0.075);
        Bird birdTiler = new Bird("Тайлер", 4.5, 1.75); // Тайлер прыгучий

        Animal[] animals = {catBars, catMurz, dogDrug, dogShar, horseGrey, horseBlack, birdNiko, birdTiler};


        System.out.println("Соревнования на дальность бега, расстоание " + sprintDistance + " метров.");
        for(int i = 0; i < animals.length; ++i){
            System.out.println(animals[i].getName() + " умеет бегать на расстоание " + animals[i].getRunDistance());
            System.out.println(animals[i].run(sprintDistance));
        }

        System.out.println("Соревнования на высоту прыжка, препятствие " + barrierHeight + " метров.");
        for(int i = 0; i < animals.length; ++i){
            System.out.println(animals[i].getName() + " умеет прыгать на высоту " + animals[i].getJumpHeight());
            System.out.println(animals[i].jump(barrierHeight));
        }

        System.out.println("Соревнования на умение плавать на расстояние " + swimDistance + " метров.");
        for(int i = 0; i < animals.length; ++i){
            System.out.println(animals[i].getName() + " умеет плавать на расстояние " + animals[i].getSwimDistance());
            System.out.println(animals[i].swim(swimDistance));
        }
    }
}
