package 字符串类型;
import java.util.*;
public class Anagram {
	public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++){
            int[] count = new int[26];
            for(int j = 0; j < strs[i].length(); j++){
                count[strs[i].charAt(j) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < 26; j++){
                sb.append(count[j]);
                sb.append("#");
            }
            String key = sb.toString();
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }
}
