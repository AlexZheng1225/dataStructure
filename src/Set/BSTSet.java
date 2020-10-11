package Set;

import java.util.ArrayList;

/**
 * @Author: ZhengCheng
 * @Date: created in 15:23  2020/2/29
 * @Annotation:基于二分搜索树的集合类
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<E>();
    }

    @Override
    public int getSize(){
        return bst.size();
    }

    @Override
    public boolean isEmpty(){
        return bst.isEmpty();
    }

    @Override
    public void add(E e){
        bst.add(e);
    }

    @Override
    public boolean contains(E e){
        return bst.contains(e);
    }

    @Override
    public void remove(E e){
        bst.remove(e);
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> word1 = new ArrayList<>();
        if(FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt",word1)){
            System.out.println("Total words:"+word1.size());
            BSTSet<String> bstSet = new BSTSet<String>();
            for(String word:word1){
                bstSet.add(word);
            }
            System.out.println("BSTSet--Total different words:"+bstSet.getSize());
        }

        System.out.println();

        System.out.println("a-tale-of-two-cities");
        ArrayList<String> word2 = new ArrayList<>();
        if(FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\a-tale-of-two-cities.txt",word2)){
            System.out.println("Total words:"+word2.size());
            BSTSet<String> bstSet = new BSTSet<String>();
            for(String word:word2){
                bstSet.add(word);
            }
            System.out.println("BSTSet--Total different words:"+bstSet.getSize());
        }
    }

}
