package Trie.question211;

import Trie.Trie;

import java.util.TreeMap;

/**
 * @Author Alex Zheng
 * @Date 2020/10/12 13:46
 * @Annotation
 */
public class WordDictionary {

    private class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c)==null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord){
            cur.isWord = true;
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root,word,0);
    }

    private boolean search(Node node,String word,int index){
        if (index==word.length()){
            return node.isWord;
        }

        char c = word.charAt(index);
        if (c!='.'){
            if (node.next.get(c)==null){
                return false;
            }
            return search(node.next.get(c),word,index+1);
        }else {
            for (char nextChar:node.next.keySet()){
                if (search(node.next.get(nextChar),word,index+1)){
                    return true;
                }
            }
            return false;
        }

    }

}
