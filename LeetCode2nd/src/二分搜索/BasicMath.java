package 二分搜索;

public class BasicMath {
	/**
	 * 1.整数求根
	 * 2.求根精确版本
	 * 3.指数
	 */
	
	/**
	 * 69. Sqrt(x)
	 */
	public int mySqrt(int x) {
        if(x == 0) return 0;
        long left = 0;
        long right = x;
        while(left <= right){
            long mid = (left + right)/2;
            if(mid*mid == x) return (int)mid;
            else{
                if(mid*mid > x){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }
        }
        return (int)right;
    }
	
	/**
	 * follow up ： 精确求平方根
	 * http://blog.csdn.net/xusiwei1236/article/details/25657611
	 * 69. Sqrt(x)
	 */
	private final static Double EPSILON = 0.0000001;
	public double mySqrt(double x) {
		double left = 0.0;
		double right = x + 1/4;
		double mid = 0.0;
		while(true) {
			mid = (left + right)/2;
			if( mid - left < EPSILON || right - mid < EPSILON) break;
			if((mid * mid - x) * (left * left - x) < 0) {
				right = mid;
			}else {
				left = mid;
			}
		}
		return mid;
	}
	
	/**
	 * 50. Pow(x, n)
	 */
	public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n % 2 == 0){
            double tmp = myPow(x, n/2);
            return tmp * tmp;
        }else{
            double tmp = myPow(x, n/2);
            if(n >= 0)
                return x * tmp * tmp;
            else
                return (1/x) * tmp * tmp;
        }
    }
}

