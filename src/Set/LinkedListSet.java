package Set;


import java.util.ArrayList;

/**
 * @Author: ZhengCheng
 * @Date: created in 16:19  2020/2/29
 * @Annotation: 基于链表实现的Set
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;  //使用的是之前写的linkedList,并不是java.util工具类下的linkedList

    public LinkedListSet() {
        list = new LinkedList<E>();
    }

    @Override
    public void add(E e) {
        //Set集合中元素不可重复，而链表中是可以重复的，需要组合逻辑进行判断
        //去重操作
        if (!list.contains(e)) {
            list.addFirst(e); //在链表头中添加元素 时间复杂度是O(1)级别
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> word1 = new ArrayList<>();
        if (FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt", word1)) {
            System.out.println("Total words:" + word1.size());
            LinkedListSet<String> linkedListSet = new LinkedListSet<String>();
            for (String word : word1) {
                linkedListSet.add(word);
            }
            System.out.println("LinkedListSet--Total different words:" + linkedListSet.getSize());
        }

        System.out.println();

        System.out.println("a-tale-of-two-cities");
        ArrayList<String> word2 = new ArrayList<>();
        if (FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\a-tale-of-two-cities.txt", word2)) {
            System.out.println("Total words:" + word2.size());
            LinkedListSet<String> linkedListSet = new LinkedListSet<String>();
            for (String word : word2) {
                linkedListSet.add(word);
            }
            System.out.println("LinkedListSet--Total different words:" + linkedListSet.getSize());
        }
    }
}
