package AmazonOA;

import java.util.*;

public class Music2Sum {
    public static List<Integer> getPair(int duration, List<Integer> songs) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = duration - 30;
        int longestSong = Integer.MIN_VALUE;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            if (map.containsKey(sum - songs.get(i))) {
                if (songs.get(i) > longestSong) {
                    ans = Arrays.asList(map.get(sum - songs.get(i)), i);
                    longestSong = songs.get(i);
                }

                if (sum - songs.get(i) > longestSong) {
                    ans = Arrays.asList(map.get(sum - songs.get(i)), i);
                    longestSong = sum - songs.get(i);
                }
            }
            map.put(songs.get(i), i);
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
