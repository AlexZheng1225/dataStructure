package linkedList;

/**
 * @Author: ZhengCheng
 * @Date: created in 16:13  2020/2/12
 * @Annotation:LinkedList实现
 */
public class LinkedList<E> {

    //定义一个Node
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

    //private Node head; //链表的头
    private Node dummyHead;//虚拟头结点
    private int size;

    public LinkedList(){
        //head = null;
        dummyHead = new Node();
        size=0;
    }

    //获取链表中元素个数
    public int getSize(){
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty(){
        return size==0;
    }

    //在链表的index(0-based)位置添加新的元素e
    //在链表中这不是一个常用的操作 练习用(考题、面试题、练习题)
    public void add(int index,E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        //判断链表是否为空
//        if(index==0){
//            addFirst(e);
//        }else {
//            Node prev = head;
//            for(int i = 0;i<index-1;i++){
//                prev = prev.next;
//            }
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
//            //prev.next = new Node(e,prev.next);
//        }

        Node prev = dummyHead;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    //在链表头添加新的元素
    public void addFirst(E e){
        add(0,e);
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

//        head = new Node(e,head);
    }

    //向链表末尾添加新元素e
    public void addLast(E e){
        add(size,e);
    }

    //获得链表的index(0-based)位置的元素e
    //在链表中这不是一个常用的操作 练习用(考题、面试题、练习题)
    public E get(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node current = dummyHead.next;
        for(int i = 0;i<index;i++){
            current = current.next;
        }
        return current.e;
    }

    //获取链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    //获得链表最后一个元素
    public E getLast(){
        Node current = dummyHead.next;
        for(int i = 0;i<size;i++){
            current = current.next;
        }
        return current.e;
    }

    //更新链表的index(0-based)位置的元素e
    //在链表中这不是一个常用的操作 练习用(考题、面试题、练习题)
    public void set(int index,E e){
        if(index<0||index>=size){
            throw new IllegalArgumentException("Set Faild.Illegal index");
        }
        Node current = dummyHead.next;
        for(int i = 0;i<index;i++){
            current = current.next;
        }
        current.e = e;
    }

    //查找链表中是否存在元素e
    public boolean contains(E e){
        Node current = dummyHead;
        for(int i = 0;i<size;i++){
            if(e.equals(current.e)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //从链表中删除index(0-based)位置的元素，并返回删除的元素
    //在链表中这不是一个常用的操作 练习用(考题、面试题、练习题)
    public E remove(int index){
        if(index<0 || index>=size){
           throw new IllegalArgumentException("Remove faild. Index is illegal.");
        }
        Node prev = dummyHead;
        for(int i = 0;i<index;i++){ //获取待删除元素之前的元素
            prev = prev.next;
        }
        Node temp = prev.next;
        prev.next = temp.next;
        temp.next = null;
        size--;  //不要忘记维护size
        return temp.e;
    }

    //从链表中删除第一个元素
    public E removeFirst(){
         return remove(0);
    }

    //从链表中删除最后一个元素
    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node current = dummyHead.next;
        while(current!=null){
            res.append(current+"-->");
            current = current.next;
        }
        res.append("NULL");
        return res.toString();
    }


}

