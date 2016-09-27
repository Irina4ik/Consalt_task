/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author i.
 */
public class Subsequence {

    public static void main(String[] args) {
        boolean isSubseq = Subsequence.find(Arrays.asList("Hello", "!"), Arrays.asList("Hello", "!", "!", "!"));
        System.out.println(isSubseq);
        isSubseq = Subsequence.find(Arrays.asList(1, "01.10"), Arrays.asList(1, "01.10"));
        System.out.println(isSubseq);
        isSubseq = Subsequence.find(Arrays.asList("J", "A", "V", "A", "!", 27, 9, 2016), Arrays.asList("X", "J", "Y", "F", "A", "Co", "V", "VM", "A", "Com", "!", 27, 23, 9, 93, 2016));
        System.out.println(isSubseq);
        isSubseq = Subsequence.find(Arrays.asList(4, 5, 5, 4, 1), Arrays.asList(1, 4, 5, 5, 9, 1));
        System.out.println(isSubseq);
        isSubseq = Subsequence.find(Arrays.asList("T", "a", "s", "k"), Arrays.asList("a", "s", "k"));
        System.out.println(isSubseq);
        isSubseq = Subsequence.find(Arrays.asList("Goodbye", "!"), Arrays.asList("!", "Goodbye"));
        System.out.println(isSubseq);

    }

    //метод сравнивает последовательности X и Y
    private static boolean find(List<Object> sublistX, List<Object> listY) {
        //
        if (sublistX.size() > listY.size()) {
            return false;
        }
        int icount = 0; // индекс по X, количество совпадений
        //сравниваем списки
        for (int j = 0; (icount < sublistX.size()) && (j < listY.size()); j++) {
            // берем элемент из первого списка, ищем его во втором
            // нашли (увеличиваем icount), переходим к поиску следующего
            if (sublistX.get(icount).equals(listY.get(j))) {
                icount++;
            }
            // прошли до конца Y, там не нашли какой-то элемент из X, прекращаем поиск
        }
        // или проверили все X, нашли все элементы в Y, тогда true
        // (можно ли лист X можно получить вычеркиванием некоторых элементов листа Y)
        return (icount == sublistX.size());
    }
}
