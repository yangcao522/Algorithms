package AmazonOA;
import java.util.*;

public class AmazonAir {
    public static List<List<Integer>> PrimeMaxProfit(
            int maxTravelDist,
            List<List<Integer>> forwardRouteList,
            List<List<Integer>> returnRouteList
    ) {
        Collections.sort(forwardRouteList, Comparator.comparingInt(a -> a.get(1)));
        Collections.sort(returnRouteList, Comparator.comparingInt(a -> a.get(1)));
        int M = forwardRouteList.size();
        int N = returnRouteList.size();
        int l = 0;
        int r = N - 1;

        int diff = Integer.MAX_VALUE;
        List<List<Integer>> ans = new ArrayList<>();
        if (maxTravelDist <= 0 || M == 0 || N == 0) return ans;
        while (l < M && r >= 0) {
            int first = forwardRouteList.get(l).get(1);
            int second = returnRouteList.get(r).get(1);
            int sum = first + second;
            if (sum <= maxTravelDist && (maxTravelDist - sum) <= diff) {
                diff = maxTravelDist - sum;
            }

            if (sum <= maxTravelDist) l ++;
            else r --;
        }

        int maxSum = maxTravelDist - diff;

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (List<Integer> ret : returnRouteList) {
            if(!map.containsKey(ret.get(1))) map.put(ret.get(1), new ArrayList<>());
            map.get(ret.get(1)).add(ret.get(0));
        }

        for (List<Integer> fowrd : forwardRouteList) {
            if (map.containsKey(maxSum - fowrd.get(1))) {
                for (int id : map.get(maxSum - fowrd.get(1))) {
                    ans.add(Arrays.asList(fowrd.get(0), id));
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> forw = Arrays.asList(Arrays.asList(4, 1000), Arrays.asList(2, 3000), Arrays.asList(3, 4000), Arrays.asList(5, 4000), Arrays.asList(1, 5000));
        List<List<Integer>> retu = Arrays.asList(Arrays.asList(1, 2000), Arrays.asList(3, 5000), Arrays.asList(2, 5000), Arrays.asList(4, 6000));
        List<List<Integer>> ans = PrimeMaxProfit(9000, forw, retu);
    }
}
