/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author i.
 */
public class Duplicates {
    
    public static void main(String[] args) {
        Duplicates dupl = new Duplicates();
        boolean result = dupl.processFile(new File("inputFile.txt"), new File("outputFile.txt"));
        System.out.println("Duplicate: " + result);
    }
    
    public boolean processFile(File inputFile, File outputFile) {
        Map<String, Integer> map = new TreeMap<>(); // TreeMap сортирует по ключу, хранит только уникальные значения ключа
        String line = null; // переменная для хранения строк из файла inputFile
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
             FileWriter fileWriter = new FileWriter(outputFile, true)) { // создаем потоки на чтение и запись с возможностью дописывать файл
            if (!inputFile.exists()) {
                throw new FileNotFoundException(); // проверка существования файла inputFile, если его нет, выбрасываем FileNotFoundException и возвращаем false
            }
            if (!outputFile.exists()) {
                outputFile.createNewFile(); // проверка существования файла outputFile, если его нет, создаем новый
            }
            while ((line = bufferedReader.readLine()) != null) { // считываем построчно данные из inputFile
                if (!map.containsKey(line)) { // проверяем не содержит ли коллекция ключ со значением строки
                    map.put(line, 1); // если такого ключа не было, добавляем его в коллекцию и ставим количество повторений = 1
                } else {
                    map.replace(line, map.get(line) + 1); // иначе, если ключ уже есть в коллекции, увеличиваем его количество повторений
                }
            }
            
            for (Map.Entry<String, Integer> lines : map.entrySet()) { // проходим по коллекции, пишем всё в файл outputFile
                fileWriter.write(lines.getKey() + "[" + lines.getValue() + "]\n");
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Файл inputFile.txt не найден!");
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
}
