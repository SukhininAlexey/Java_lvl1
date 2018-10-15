package ru.geekbrains.oop.humanResources;

public class Department {
    private Employee[] employees; // В масстиве содержатся сотрудники. Длина у массива жестко будет задана, так что...
    private int count; // ...придется использовать count для того, чтобы понять, сколько всего сотрудников в данный момент.
    private int employeeId; // решение задания 7.
    private String deptName;

    public Department(String deptName, int employeesQuantyty){
        this.deptName = deptName;
        this.employees = new Employee[employeesQuantyty];
    }

    public Employee createNewEmployee(String fullName, int birthYear, double salary){
        this.employees[this.count] = new Employee(++this.employeeId, fullName, birthYear, salary);
        return this.employees[this.count++]; // Постинкримент увеличит после выдачи значения. Нам так и надо.
    }

    public Employee getEmployee(int id){
        for(int i = 0; i < this.count; i++){
            if(this.employees[i].getId() == id){
                return this.employees[i];
            }
        }
        return null; // Ничего не нашли
    }

    public Employee getEmployee(String fullName){
        for(int i = 0; i < this.count; i++){
            if(this.employees[i].getFullName() == fullName){
                return this.employees[i];
            }
        }
        return null; // Ничего не нашли
    }

    // Удаляем только по id, чтобы не было ошибки
    public boolean dismissEmployee(int id){
        boolean found = false;
        for(int i = 0; i < this.count; i++){

            // Сначала заNULLляем сотрудника и вешаем флаг, что это случилось, потом, если правее кто-то есть, сдвигаем остальных
            if(this.employees[i].getId() == id ) {
                this.employees[i] = null;
                found = true;
            }else if(found){
                this.employees[i - 1] = this.employees[i];
            }
        }

        if(found) count--;
        return found;
    }

    // Решение задания 5
    public void printInfoByAge(int age){
        Employee e;
        for(int i = 0; i < this.count; i++){
            e = this.employees[i];
            if(e.getAge() >= age){
                System.out.println("Сотрудник: " + e.getFullName() + "; оклад: " + e.getSalary() + "; возраст: " + e.getAge());
            }
        }
    }

    // Решение задания 6.
    public void increaseSalaryByAge(double salaryIncrease, int age){
        for(int i = 0; i < this.count; i++){

            // Если человеку уже испольнилось 45, то он старше 40. С точки зрения математики условие должно быть строгим,
            // но с точки зрения здравого смысла >=. Так что ставлю >=.
            if(this.employees[i].getAge() >= age){
                double currentSalary = this.employees[i].getSalary();
                this.employees[i].setSalary(currentSalary + salaryIncrease);
            }
        }
    }

    // Решение задания 6-1
    public int getAverageAge() {
        int sum = 0;
        for(int i = 0; i < this.count; i++){
            sum += this.employees[i].getAge();
        }
        return sum / this.count; // Не буду в double переводить, т.к. такобычно средний возраст не показывают. Осознанный шаг.
    }

    // Решение задания 6-2
    public double getAverageSalary() {
        double sum = 0;
        for(int i = 0; i < this.count; i++){
            sum += this.employees[i].getSalary();
        }
        return sum / this.count;
    }

    public int getCount(){
        return this.count;
    }
}
