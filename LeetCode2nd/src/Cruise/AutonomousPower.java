package Cruise;

public class AutonomousPower {
    public double getPowerPerHour(String str) {
        String[] records = str.split("\\n");
        String[] data = records[1].split("\\s+");
        double prev_p = Double.valueOf(data[1]);
        int prev_t = Integer.valueOf(data[0]);
        boolean prev_type = data[2].equals("autonomous");
        int tt = 0;
        double tp = 0;
        for (int i = 1; i < records.length; i++) {
            data = records[i].split("\\s+");
            int timeStamp = Integer.valueOf(data[0]);
            double power = Double.valueOf(data[1]);
            boolean type = data[2].equals("autonomous");
            System.out.println(power);
            if (prev_type) {
                tp += (prev_p - power);
                tt += (timeStamp - prev_t);
            }
            prev_p = power;
            prev_type = type;
            prev_t = timeStamp;
        }
        System.out.println(tp + " " + tt);
        return tp/tt * 60;
    }

    public static void main(String[] args) {
        AutonomousPower ap = new AutonomousPower();
        System.out.print(ap.getPowerPerHour("1  98.53 manual\n" +
                "2  96.2   autonomous\n" +
                "3  93.1   autonomous\n" +
                "4  90.2   autonomous\n" +
                "5  88.3   manual\n" +
                "6  85.6   autonomous\n"));
    }
}
