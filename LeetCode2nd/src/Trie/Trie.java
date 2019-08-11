package Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Then why do we need trie? Although hash table has O(1) time complexity for looking for a key, it is not efficient in the following operations :
 * -Finding all keys with a common prefix.
 * -Enumerating a dataset of strings in lexicographical order.
 * Another reason why trie outperforms hash table, is that as hash table increases in size, there are lots of hash collisions and the search time complexity could deteriorate to O(n), where n is the number of keys inserted.
 */
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

    public Trie() {
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
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
