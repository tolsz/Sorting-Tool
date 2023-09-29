package sorting;

import java.util.List;

public class Sorter {
    DataType type;

    public Sorter(String[] args) {
        List<String> data = List.of("long", "word", "line");
        List<String> sort = List.of("byCount", "natural");
        String sortingType = "natural";
        String dataType = "word";
        String inputFile = "";
        String outputFile = "";
        for (int i = 0; i < args.length; i++) {
            if ("-sortingType".equals(args[i])) {
                String checkSorting;
                try {
                    checkSorting = args[i + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No sorting type defined!");
                    return;
                }

                if (!sort.contains(checkSorting)) {
                    System.out.println("No sorting type defined!");
                    return;
                } else {
                    sortingType = checkSorting;
                    i += 1;
                }
            } else if ("-dataType".equals(args[i])) {
                String checkData;
                try {
                    checkData = args[i + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No sorting type defined!");
                    return;
                }

                if (!data.contains(checkData)) {
                    System.out.println("No data type defined!");
                    return;
                } else {
                    dataType = checkData;
                    i += 1;
                }
            } else if ("-inputFile".equals(args[i])) {
                inputFile = args[i + 1];
                i += 1;
            } else if ("-outputFile".equals(args[i])) {
                outputFile = args[i + 1];
                i += 1;
            }
            else {
                System.out.println("\"" + args[i] + "\" is not a valid parameter. It will be skipped.");
            }
        }

        switch (dataType) {
            case "line" -> type = new Line();
            case "word" -> type = new Word();
            case "long" -> type = new Number();
        }

        type.type(sortingType, inputFile, outputFile);
    }
}