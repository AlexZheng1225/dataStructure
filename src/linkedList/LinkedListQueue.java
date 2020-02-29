package linkedList;

import queue.Queue;

/**
 * @Author: ZhengCheng
 * @Date: created in 16:30  2020/2/15
 * @Annotation:使用链表实现队列 ———— 不使用之前实现的LinkedList 因为没有尾节点 （出队一方为队首，入队一方为队尾）
 */
public class LinkedListQueue<E> implements Queue<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }

    }

    private Node head,tail; //创建头节点和尾结点
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if(tail==null){
            tail = new Node(e);
            head = tail;
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Connot dequeue from an queue.");
        }
        Node tempNod = head;
        head = head.next;
        tempNod.next = null;
        //需要判断删除结点后链表是否为空 如果为空 则需要维护尾结点
        if(head==null){
            tail = null;
        }
        size--;
        return tempNod.e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Get faild. Queue is empty.");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur!=null){
            res.append(cur+"-->");
            cur = cur.next;
        }

        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if(i%3==2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
