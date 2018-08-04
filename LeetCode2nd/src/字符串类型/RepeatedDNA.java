package 字符串类型;

import java.util.ArrayList;
import java.util.List;

public class RepeatedDNA {
	/**
	 * 这一题具有其特殊性，只有四个字母，所以我们可以用2bit来表示一个字母。
	 */
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> res = new ArrayList<>();
		int[] hash = new int[1048576];
		for(int i = 0; i <= s.length() - 10; i++) {
			String str = s.substring(i, i + 10);
			int index = encode(str);
			hash[index]++;
			if(hash[index] == 2) res.add(str);
		}
		return res;
	}
	
	public int encode(String str) {
		int num = 0;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			num = num << 2;
			if(c == 'A') num += 0;
			if(c == 'C') num += 1;
			if(c == 'G') num += 2;
			if(c == 'T') num += 3;
		}
		return num;
	}
}
