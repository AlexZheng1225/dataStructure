package Set;

import java.util.ArrayList;

/**
 * @Author Alex Zheng
 * @Date 2020/10/21 16:26
 * @Annotation 测试不同数据结构实现的Set的性能
 */
public class Main {
    private static Double testSet(Set<String> set,String filename){
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
    }

    public static void main(String[] args) {
        BSTSet<String> bstSet = new BSTSet<String>();
        System.out.println("BSTSet Total time:"+testSet(bstSet,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));

        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<String>();
        System.out.println("LinkedListSet Total time:"+testSet(linkedListSet,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));

        System.out.println();

        AVLSet<String> avlSet = new AVLSet<String>();
        System.out.println("AVLSet Total time:"+testSet(avlSet,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));
    }
}
