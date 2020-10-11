import BST.BST;
import Map.Map;
import Map.BSTMap;
import Map.LinkedListMap;
import Set.BSTSet;
import Set.FileOperation;
import Set.LinkedListSet;
import Set.Set;
import array.Array;
import array.Student;
import linkedList.LinkedList;
import linkedList.LinkedListQueue;
import linkedList.LinkedListStack;
import queue.ArrayQueue;
import queue.LoopQueue;
import queue.Queue;
import stack.ArrayStack;
import stack.Stack;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static Double testMap(Map<String,Integer> map, String filename){
        long start = System.nanoTime();
        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename,words)){
            System.out.println("Total words:" + words.size());
            for(String word:words){
                if(map.contains(word)){
                    map.add(word,map.get(word)+1);
                }else{
                    map.add(word,1);
                }
            }
        }
        System.out.println("Total different words:"+map.getSize());
        System.out.println("Frequency of pride:"+map.get("pride"));

        long end = System.nanoTime();
        return (end-start)/1000000000.0;
    }

    /*private static Double testSet(Set<String> set,String filename){
        long start = System.nanoTime();
        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename,words)){
            System.out.println("Total words:" + words.size());
            for(String word:words){
                set.add(word);
            }
        }
        System.out.println("Total different words:"+set.getSize());
        long end = System.nanoTime();
        return (end-start)/1000000000.0;
    }*/

    //linkedList包中测试代码 用于测试LinkedListStack和ArrayListStack的性能差距 对应CSDN文章第五篇
    /*public static Double testStack(Stack<Integer> stack, int opCount){

        long start = System.nanoTime();
        Random random = new Random();

        for(int i = 0;i<opCount;i++){
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for(int i = 0;i<opCount;i++){
            stack.pop();
        }

        long end = System.nanoTime();

        return (end-start)/1000000000.0;
    }*/

    //queue包中测试代码 用于测试ArrayQueue和LoopQueue的性能差距  对应CSDN文章第三篇/第五篇
    /*public static Double testQueue(Queue<Integer> q,int opCount){
        long start = System.nanoTime();

        for(int i = 0;i<opCount;i++){
            q.enqueue(i);
        }
        for(int i = 0;i<opCount;i++){
            q.dequeue();
        }

        long end = System.nanoTime();

        return (end-start)/1000000000.0;
    }*/

    public static void main(String[] args) {

//        BSTMap<String,Integer> bstMap = new BSTMap<String, Integer>();
//        System.out.println("Total time:"+testMap(bstMap,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));
//
//        System.out.println();
//
//        LinkedListMap<String,Integer> listMap = new LinkedListMap<String, Integer>();
//        System.out.println("Total time:"+testMap(listMap,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));

        /*BSTSet<String> bstSet = new BSTSet<String>();
        System.out.println("Total time:"+testSet(bstSet,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));

        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<String>();
        System.out.println("Total time:"+testSet(linkedListSet,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));*/


        /*BST<Integer> bst = new BST<Integer>();
        Random random = new Random();
        int n = 1000;
        for(int i = 0;i<n;i++){
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while(!bst.isEmpty()){
            nums.add(bst.removeMin());
        }
        System.out.println(nums);

        for(int i = 1;i<nums.size();i++){
            if(nums.get(i-1)>nums.get(i)){
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("removeMin test completed.");*/


//        int[] nums = {5,3,6,8,4,2};
//        for (int num:nums){
//            bst.add(num);
//        }

//        bst.preOrder();
//        System.out.println();
//
//        bst.preOrderNR();
//        System.out.println();

//        bst.inOrder();
//        System.out.println();
//
//        bst.postOrder();
//        System.out.println();

//        System.out.println(bst);

//        bst.levelOrder();
//        System.out.println();


        /*int opCount = 100000000;
        //时间复杂度差异不大
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack,opCount);
        System.out.println("ArrayStack, Time:"+time1+"s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack,opCount);
        System.out.println("LinkedListStack, Time:"+time2+"s");*/



        //LinkedList测试 对应CSDN文章第四篇
        /*LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0;i<5;i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2,666);
        System.out.println(linkedList);

        linkedList.set(6,666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);*/



        //queue包中测试代码  对应CSDN文章第三篇/第五篇
        /*int opCount = 1000000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue,opCount);
        System.out.println("ArrayQueue, Time:"+time1+"s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue,opCount);
        System.out.println("LoopQueue, Time:"+time2+"s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue,opCount);
        System.out.println("LinkedListQueue, Time:"+time3+"s");*/


        //Stack包中的测试代码 对应CSDN文章第三篇
        /*ArrayStack<Integer> stack = new ArrayStack<>();
        for(int i =0;i<5;i++){
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);*/


        //Array包中的代码测试 对应CSDN文章第二篇
        /*Array<Student> stu = new Array<>();
        stu.addFirst(new Student(1001,"Alex"));
        stu.add(1,new Student(1002,"Sam"));
        stu.addLast(new Student(1003,"Taylor"));
        System.out.println(stu);*/

        /*array.array<Integer> arr = new array.array<>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.addLast(10);
        System.out.println(arr);
        arr.removeLast();
        System.out.println(arr);*/

    }
}
