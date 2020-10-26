package HashTable;

/**
 * @Author Alex Zheng
 * @Date 2020/10/25 14:20
 * @Annotation 387 leetcode
 */
class Solution {
    public int firstUniqChar(String s) {
        int[] frep = new int[26];
        for (int i = 0; i < s.length(); i++) {
            frep[s.charAt(i) - 'a'] += 1;
        }
        for(int i=0;i<s.length();i++){
            if(frep[s.charAt(i) - 'a']==1){
                return i;
            }
        }
        return -1;
    }
}