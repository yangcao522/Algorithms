package 贪心算法;

public class Test {
	public static int arrangeCoins(int n) {
        long start = 0;
        long end = (long)n;
        long mid = 0;
        
        while(start + 1 < end){
            mid = start + (end - start) / 2;
            if(mid * (mid + 1) < 2 * n){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(end * (end + 1) <= 2 * n){
            return (int)end;
        }else{
            return (int)start;
        }
    }
	
	public static void main(String[] args) {
		System.out.println(arrangeCoins(1804289383));
	}
}
