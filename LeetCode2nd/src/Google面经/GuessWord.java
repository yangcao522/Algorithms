package Google面经;
import java.util.*;

public class GuessWord {
    /**
     * https://leetcode.com/problems/guess-the-word/submissions/
     * 如果我们选择的candidate和其余所有单词中的大部分单词都match0次，那么这个candidate很有可能和target也match0次
     * 如果我们选择的candidate和其余所有单词中的少部分单词都match0次，那么这个candidate很有可能和target match不止0次
     * 所以即使这个candidate不是最终的target，那么这个candidate和target match了num次，我们通过num次就可以找到接下来的candidate pool
     * 这个candidate pool很有可能减少了很多。如果num是0的话，这个candidate pool可能会很大。因此我们要选match 0次最少的candidate，来不断的
     * 缩小candidate pool。
     */
    class Master { public int guess(String s){return 0;} }

    class Node {
        String s;
        int freq;
        public Node(String s, int freq) {
            this.s = s;
            this.freq = freq;
        }
    }

    public void findSecretWord(String[] wordList, Master master) {
        for (int i = 0; i < 10; i++) {
            Map<String, Integer> map = new HashMap<>();
            for (String w1 : wordList) {
                for (String w2 : wordList) {
                    if (match(w1, w2) == 0) map.put(w1, map.getOrDefault(w1, 0) + 1);
                }
            }

            Node cand = new Node("", 101);
            for (String key : wordList) {
                if (map.getOrDefault(key, 0) < cand.freq) {
                    cand = new Node(key, map.getOrDefault(key, 0));
                }
            }

            int matchNum = master.guess(cand.s);
            if (matchNum == 6) return;

            List<String> tmp = new ArrayList<>();
            for (String str : wordList) {
                if (match(str, cand.s) == matchNum) {
                    tmp.add(str);
                }
            }
            wordList = tmp.toArray(new String[tmp.size()]);
        }
    }

    private int match(String a, String b) {
        int ans = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                ans ++;
            }
        }
        return ans;
    }

    //leetcode minmax 解法:
    int[][] H;
    public void findSecretWord2(String[] wordlist, Master master) {
        int N = wordlist.length;
        H = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = i; j < N; ++j) {
                int match = 0;
                for (int k = 0; k < 6; ++k)
                    if (wordlist[i].charAt(k) == wordlist[j].charAt(k))
                        match++;
                H[i][j] = H[j][i] = match;
            }

        List<Integer> possible = new ArrayList();
        List<Integer> path = new ArrayList();
        for (int i = 0; i < N; ++i) possible.add(i);

        while (!possible.isEmpty()) {
            int guess = solve(possible, path);
            int matches = master.guess(wordlist[guess]);
            if (matches == wordlist[0].length()) return;
            List<Integer> possible2 = new ArrayList();
            //这边就相当于缩小范围了，从原来的possible中选出和目标match了matches次数
            for (Integer j: possible) if (H[guess][j] == matches) possible2.add(j);
            possible = possible2;
            //猜过的词
            path.add(guess);
        }

    }

    public int solve(List<Integer> possible, List<Integer> path) {
        //假如只剩下两个词，随便猜一个
        if (possible.size() <= 2) return possible.get(0);
        List<Integer> ansgrp = possible;

        //最终猜到的
        int ansguess = -1;

        //没有猜过的词遍历一边
        for (int guess = 0; guess < H.length; ++guess) {
            if (!path.contains(guess)) {
                //match次数的表
                ArrayList<Integer>[] groups = new ArrayList[7];
                for (int i = 0; i < 7; ++i) groups[i] = new ArrayList<Integer>();

                //possible中所有其他词和当前词match次数建一张表
                for (Integer j: possible) if (j != guess) {
                    groups[H[guess][j]].add(j);
                }

                //找到最大的group
                ArrayList<Integer> maxgroup = groups[0];
                for (int i = 0; i < 7; ++i) {
                    if (groups[i].size() > maxgroup.size())
                        maxgroup = groups[i];
                }

                //最大group里的全局最小
                if (maxgroup.size() < ansgrp.size()) {
                    ansgrp = maxgroup;
                    ansguess = guess;
                }
            }
        }

        return ansguess;
    }
}
