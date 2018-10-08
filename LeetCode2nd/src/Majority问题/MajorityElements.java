package Majority问题;

public class MajorityElements {
    public static int majorityElement(int[] num) {

        int majorIndex=0, count = 1;
        for(int i=1; i<num.length;i++){
            if(num[majorIndex]==num[i]){
                count++;
            }else{
                count--;
            }
            if(count==0){
                count = 1;
                majorIndex=i;
            }

        }
        return num[majorIndex];
    }

    public static void main(String[] args){
        int[] num = new int[]{1,2,2,2,1,3,3};
        System.out.println(majorityElement(num));
    }

}
