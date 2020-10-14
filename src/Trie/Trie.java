package Trie;

import java.util.ArrayList;
import java.util.TreeMap;//基于红黑树

/**
 * @Author Alex Zheng
 * @Date 2020/10/9 13:30
 * @Annotation Tire 可以解决Leetcode 208号问题
 */
public class Trie {

    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    //指的是单词数量
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    //向Tire中添加一个新的单词word（非递归）
    public void add(String word){
        Node cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            //cur.next是一个TreeMap，get(c)即是获取Map中key为c的Node节点
            if (cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            //cur.next.get(c) 即获得下一个key为c的Node节点
            cur = cur.next.get(c);
        }
        //确保最后一个节点（isWord为false，这样表示这个单词之前没被存储过）
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    //查询单词word是否在Tire中（非递归）
    public boolean contains(String word){
        Node cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (cur.next.get(c)==null){
                return false;
            }
            cur = cur.next.get(c);
        }
        //比如Tire中有panda这个单词，但是我们现在查pan 那么很显然pan没有存进去但又是panda的一部分
        //此时返回cur.isWord
        return cur.isWord;
    }

    //查询是否在Tire中有单词以prefix为前缀(前缀：一个单词的所有字母本身也是这个单词的前缀)
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    //获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> word1 = new ArrayList<>();
        if(FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt",word1)){
            long stratTime = System.nanoTime();
            BSTSet<String> bstSet = new BSTSet<String>();
            for(String word:word1){
                bstSet.add(word);
            }
            for(String word:word1){
                bstSet.contains(word);
            }
            long endTime = System.nanoTime();
            double time = (endTime-stratTime)/100000000.0;
            System.out.println("BSTSet--Total different words:"+bstSet.getSize());
            System.out.println("BST Time:"+time+"s");
            System.out.println();

            stratTime = System.nanoTime();
            Trie tire = new Trie();
            for(String word:word1){
                tire.add(word);
            }
            for(String word:word1){
                tire.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime-stratTime)/100000000.0;
            System.out.println("Trie--Total different words:"+bstSet.getSize());
            System.out.println("Tire Time:"+time+"s");
        }




    }

}
