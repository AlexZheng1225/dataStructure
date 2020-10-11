package linkedList;

import stack.ArrayStack;
import stack.Stack;

/**
 * @Author: ZhengCheng
 * @Date: created in 13:50  2020/2/15
 * @Annotation:基于LinkedList实现栈
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        //从时间复杂度考虑 栈的时间复杂度是O(1) 故我们需要调用addFirst方法进行入栈
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }


    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for(int i =0;i<5;i++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }

}
