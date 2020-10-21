package Map;

import java.util.ArrayList;

/**
 * @Author Alex Zheng
 * @Date 2020/10/21 16:10
 * @Annotation 测试函数
 */
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

    public static void main(String[] args) {
        BSTMap<String,Integer> bstMap = new BSTMap<String, Integer>();
        System.out.println("BSTMap Total time:"+testMap(bstMap,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));

        System.out.println();

        LinkedListMap<String,Integer> listMap = new LinkedListMap<String, Integer>();
        System.out.println("LinkedListMap Total time:"+testMap(listMap,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));

        System.out.println();

        AVLMap<String,Integer> avlMap = new AVLMap<String, Integer>();
        System.out.println("AVLMap Total time:"+testMap(avlMap,"E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt"));
    }

}
