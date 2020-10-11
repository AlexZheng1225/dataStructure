package Map;

import Set.FileOperation;

import java.util.ArrayList;

/**
 * @Author: ZhengCheng
 * @Date: created in 22:14  2020/2/29
 * @Annotation: 基于链表实现的映射Map--链表结点有三个值
 *对于Map来说，key是唯一的。
 */
public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key,V value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key,null,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString() {
            return key.toString()+":"+value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size=0;
    }

    //返回传来key的值所对应的节点相应的引用(返回节点)
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur!=null){
            if(cur.key.equals(key)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if(node==null){
            //从队首插入元素
            dummyHead.next = new Node(key,value,dummyHead.next);
            size++;
        }else{
            //认为用户当前传来的数据是用户真正想要的数据
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while(prev.next!=null){
            //找到待删除元素的前一个元素
            if(prev.next.key.equals(key)){
                break;
            }
            prev = prev.next;
        }
        if(prev.next!=null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key)!=null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        if(node==null){
            return null;
        }
        return node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if(node==null){
            throw new IllegalArgumentException(key+" doesn't exits!");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> word1 = new ArrayList<>();
        if (FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt", word1)) {
            System.out.println("Total words:" + word1.size());
            LinkedListMap<String,Integer> map = new LinkedListMap<String,Integer>();
            for (String word : word1) {
                if(map.contains(word)){
                    map.add(word,map.get(word)+1);
                }else{
                    map.add(word,1);
                }
            }
            System.out.println("Total different words:" + map.getSize());
            System.out.println("Frequency of pride:"+map.get("pride"));
            System.out.println("Frequency of pride:"+map.get("is"));
        }

        System.out.println();

        System.out.println("a-tale-of-two-cities");
        ArrayList<String> word2 = new ArrayList<>();
        if (FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\a-tale-of-two-cities.txt", word2)) {
            System.out.println("Total words:" + word2.size());
            LinkedListMap<String,Integer> map = new LinkedListMap<String,Integer>();
            for (String word : word2) {
                if(map.contains(word)){
                    map.add(word,map.get(word)+1);
                }else{
                    map.add(word,1);
                }
            }
            System.out.println("Total different words:" + map.getSize());
            System.out.println("Frequency of cities:"+map.get("cities"));
        }



    }

}
