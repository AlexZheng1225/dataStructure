package Trie;
import java.util.TreeMap;//基于红黑树

/**
 * @Author Alex Zheng
 * @Date 2020/10/9 13:30
 * @Annotation
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
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    //获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }



}
