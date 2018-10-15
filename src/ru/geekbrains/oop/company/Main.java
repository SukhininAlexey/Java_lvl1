package ru.geekbrains.oop.company;

import ru.geekbrains.oop.humanResources.Department;
import ru.geekbrains.oop.humanResources.Employee;

public class Main {
    public static void main(String[] args) {
        // Department - это вшитая реализация задания 8. Я понимаю, что я должен был использовать статику. Но я ОЧЕНЬ
        // хотел попробовать поработать с объектами. Кроме того, в задании не сказано, как именно надо решать задачу.
        // Я осознанно конструирую фабрику.
        Department salesDept = new Department("Продажи розовых слонов", 10);
        salesDept.createNewEmployee("Николай Шишкин", 1963, 60000); // Единственный, кто реально работает
        salesDept.createNewEmployee("Жансяолу Го", 1970, 120000); // Китаец-трансгендр. Наш отдел толерантен ко всем. Зарплата соответствующая.
        salesDept.createNewEmployee("Мария Вкаловная", 1993, 70000); // Получает больше мужчины, т.к. иначе либералы в суд подадут.
        salesDept.createNewEmployee("Иван Смирнов", 1988, 40000); // Обычный работящий парень. Не понимает новые веяния моды. Работает хорошо
        salesDept.createNewEmployee("Жанна Гаврилина", 1972, 50000); // Мать-одиночка, двое детей. Уволить невозможно.
        salesDept.createNewEmployee("Евгений Круглов", 1977, 50000); // Внучатый племянник соседа начальника отдела по кадрам.

        // Операции с кадрами:
        System.out.println("Количество сотрудников до сокращения: " + salesDept.getCount());
        Employee emp = salesDept.getEmployee("Иван Смирнов");
        salesDept.dismissEmployee(emp.getId()); // Надо кого-то сократить, но остальных не уволишь...
        System.out.println("Количество сотрудников после сокращения: " + salesDept.getCount());
        salesDept.createNewEmployee("Фёдор Баженов", 1976, 20000); // Пришел с завода. Хочет тоговать слониками. Зарплату низкую просит.
        System.out.println("Количество сотрудников после донайма: " + salesDept.getCount());

        // Задание 5:
        System.out.println("Информмация о сотрудниках до увеличения зарплат: ");
        salesDept.printInfoByAge(40);

        // Задание 6:
        salesDept.increaseSalaryByAge(5000, 45);
        System.out.println("Информмация о сотрудниках после увеличения зарплат: ");
        salesDept.printInfoByAge(0); // посмотрим на всех.

        //Задание 7:
        System.out.println("Средний возраст сотрудников: " + salesDept.getAverageAge());
        System.out.println("Средняя зарплата сотрудников: " + salesDept.getAverageSalary());
    }
}
