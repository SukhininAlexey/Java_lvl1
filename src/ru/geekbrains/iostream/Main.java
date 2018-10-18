package ru.geekbrains.iostream;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // Метод по сливанию двух файлов в один. Читает строку, заносит строку в файл. И так же для второго файла делает.
    private static void mergeFiles(String fileA, String fileB, String resultFile) throws FileNotFoundException, IOException {

        // Открываем те потоки, которе поднадобаяться во время первого цикла
        FileInputStream fileInA = new FileInputStream(fileA);
        FileOutputStream fileOutRes = new FileOutputStream(resultFile, true);

        // Сканнер и принтер нужны по-любому
        PrintStream ps = new PrintStream(fileOutRes);
        Scanner sc = new Scanner(fileInA);

        // Печатаем информацию из первого файла
        while(sc.hasNext()){
            ps.println(sc.nextLine());
        }
        fileInA.close(); // Закрываем файл, чтобы освободить ресурс

        // Открываем второй файл и определяем его в качестве аргумента в поток того же сканнера
        FileInputStream fileInB = new FileInputStream(fileB);
        sc = new Scanner(fileInB);

        // Печатаем информацию из второго файла
        while(sc.hasNext()){
            ps.println(sc.nextLine());
        }
        // Всё закрываем
        fileInB.close();
        sc.close();
        fileOutRes.close();
        ps.close();
    }

    // Метрод для поиска слова в файле.
    // Файл полностью состоит из слов. Считывание next() забирает слово от пробела до пробела. Я просто для каждого слова
    // провожу сравнение с переданным вторым аргументом словом.
    private static boolean findWordInFile(String fileName, String word) throws FileNotFoundException, IOException {
        FileInputStream file = new FileInputStream(fileName);
        Scanner sc = new Scanner(file);

        String next;
        while(sc.hasNext()){
            next = sc.next();
            if(next.equals(word)) {
                file.close(); // Не забыли закрыть
                sc.close();
                return true;
            }
        }

        file.close(); // Не забыли закрыть
        sc.close();
        return false;
    }

    // Метод вызывает поиск слова findWordInFile() для каждого файла в папке.
    private static boolean findWordInDir(String dirPath, String word)throws FileNotFoundException, IOException {
        File dir = new File(dirPath);
        File[] arrFiles = dir.listFiles(); // Список объектов - файлов
        boolean result;

        // Проверяем каждый файл
        for(int i = 0; i < arrFiles.length; ++i){
            result = findWordInFile(dirPath + "/" + arrFiles[i].getName(), word);
            if(result){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        try{
            mergeFiles("test_a.txt", "test_b.txt", "result.txt");
            System.out.println("Слово " + (findWordInFile("result.txt", "blackbird") ? "найдено" : "не найдено"));
            System.out.println("Слово " + (findWordInDir("./test_files", "blackbird") ? "найдено" : "не найдено"));
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
