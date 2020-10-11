package Set;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: ZhengCheng
 * @Date: created in 21:48  2020/2/26
 * @Annotation: Binary Search Tree 递归写法
 */
//使泛型具有可比较性 并不是所有的数据都可以拿来比较
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    //向二分搜索树种添加新的元素e
    public void add(E e){
        /*if(root==null){
            root = new Node(e);
            size++;
        }else{
            add(root,e);
        }*/
        root = add(root,e);
    }

    //向以node为根的二分搜索树中插入元素E，递归算法
    private Node add(Node node,E e){

        /*if(e.equals(node.e)){
            return;
        }else if(e.compareTo(node.e)<0&&node.left==null){
            node.left = new Node(e);
            size++;
            return;
        }else if(e.compareTo(node.e)>0&&node.right==null){
            node.right = new Node(e);
            size++;
            return;
        }*/

        if(node==null){
            size++;
            return new Node(e);
        }

        //开始调用插入方法 (递归)
        if(e.compareTo(node.e)<0){
            node.left = add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right = add(node.right,e);
        }
        return node;
    }

    //看二分搜索树种是否包含元素e
    public boolean contains(E e){
        return contains(root,e);
    }

    //看以node为根的二分搜索树中是否包含元素e  递归算法
    private boolean contains(Node node,E e){

        if(node==null){
            return false;
        }

        if(e.compareTo(node.e)==0){
            return true;
        }else if(e.compareTo(node.e)<0){
            return contains(node.left,e);
        }else{ //e.compareTo(node.e) >0
            return contains(node.right,e);
        }
    }

    //二分搜索树的前序遍历(深度优先遍历)
    public void preOrder(){
        preOrder(root);
    }

    //前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node){

        if(node==null){
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //二分搜索树 前序遍历 非递归
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node current = stack.pop();
            System.out.println(current.e);
            if(current.right!=null){
                stack.push(current.right);
            }
            if(current.left!=null){
                stack.push(current.left);
            }
        }
    }

    //二分搜索树的中序遍历
    public void inOrder(){
        inOrder(root);
    }

    //中序遍历以node为根的二分搜索树，递归算法
    private void inOrder(Node node) {
        if(node == null){
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //二分搜索树的后序遍历
    public void postOrder(){
        postOrder(root);
    }

    //后序遍历以node为根的二分搜索树，递归算法
    private void postOrder(Node node) {

        if(node == null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //层序遍历(广度优先遍历) 运用到队列这种数据结构
    public void levelOrder(){

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node current = q.remove();
            System.out.println(current.e);
            if(current.left!=null){
                q.add(current.left);
            }
            if(current.right!=null){
                q.add(current.right);
            }
        }
    }

    //寻找二分搜素树的最小元素
    public E minimum(){
        if(size==0){
            throw new IllegalArgumentException("BTS is empty.");
        }
        return minimum(root).e;
    }

    //返回node为根的二分搜索树的最小值所在的结点
    private Node minimum(Node node) {
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }

    //寻找二分搜素树的最大元素
    public E maximun(){
        if(size==0){
            throw new IllegalArgumentException("BTS is empty.");
        }
        return maximun(root).e;
    }

    //返回node为根的二分搜索树的最大值所在的结点
    private Node maximun(Node node) {
        if(node.right==null){
            return node;
        }
        return maximun(node.right);
    }

    //从二分搜索树中删除最小值所在节点，返回最小值
    public E removeMin(){
        E ret = minimum(); //查找最小值
        root = removeMin(root);
        return ret;
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

    //从二分搜索树中删除最大值所在结点，返回最大值
    public E removeMax(){
        E ret = maximun();
        root = removeMax(root);
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最大节点
    //返回删除结点后二分搜索树的根
    private Node removeMax(Node node) {
        if(node.right==null){
            Node nodeLeft = node.left;
            node.left = null;
            size--;
            return nodeLeft;
        }
        node.right = removeMax(node.right);  //左结点移动到了被删元素的位置上
        return node;
    }

    //从二分搜索树中删除元素为e的节点
    public void remove(E e){
        remove(root,e);
    }

    //删除以node为跟的二分搜索树种值为e的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node remove(Node node,E e){
        //若节点为空，则返回
        if(node==null){
            return null;
        }

        if(e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right = remove(node.right,e);
        }else{    //e == node.e
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

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    //生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if(node == null){
            res.append(generateDepthString(depth)+"NULL\n");
            return;
        }

        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);

    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for(int i=0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }

}
