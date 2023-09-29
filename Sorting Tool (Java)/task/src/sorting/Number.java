package sorting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Number implements DataType<Long> {

    @Override
    public void type(String sortingMethod, String file, String output) {
        Scanner scanner = scan(file);
        if (scanner == null) return;

        List<Long> list = new ArrayList<>();
        while (true) {
            String input = "";
            try {
                while (scanner.hasNext()) {
                    input = scanner.next();
                    list.add(Long.parseLong(input));
                }
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" is not a long. It will be skipped." );
                continue;
            }
            break;
        }
        scanner.close();
        if (output.isEmpty()) print(list, sortingMethod);
        else toFile(list, sortingMethod, output);
    }

    private void print(List<Long> list, String sortingMethod) {
        System.out.println("Total numbers: " + list.size() + ".");
        switch (sortingMethod) {
            case "natural" -> {
                System.out.print("Sorted data: ");
                Collections.sort(list);
                list.forEach(n -> System.out.print(n + " "));
            }
            case "byCount" -> sort(list);
        }
    }

    private void toFile(List<Long> list, String sortingMethod, String output) {
        if ("natural".equals(sortingMethod)) {
            try (PrintWriter writer = new PrintWriter(output)) {
                writer.println("Total lines: " + list.size() + ".");
                writer.print("Sorted data: ");
                Collections.sort(list);
                list.forEach(n -> writer.print(n + " "));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if ("byCount".equals(sortingMethod)) {
            sort(list, output);
        }
    }
}
