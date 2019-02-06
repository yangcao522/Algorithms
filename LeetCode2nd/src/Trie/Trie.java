package Trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    class TrieNode{
        TrieNode[] children;
        boolean endOfWord;
        public TrieNode(){
            children = new TrieNode[26];
            endOfWord = false;
        }
    }

    TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    //insert
    public void insert(String word) {
        TrieNode node = root;
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (node.children[arr[i] - 'a'] == null) {
                node.children[arr[i] - 'a'] = new TrieNode();
            }
            node = node.children[arr[i] - 'a'];
        }
        node.endOfWord = true;
    }

    //search
    public boolean search(String word) {
        TrieNode node = root;
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (node.children[arr[i] - 'a'] == null) {
                node.children[arr[i] - 'a'] = new TrieNode();
            }
            node = node.children[arr[i] - 'a'];
        }
        return node.endOfWord;
    }

    //find words according to prefix;
    public List<String> getWords(String prefix) {
        List<String> ans = new ArrayList<>();
        TrieNode node = root;
        char[] arr = prefix.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (node.children[arr[i] - 'a'] == null) {
                return ans;
            }
            node = node.children[arr[i] - 'a'];
        }
        StringBuilder sb = new StringBuilder(prefix);
        helper(node, ans, sb);
        return ans;
    }

    private void helper(TrieNode node, List<String> res, StringBuilder sb) {
        if (node.endOfWord) {
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                sb.append((char)(i + 'a'));
                helper(node.children[i], res, sb);
            }
        }
    }
}
