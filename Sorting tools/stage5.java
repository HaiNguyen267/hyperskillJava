package hSt;



import java.util.*;

public class Main {

    static Map<String, Integer> map = new LinkedHashMap<>();
    static int total = 0;
    static String dataType = "word";
    static String sortingType = "natural";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String paraRegex = "-dataType|-sortingType";
        String dataTypeRegex = "long|word|line";
        String sortingTypeRegex = "natural|byCount";

        boolean undefinedDatatype = false;
        boolean undefinedSortingType = false;

        List<String> cmdArgs = Arrays.asList(args);

        // check if data is undefined
        if (cmdArgs.contains("-dataType")) {

            int indexOfDataType = cmdArgs.indexOf("-dataType");

            if (indexOfDataType < cmdArgs.size() - 1 && cmdArgs.get(indexOfDataType + 1).matches(dataTypeRegex)) {
                dataType = cmdArgs.get(cmdArgs.indexOf("-dataType") + 1);
            } else {
                System.out.println("No data type defined!");
                undefinedDatatype = true;
            }
        }

        // check if the sorting type is undefined
        if (cmdArgs.contains("-sortingType")) {

            int indexOfSortingType = cmdArgs.indexOf("-sortingType");

            if (indexOfSortingType < cmdArgs.size() - 1 && cmdArgs.get(indexOfSortingType + 1).matches(sortingTypeRegex)) {
                sortingType = cmdArgs.get(cmdArgs.indexOf("-sortingType") + 1);
            } else {
                System.out.println("No sorting type defined!");
                undefinedSortingType = true;
            }
        }

        // print out all invalid parameters
        for (String para : cmdArgs) {
            if (!para.matches(dataTypeRegex) && !para.matches(sortingTypeRegex) && !para.matches(paraRegex)) {
                System.out.println(String.format("\"%s\" is not a valid parameter. It will be skipped.", para));
            }
        }

        // take parameters and process
        if (!undefinedDatatype && !undefinedSortingType) {

            while (sc.hasNextLine()) {
                //System.out.println("has next line");
                String line = sc.nextLine();

                if (dataType.equals("line")) {
                    if (!map.containsKey(line)) {
                        map.put(line, 1);
                    } else {
                        map.put(line, map.get(line) + 1);
                    }
                    total ++;
                } else if (dataType.equals("word")) {
                    String[] parts = line.split("\\s+");

                    for (String key : parts) {
                        if (!map.containsKey(key)) {
                            map.put(key, 1);
                        } else {
                            map.put(key, map.get(key) + 1);
                        }
                        total ++;
                    }
                } else if (dataType.equals("long")) {
                    String[] parts = line.split("\\s+");

                    for (String key : parts) {
                        try {
                            long num = Long.parseLong(key); // if key is not a number, this line throws exception

                            if (!map.containsKey(key)) {
                                map.put(key, 1);
                            } else {
                                map.put(key, map.get(key) + 1);
                            }
                            total ++;
                        } catch (Exception e) {
                            System.out.println(String.format("\"%s\" is not a long. It will be skipped.", key));
                        }

                    }
                }
            }

            map = sort();
            printResult();
        }

    }

    public static Map<String, Integer> sort() {
        Map<String, Integer> result = new LinkedHashMap<>();

        if (sortingType.equals("natural")) {
            // after add a key and its value to the result map, it will be removed from the original map
            while (!map.entrySet().isEmpty()) {
                //System.out.println("natural");
                long minLength = Integer.MAX_VALUE;
                String keyOfMinLength = "";

                if (dataType.equals("long")) {
                    // str is the number in string format
                    for (String str : map.keySet()) {
                        if (Long.parseLong(str) < minLength) {
                            minLength = Long.parseLong(str);
                            keyOfMinLength = str;
                        }
                    }
                } else if (dataType.equals("word") || dataType.equals("line")){
                    // word or line
                    for (String str : map.keySet()) {
                        if (str.length() < minLength) {
                            minLength = str.length();
                            keyOfMinLength = str;
                        }
                    }
                }

                result.put(keyOfMinLength, map.get(keyOfMinLength));
                map.remove(keyOfMinLength);
//                System.out.println(map.entrySet().size());
//                System.out.println(dataType);
//                System.out.println(sortingType);
            }
        } else if (sortingType.equals("byCount")) {
            /* before sorting keys(numbers, words or lines) by their values(repeat times),
             sort the keys by the order of ascending order(for numbers) or lexicography order (for words and lines)
            , so that in the case when some number have equal repeat times, they will be sorted by ascedning order (the smaller with appear first in case 2 or more number are equaal)
             or when some words (or lines) have equal repeat times, those words (lines) will appear in lexicagraphic order

             */
            map = bubbleSort(map);


            // after add a key and its value to the result map, it will be removed from the original map
            while (!map.entrySet().isEmpty()) {
                //System.out.println("byCount");
                long minCount = Integer.MAX_VALUE;
                String keyOfMin = "";

                for (String key : map.keySet()) {
                    if (map.get(key) < minCount) {
                        minCount = map.get(key);
                        keyOfMin = key;
                    }
                }

                result.put(keyOfMin, map.get(keyOfMin));
                map.remove(keyOfMin);
            }
        }

        return result;
    }

    public static Map<String, Integer> bubbleSort(Map<String, Integer> map) {
        String[] keys = map.keySet().toArray(new String[0]);
        Map<String, Integer> result = new LinkedHashMap<>();

        // sort the keys of the map by ascending order(for numbers), or lexicographic order (for words and lines)
        for (int i = 0; i < keys.length; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                if (dataType.equals("long")) {
                    if (Long.parseLong(keys[j]) < Long.parseLong(keys[i])) {
                        String temp = keys[j];
                        keys[j] = keys[i];
                        keys[i] = temp;
                    }
                } else if (dataType.equals("word") || dataType.equals("line")) {
                    if (keys[j].compareTo(keys[i]) < 0) {
                        String temp = keys[j];
                        keys[j] = keys[i];
                        keys[i] = temp;
                    }
                }
            }
        }

        // put all sorted keys and their values to a new map
        for (int i = 0; i < keys.length; i++) {
            result.put(keys[i], map.get(keys[i]));
        }
        return result;
    }

    public static void printResult() {
        String type = dataType.equals("long")? "numbers" : dataType.equals("word")? "words" : "lines";
        System.out.println(String.format("Total %s: %d.", type, total));

        if (sortingType.equals("natural")) {
            // in natural order, the order is based on the magnitude(for numbers), or length(for words and lines)

            System.out.print("Sorted data: ");
            if (dataType.equals("line")) {
                System.out.println(); // the lines must be printed in a new line
                for (String line : map.keySet()) {
                    for (int i = 0; i < map.get(line); i++) {
                        System.out.println(line);
                    }

                }
            } else if (dataType.equals("long") || dataType.equals("word")) {
                // numbers and words will be printed in a same line as the string "Sorted data:"
                for (String key : map.keySet()) {
                    for (int i = 0; i < map.get(key); i++) {
                        System.out.print(key + " "); // key can be a word or a number in string format
                    }

                }
            }
        } else if (sortingType.equals("byCount")) {
            /* byCount order means the keys (numbers, words, lines) will be printed in the order based on repeating times
             the most appear key will be printed last
             in case of repeating times equal, the numbers with smaller values will be printed first
             , while the words or lines will be printed in lexicographic order if they have equal repeating time
              */
            for (String key : map.keySet()) {

                int repeat = map.get(key);
                int percent = (int) ((double) repeat * 100) / total;;
                System.out.println(String.format("%s: %d time(s), %d%%", key, repeat, percent));
            }
        }
    }




}
