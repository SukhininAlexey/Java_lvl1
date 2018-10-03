package ru.geekbrains.arrays;

import java.util.Arrays;

public class ArraysHomework {

    private static void invertValues(int[] arr){
//      Вариант 1:
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0){
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }

//        Вариант 2:
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = arr[i] == 0 ? 1 : 0;
//        }
    }

    private static void fillArray(int[] arr){
        for(int i = 0, n = 1; i < arr.length; i++, n += 3){
            arr[i] = n;
        }
    }

    private static void multiplyArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
//            Вариант 1:
//            arr[i] *= arr[i] < 6 ? 2 : 1; // получилась элегантная запись, но будет выполнено домножение для каждого элемента

//            Вариант 2:
            if(arr[i] < 6)
                arr[i] *= 2;
        }
    }

    private static int findMax(int[] arr){
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){ // я осознанно беру единицу, т.к. нулевой элемент уже считается максимумом
            if(arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    private static int findMin(int[] arr){
        int min = arr[0];
        for(int i = 1; i < arr.length; i++){ // я осознанно беру единицу, т.к. нулевой элемент уже считается минимумом
            if(arr[i] < min)
                min = arr[i];
        }
        return min;
    }

    private static void fillMainDiagonal(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(i == j)
                    arr[i][j] = 1;
            }
        }
    }

    private static void fillSideDiagonal(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(j == arr.length - 1 - i)
                    arr[i][j] = 1;
            }
        }
    }

    private static void printMatrix(int[][] arr){
        System.out.println("Двумерный массив: ");
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private static boolean checkSidesSum(int[] arr){
        int left, right;
        for(int i = 0; i < arr.length; i++){
            left = 0;
            right = 0;
            for(int j = 0; j < arr.length; j++){
                if( i <= j){
                    left += arr[j];
                }else{
                    right += arr[j];
                }
            }

            if(left == right)
                return true;
        }
        return false;
    }

    private static void shiftArrayDifficult(int[] arr, int shift){
        int steps = shift >= 0 ? shift : -shift;
        int step = shift >= 0 ? 1 : -1;
        int tmp, val, nextStep;
        for(int i = 0; i < steps; i++){
            val = arr[0];
            nextStep = 0;
            for(int j = 0; j < arr.length; j++){
                nextStep = nextStep + step;
                if(nextStep >= arr.length){
                    nextStep = 0;
                }else if(nextStep < 0){
                    nextStep = arr.length - 1;
                }

                tmp = arr[nextStep];
                arr[nextStep] = val;
                val = tmp;
            }
        }
    }

    public static void main(String[] args) {

        int[] binaryArr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        invertValues(binaryArr);
        System.out.println("Массив с инвертированными значениями: " + Arrays.toString(binaryArr));

        int[] emptyArr = new int[8];
        fillArray(emptyArr);
        System.out.println("Заполненный по заданию массив: " + Arrays.toString(emptyArr));

        int[] multiplyedArr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyArray(multiplyedArr);
        System.out.println("Массив с домноженными элементами: " + Arrays.toString(multiplyedArr));

        System.out.println("Максимальный элемент предыдущего массива равен " + findMax(multiplyedArr));
        System.out.println("Минимальный элемент предыдущего массива равен " + findMin(multiplyedArr));

        int[][] matrixMain = new int[5][5];
        int[][] matrixSide = new int[5][5];
        fillMainDiagonal(matrixMain);
        fillSideDiagonal(matrixSide);
        printMatrix(matrixMain);
        printMatrix(matrixSide);

        int[] sidesChecker = {10, 1, 2, 3, 4};
        System.out.println("Есть ли место, где в переданном массиве левая и правая части равны: " + (checkSidesSum(sidesChecker) ? "ДА" : "НЕТ"));

        int[] shiftChecker = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shiftArrayDifficult(shiftChecker, -3);
        System.out.println("Смещенный сложно массив: " + Arrays.toString(shiftChecker));
    }
}
