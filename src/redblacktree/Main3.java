package redblacktree;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author Alex Zheng
 * @Date 2020/10/24 13:30
 * @Annotation 顺序存储
 */
public class Main3 {
    public static void main(String[] args) {
        int n = 20000000;
        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i=0;i<n;i++){
            testData.add(i);
        }

        //BST不测试 顺序操作下会退化成链表

        //Test AVL Tree
        long startTime = System.nanoTime();
        AVLTree<Integer, Integer> avl = new AVLTree<Integer, Integer>();
        for (Integer x:testData){
            avl.add(x,null);
        }
        long endTime = System.nanoTime();
        double time = (endTime-startTime)/1000000000.0;
        System.out.println("Test AVL Tree Time: "+time+"s");

        //Test RBTree
        startTime = System.nanoTime();
        RBTree<Integer, Integer> rbTree = new RBTree<Integer, Integer>();
        for (Integer x:testData){
            rbTree.add(x,null);
        }
        endTime = System.nanoTime();
        time = (endTime-startTime)/1000000000.0;
        System.out.println("Test RBTree Time: "+time+"s");
    }
}
