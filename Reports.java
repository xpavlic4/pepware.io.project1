import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Reports {
    private int totalCount;
    private Map<Integer, Integer> basic;
    private Map<Integer, Integer> delux;
    private Map<Integer, Integer> totals;

    public static void main(String[] args) throws IOException {
        new Reports().run();
    }

    private void run() throws IOException {
         basic = new HashMap<Integer, Integer>();
         delux = new HashMap<Integer, Integer>();
        totals = new HashMap<Integer, Integer>();
        int basicCount = readIntoMap(basic, "/Basic.txt");
        System.out.println("basic: " + basicCount);
        int deluxCount = readIntoMap(delux, "/Delux.txt");
        System.out.println("delux: " + deluxCount);

        totalCount = readIntoMap(totals, "/Total.txt");
        System.out.println("Total: " + totalCount);

        yearReport();
        monthReport();
        weeklyReport();
        
    }

    private void weeklyReport() {
        report(7, "Sum last week: ");
    }

    private void monthReport() {
        report(30, "Sum last month: ");
    }

    private void yearReport() {
        report(356, "Sum last year: ");
    }

    private void report(int groupBy, String header) {
        int sum = 0;
        
        for (int i = 0; i<groupBy; i++) {
            sum = sum + totals.get(totalCount - i - 1);
        }
        System.out.println(header + sum);
    }

    private int readIntoMap(Map<Integer, Integer> basic, String filename) throws IOException {
        InputStream resourceAsStream = Reports.class.getResourceAsStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line = null;
        line = br.readLine(); //header
        if (!line.contains(":")) {
            throw new IllegalStateException("header is missing");
        }
        int i = 0;
        do  {
            i++;
            line = br.readLine();
            if (line != null) {
                basic.put(i, Integer.parseInt(line));
            }
        } while (line != null);
        return i;
    }
}
