package Set;

import java.util.TreeSet;

/**
 * @Author: ZhengCheng
 * @Date: created in 20:17  2020/2/29
 * @Annotation:leetCode804
 */
class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.",
                "....","..",".---","-.-",".-..","--","-.","---",".--.",
                "--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> treeSet = new TreeSet<>();
        for(String word:words){
           StringBuilder res = new StringBuilder();
           for(int i=0;i<word.length();i++){
               res.append(codes[word.charAt(i)-'a']); //使用StringBuilder是因为这是一个单词，需要拼接起来
           }
           treeSet.add(res.toString());
        }
        return treeSet.size();
    }
}
