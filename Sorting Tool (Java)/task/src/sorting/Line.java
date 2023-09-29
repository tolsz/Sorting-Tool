package sorting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Line implements DataType<String> {

    @Override
    public void type(String sortingMethod, String input, String output) {
        Scanner scanner = scan(input);
        if (scanner == null) return;

        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        scanner.close();
        if (output.isEmpty()) print(list, sortingMethod);
        else toFile(list, sortingMethod, output);
    }

    private void print(List<String> list, String sortingMethod) {
        System.out.println("Total lines: " + list.size() + ".");

        switch (sortingMethod) {
            case "natural" -> {
                System.out.println("Sorted data:");
                Collections.sort(list);
                list.forEach(n -> System.out.println(n + " "));
            }
            case "byCount" -> sort(list);
        }
    }

    private void toFile(List<String> list, String sortingMethod, String output) {
        if ("natural".equals(sortingMethod)) {
            try (PrintWriter writer = new PrintWriter(output)) {
                writer.println("Total lines: " + list.size() + ".");
                writer.println("Sorted data:");
                Collections.sort(list);
                list.forEach(n -> writer.println(n + " "));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if ("byCount".equals(sortingMethod)) {
            sort(list, output);
        }
    }
}
