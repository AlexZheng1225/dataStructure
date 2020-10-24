package redblacktree;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author Alex Zheng
 * @Date 2020/10/24 13:23
 * @Annotation 测试RBTree在添加操作中相比AVL Tree是否有优势
 */
public class Main2 {
    public static void main(String[] args) {
        int n = 20000000;
        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i=0;i<n;i++){
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        //Test BST
        long startTime = System.nanoTime();
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        for (Integer x:testData){
            bst.add(x,null);
        }
        long endTime = System.nanoTime();
        double time = (endTime-startTime)/1000000000.0;
        System.out.println("Test BST Time: "+time+"s");

        //Test AVL Tree
        startTime = System.nanoTime();
        AVLTree<Integer, Integer> avl = new AVLTree<Integer, Integer>();
        for (Integer x:testData){
            avl.add(x,null);
        }
        endTime = System.nanoTime();
        time = (endTime-startTime)/1000000000.0;
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
