package View;

import java.util.Map;

public class ResultPrinter {
    public void printResults(Map<String, Integer> results) {
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
