package redblacktree;

import Set.FileOperation;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;

/**
 * @Author Alex Zheng
 * @Date 2020/10/22 16:02
 * @Annotation 基于K-V的二分搜索树实现的RBTree
 */
public class RBTree<K extends Comparable<K>,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;
        //添加颜色
        public boolean color;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private int size;
    private Node root;

    public RBTree(){
        root = null;
        size=0;
    }

    //判断节点的颜色
    private boolean isRed(Node node){
        if (node==null){
            return BLACK;
        }
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    //左旋转(要添加的节点在黑色节点的右孩子位置，则需要进行左旋转)
    private Node leftRotate(Node node){
        Node x = node.right;
        //左旋转
        node.right = x.left;
        x.left = node;
        //维持节点颜色
        x.color = node.color;
        //此时是临时四节点
        node.color = RED;
        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    //右旋转
    private Node rightRotate(Node node){
        Node x = node.left;
        //右旋转
        node.left = x.right;
        x.right = node;
        //维持节点颜色
        x.color = node.color;
        //此时是临时四节点
        node.color = RED;
        return x;
    }

    //颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = node.right.color = BLACK;
    }

    //向红黑树中添加新的元素(key，value)
    public void add(K key, V value) {
        root = add(root,key,value);
        //最终根节点为黑色节点
        root.color = BLACK;
    }

    //以Node为根的红黑树中插入元素(key，value)，递归算法
    //返回插入新节点后红黑树的根
    private Node add(Node node, K key, V value) {
        if(node==null){
            size++;
            return new Node(key,value);
        }

        if(key.compareTo(node.key)<0){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key)>0){
            node.right = add(node.right,key,value);
        }else{ //key==node.key
            //当添加的key映射中已经有时
            node.value = value;
        }

        //维护的时机与AVL Tree一样，添加节点后回溯向上维护

        //判断是否需要左旋转  （左右子树都为红节点时，则要进行颜色翻转）
        if (isRed(node.right)&&!isRed(node.left)){
            node = leftRotate(node);
        }

        //判断是否需要右旋转
        if (isRed(node.left)&&isRed(node.left.left)){
            node = rightRotate(node);
        }

        //判断是否需要进行红色翻转
        if(isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }

        return node;
    }

    //返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node,K key){
        if(node==null){
            return null;
        }
        if(key.compareTo(node.key)==0){
            return node;
        }else if(key.compareTo(node.key)<0){
            return getNode(node.left,key);
        }else{
            return getNode(node.right,key);
        }
    }


    public V remove(K key) {
        Node node = getNode(root,key);
        if(node==null){
            return null;
        }
        root = remove(root,key);
        return node.value;
    }

    private Node remove(Node node,K key){
        if(node==null){
            return null;
        }

        if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
        }else{ //key==node.key

            //待删除节点左子树为空的情况
            if(node.left==null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //待删除节点左右子树都不为空
            //找到比待删除节点要大的最小的节点，即待删除右子树最小的节点
            //用此节点顶替待删除结点
            Node successor = minimum(node.right);  //找到最小值
            successor.right = removeMin(node.right);  //将右子树的最小值删除并返回删除后的结果
            successor.left = node.left;
            //在此处不用维护size，因为在调用removeMin方法的时候已经对size进行了处理
            node.left = node.right = null;
            return successor;
        }
        return node;

    }

    //返回node为根的二分搜索树的最小值所在的结点
    private Node minimum(Node node) {
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除结点后二分搜索树的根
    private Node removeMin(Node node) {
        if(node.left==null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);  //右节点移动到了被删元素的位置上
        return node;
    }


    public boolean contains(K key) {
        return getNode(root,key)!=null;
    }



    public V get(K key) {
        Node node = getNode(root,key);
        if(node==null){
            return null;
        }
        return node.value;
    }


    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if(node==null){
            throw new IllegalArgumentException(key+"does't exist!");
        }
        node.value = newValue;
    }



    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return false;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");
        ArrayList<String> word1 = new ArrayList<>();
        if (Set.FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt", word1)) {
            System.out.println("Total words:" + word1.size());
            RBTree<String,Integer> map = new RBTree<String,Integer>();
            for (String word : word1) {
                if(map.contains(word)){
                    map.set(word,map.get(word)+1);
                }else{
                    map.add(word,1);
                }
            }
            System.out.println("Total different words:" + map.getSize());
            System.out.println("Frequency of pride:"+map.get("pride"));
        }

        System.out.println();

//        System.out.println("a-tale-of-two-cities");
//        ArrayList<String> word2 = new ArrayList<>();
//        if (FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\a-tale-of-two-cities.txt", word2)) {
//            System.out.println("Total words:" + word2.size());
//            BST<String,Integer> map = new BST<String,Integer>();
//            for (String word : word2) {
//                if(map.contains(word)){
//                    map.set(word,map.get(word)+1);
//                }else{
//                    map.add(word,1);
//                }
//            }
//            System.out.println("Total different words:" + map.getSize());
//            System.out.println("Frequency of cities:"+map.get("cities"));
//        }
    }
}
