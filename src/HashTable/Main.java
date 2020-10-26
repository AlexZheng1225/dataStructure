package HashTable;

import Set.FileOperation;

import java.util.ArrayList;

/**
 * @Author Alex Zheng
 * @Date 2020/10/21 13:01
 * @Annotation
 */
public class Main {

    public static void main(String[] args) {

//        int a = 44;
//        System.out.println(((Integer)a).hashCode());
//
//        int b = -44;
//        System.out.println(((Integer)b).hashCode());
//
//        double d = 33.0;
//        System.out.println(((Double)d).hashCode());
//
//        String c = "22";
//        System.out.println(c.hashCode());

        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words:" + words.size());

            //使得BST添加进去变成链表
//            Collections.sort(words);

            //test BST
            long startTime = System.nanoTime();

            BST<String, Integer> bst = new BST<String, Integer>();
            for (String word : words) {
                if (bst.contains(word)) {
                    bst.set(word, bst.get(word) + 1);
                } else {
                    bst.add(word, 1);
                }
            }

            for (String word : words) {
                bst.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("Test BST Time: " + time + "s");

            //Test AVL Tree
            startTime = System.nanoTime();
            AVLTree<String, Integer> avl = new AVLTree<String, Integer>();
            for (String word : words) {
                if (avl.contains(word)) {
                    avl.set(word, avl.get(word) + 1);
                } else {
                    avl.add(word, 1);
                }
            }
            for (String word : words) {
                avl.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Test AVL Tree Time: " + time + "s");

            //Test RBTree
            startTime = System.nanoTime();
            RBTree<String, Integer> rbt = new RBTree<String, Integer>();
            for (String word : words) {
                if (rbt.contains(word)) {
                    rbt.set(word, rbt.get(word) + 1);
                } else {
                    rbt.add(word, 1);
                }
            }
            for (String word : words) {
                avl.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Test RBTree Time: " + time + "s");

            //Test hashTable
            startTime = System.nanoTime();
            //对于实现的hashtable来说 开多少空间实际上是很重要的
            HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
            for (String word : words) {
                if (hashTable.contains(word)) {
                    hashTable.set(word, rbt.get(word) + 1);
                } else {
                    hashTable.add(word, 1);
                }
            }
            for (String word : words) {
                hashTable.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Test HashTable Time: " + time + "s");
        }

    }
}
