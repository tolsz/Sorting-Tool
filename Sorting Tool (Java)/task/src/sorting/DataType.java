package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public interface DataType<K> {
    void type(String sortingMethod, String input, String output);

    default void sort(List<K> list) {
        Map<K, Integer> map = new TreeMap<>();
        for (K s : list) {
            if (map.containsKey(s)) {
                map.merge(s, 1, Integer::sum);
            } else {
                map.put(s, 1);
            }
        }

        for (var entry : entriesSortedByValues(map)) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " time(s), "
                    + (entry.getValue() * 100)/list.size()  + "%");
        }
    }

    default void sort(List<K> list, String output) {
        Map<K, Integer> map = new TreeMap<>();
        for (K s : list) {
            if (map.containsKey(s)) {
                map.merge(s, 1, Integer::sum);
            } else {
                map.put(s, 1);
            }
        }

        try (PrintWriter writer = new PrintWriter(output)) {
            for (var entry : entriesSortedByValues(map)) {
                writer.println(entry.getKey() + ": " + entry.getValue() + " time(s), "
                        + (entry.getValue() * 100)/list.size()  + "%");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    default SortedSet<Map.Entry<K, Integer>> entriesSortedByValues(Map<K, Integer> map) {
        SortedSet<Map.Entry<K, Integer>> sortedEntries = new TreeSet<>(
                (e1, e2) -> {
                    int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1;
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    default Scanner scan(String input) {
        if (input.isEmpty()) {
            return new Scanner(System.in);
        } else {
            try {
                return new Scanner(new File(input));
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}