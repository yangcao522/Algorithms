package AmazonOA;
import java.util.*;

public class ReorderLogs {
    public static List<String> orderLogs(List<String> logs) {
        Collections.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] a = o1.split(" ", 2);
                String[] b = o2.split(" ", 2);
                boolean typeA = !Character.isDigit(a[1].charAt(0));
                boolean typeB = !Character.isDigit(b[1].charAt(0));
                if (typeA && typeB) {
                    int comRes = a[1].compareTo(b[1].toString());
                    if (comRes == 0) return a[0].compareTo(b[0].toString());
                    return comRes;
                } else if (!typeA && typeB) {
                    return 1;
                } else if (typeA && !typeB) {
                    return -1;
                } else return 0;
            }
        });

        return logs;
    }

    public static void main(String[] args) {
        List<String> logs = Arrays.asList();
    }
}
