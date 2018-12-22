package Integer操作相关;

public class IntegerToEnglishWord {
    static String[] NUMS = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static String[] TENS = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety", "Hundred"};
    static String[] THOU = new String[]{"", "Thousand", "Million", "Billion"};
    public static String numberToWords(int num) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        if(num == 0) return "Zero";
        while(num != 0){
            int cur = num % 1000;
            if(cur != 0){
                sb.insert(0, helper(cur) + " " + THOU[count]);
                sb.insert(0, " ");
            }
            num = num / 1000;
            count ++;
        }
        return sb.toString().trim();
    }

    private static String helper(int cur){
        String after;
        String before;
        int rst = cur % 100;
        if(rst < 20){
            after = NUMS[rst];
        }else{
            int ten = rst / 10;
            int dig = rst % 10;
            after = (TENS[ten] + " " + NUMS[dig]).trim();
        }
        cur = cur / 100;
        if(cur == 0) before = "";
        else before = NUMS[cur] + " " + "Hundred";
        return (before + " " + after).trim();
    }
}
