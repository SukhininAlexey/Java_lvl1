package ru.geekbrains.game;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final char HUMAN_DOT = 'X';
    private static final char AI_DOT = 'O';
    private static final char EMPTY_DOT = '_';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static final int FIELD_SIZE = 7; // TODO: меняем эту константу, чтобы изменить размер КВАДРАТНОГО поля
    private static final int WIN_QTTY = 4; // TODO: меняем эту константу, чтобы изменить количество символов в ряд для победы
    private static int fieldSizeY;
    private static int fieldSizeX;
    private static char[][] field;

    private static void initField() {
        fieldSizeX = FIELD_SIZE; // беру из одного константного поля, чтобы был квадрт
        fieldSizeY = FIELD_SIZE; // беру из одного константного поля, чтобы был квадрт
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = EMPTY_DOT;
            }
        }
    }
    private static void printField() {
        String strY = ""; // Добавляю адаптивный вывод _y
        for(int i = 0; i < fieldSizeY; i++) strY += "_y";
        System.out.println(strY);
        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print("x");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }
    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.print("Please enter coordinates X and Y (1 to 3) separated by space");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isEmptyCell(x, y) || !isValidCell(x, y));
        field[y][x] = HUMAN_DOT;
    }
    private static boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }
    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }
    private static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }
    private static boolean isFieldFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y))
                    return false;
            }
        }
        return true;
    }
    private static boolean checkWin(char c) {

        int vertQtty = 0,
            horQtty = 0,
            posDiagQtty = 0,
            negDiagQtty = 0,
            posJ, negJ;

        // Проверка горизонталей и ветикалей
        for(int i = 0; i < FIELD_SIZE; i++){
            vertQtty = 0;
            horQtty = 0;
            for(int j = 0; j < FIELD_SIZE; j++){

                if(field[i][j] == c){
                    horQtty++;
                    if(vertQtty >= WIN_QTTY) return true;
                }else{
                    horQtty = 0;
                }

                // Ход конём: меняем местами индексы здесь, вместо того, чтобы бежать по столбцам другим циклом.
                // Экономим на сложности алгоритма. Будет O(N^2) вместо O(2*N^2). Хотя, по количеству обращений к памяти так же.
                // Но вот итераций меньше = оптимальнее.
                if(field[j][i] == c){
                    vertQtty++;
                    if(horQtty >= WIN_QTTY) return true;
                }else{
                    horQtty = 0;
                }
            }
        }

        /**
         * Я нащупал 2 варианта решения проверки по диагоналям:
         *
         * Первый: сначала пробегать по всем возможноым коэффециентам сдвига диагонали k, внутри бежать по строкам и столбцам,
         * проверять условие попадания в диагональ конкретного коэффециента сдвига, если попали - проверять совпадение с
         * переданным символом. Навскидку сложность без упрощения: O(2*N*N^2).  2N от коэффециента сдвига от -N до +N и
         * N^2 от перебора всего массива для для кадждого коэффециента сдвига. После упрощения O(2N^3). Куб одним словом. Жуть.
         *
         * Второй: пробегать по коэффециентам сдвига, бежать по одной стороне поля, а вторую вычислять через коэффециент
         * и размер квадратного поля. Сложность снова навскидку: O(2*N*N). 2N от коэффециента сдвига от -N до +N и еще
         * N от размера стороны квадратного массива. После приведения получается O(2N^2), что явно быстрее. Количество
         * проверок одинаковое.
         *
         * Выпод: получается, что метод с подходом на основе уравнений оптимальнее, чем метод на основе перебора в лоб.
         *
         * Есть еще заговздка с тем, что алгоритм проверяет диагонали в углах, которые имеют вообще единичную длину. А
         * так же проверяет все диагонали, на которых по условию победы быть не может, где длина меньше длины ряда для
         * победы. То есть, нужно просто начинать с других величин - это я решил в переменных kMin и kMax. То есть теперь
         * сложность должна быть O((2N-k)*N), где N - сторона квадратного поля, а k - количество символов в ряд для победы.
         * Сложность получается всегда меньше двойного квадрата. Иногда даже меньше квадрата.
         *
         * Есть еще проблема обязательной квадратности. Но её я пока не решал. Не знаю, играют ли на прямоугольных полях
         * крестики-нолики. Дальше буду ИИ заниматься.
          */
        int kMin = -(FIELD_SIZE - 1 - WIN_QTTY),
            kMax = FIELD_SIZE - WIN_QTTY + 1;

//        for(int k = -(FIELD_SIZE - 1); k < FIELD_SIZE; k++){ // Сохранил прошлый вариант на всякий случай
        for(int k = kMin; k <= kMax; k++){
            posDiagQtty = 0;
            negDiagQtty = 0;

            for(int i = 0; i < FIELD_SIZE; i++){
                posJ = i - k + 1;
                negJ = FIELD_SIZE - k - i;

                // Я почитал про условные операторы. Если идет ряд конъюнкций, то при первом false программа не будет
                // вести дальнейшие проверки. То есть, если не существует поля, то обращения к нему не будет. Это удобно,
                // т.к. не надо вкладывать одну проверку в другую.
                if(posJ >=0 && posJ < FIELD_SIZE && field[i][posJ] == c){
                    posDiagQtty++;
                    if(posDiagQtty >= WIN_QTTY) return true;
                }else{
                    posDiagQtty = 0;
                }


                if(negJ >=0 && negJ < FIELD_SIZE && field[i][negJ] == c){
                    negDiagQtty++;
                    if(negDiagQtty >= WIN_QTTY) return true;
                }else{
                    negDiagQtty = 0;
                }
            }
        }

        return false;
    }



    public static void main(String[] args) {
        initField();
        printField();
//        while (true) {
        while (true) {
            humanTurn();
            printField();
            if (checkWin(HUMAN_DOT)) {
                System.out.println("Player wins!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Draw!");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("Computer wins!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Draw!");
                break;
            }
        }
        //play again?
        // if !y break;
    }
//    }

}