package hSt;



import java.util.*;

public class Main {

    static Map<String, Integer> map = new LinkedHashMap<>();
    static int total = 0;
    static String dataType = "word";
    static String sortingType = "natural";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> cmdArgs = Arrays.asList(args);
        if (cmdArgs.contains("-dataType")) {
            dataType = cmdArgs.get(cmdArgs.indexOf("-dataType") + 1);
        }
        if (cmdArgs.contains("-sortingType")) {
            sortingType = cmdArgs.get(cmdArgs.indexOf("-sortingType") + 1);
        }

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
            } else if (dataType.equals("word") || dataType.equals("long")) {
                String[] parts = line.split("\\s+");

                for (String str : parts) {
                    if (!map.containsKey(str)) {
                        map.put(str, 1);
                    } else {
                        map.put(str, map.get(str) + 1);
                    }
                    total ++;
                }
            }
        }

        map = sort();
        printResult();
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
            // after add a key and its value to the result map, it will be removed from the original map


            map = bubbleSort(map);

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
        
        for (int i = 0; i < keys.length; i++) {
            result.put(keys[i], map.get(keys[i]));
        }
        return result;
    }

    public static void printResult() {
        String type = dataType.equals("long")? "numbers" : dataType.equals("word")? "words" : "lines";
        System.out.println(String.format("Total %s: %d.", type, total));
        if (sortingType.equals("natural")) {
            System.out.print("Sorted data: ");

            if (dataType.equals("line")) {
                System.out.println();
                for (String line : map.keySet()) {
                    for (int i = 0; i < map.get(line); i++) {
                        System.out.println(line);
                    }

                }
            } else if (dataType.equals("long") || dataType.equals("word")) {
                for (String key : map.keySet()) {
                    for (int i = 0; i < map.get(key); i++) {
                        System.out.print(key + " "); // key can be a word or a number in string format
                    }

                }
            }
        } else if (sortingType.equals("byCount")) {
            
            for (String key : map.keySet()) {
                int repeat = map.get(key);
                int percent = (int) ((double) repeat * 100) / total;;
                System.out.println(String.format("%s: %d time(s), %d%%", key, repeat, percent));
            }
        }
    }




}
