package 技巧性题目;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
	public static List<String> simpleJustify(String[] words, int maxWidth){
		List<String> res = new ArrayList<>();
		int start = 0, end = 1, n = words.length;
		while(start < n) {
			int compulsorySpace = 0;
			int wordLength = words[start].length();
			while(end < n && compulsorySpace + 1 + wordLength + words[end].length() <= maxWidth) {
				compulsorySpace ++;
				wordLength += words[end].length();
				end ++;
			}
			if(end == n) {
                StringBuilder sb = new StringBuilder(words[start]);
                for(int i = start + 1; i < end; i++) sb.append(" " + words[i]);
                for(int i = wordLength + compulsorySpace; i < maxWidth; i++) sb.append(" ");
                res.add(sb.toString());
                break;
            }
			StringBuilder sb = new StringBuilder(words[start]);
            for(int i = start + 1; i < end; i++) {
                sb.append(" ");
                sb.append(words[i]);
            }
            for(int i = sb.length(); i < maxWidth; i++) {
            		sb.append(" ");
            }
            res.add(sb.toString());
            start = end;
            end = start + 1;
		}
		return res;
	}
	
	public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        //start表示每一行开头单词的指针，end表示每一行结尾单词的向后一个指针
        int start = 0, end = 1, n = words.length;
        while(start < n){
        		//单词间总的空格数
            int compulsorySpace = 0;
            //这一行中的单词总长度
            int wordLength = words[start].length();
            while(end < n && compulsorySpace + 1 + wordLength + words[end].length() <= maxWidth){
                compulsorySpace ++;
                wordLength += words[end].length();
                end ++;
            }
            //当放完最后一个单词，也就意味着这是最后一行
            if(end == n){
                StringBuilder sb = new StringBuilder(words[start]);
                for(int i = start + 1; i < end; i++) sb.append(" " + words[i]);
                for(int i = wordLength + compulsorySpace; i < maxWidth; i++) sb.append(" ");
                res.add(sb.toString());
                break;
            }
            //这一行只有一个单词
            if(end - start == 1){
                StringBuilder sb = new StringBuilder(words[start]);
                for(int i = wordLength; i < maxWidth; i++) sb.append(" ");
                res.add(sb.toString());
            }else{
            		//平均两个单词之间的空格个数
                int spaces = (maxWidth - wordLength) / (end - start - 1);
                //平均之后还剩余的空格个数
                int remains = maxWidth - wordLength - (end - start - 1) * spaces;
                StringBuilder sb = new StringBuilder(words[start]);
                for(int i = start + 1; i < end; i++){
                    for(int j = 0; j < spaces; j++) sb.append(" ");
                    if(remains-- > 0) sb.append(" ");
                    sb.append(words[i]);
                }
                res.add(sb.toString());
            }
            start = end;
            end = start + 1;
        }
        return res;
    }
	
	public static void main(String[] args) {
		String[] words = new String[] {"I", "am", "a", "super", "man"};
		List<String> res = simpleJustify(words, 6);
		for(int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
	}
}
