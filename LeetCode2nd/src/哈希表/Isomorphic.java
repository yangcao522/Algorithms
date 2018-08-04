package 哈希表;

import java.util.HashMap;
import java.util.Map;

public class Isomorphic {
	/**
	 * Isomorphic Strings
	 */
	public boolean isIsomorphic(String s, String t) {
		if(s.length() != t.length()) return false;
		Map<Character, Character> mapS = new HashMap<>();
		Map<Character, Character> mapT = new HashMap<>();
		for(int i = 0; i < s.length(); i++) {
			char charS = s.charAt(i);
			char charT = t.charAt(i);
			if(mapS.containsKey(charS) && mapS.get(charS) != charT) {
				return false;
			}
			if(mapT.containsKey(charT) && mapT.get(charT) != charS) {
				return false;
			}
		}
		return true;
	}
}
