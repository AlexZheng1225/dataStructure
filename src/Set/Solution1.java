package Set;

import queue.Array;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @Author: ZhengCheng
 * @Date: created in 13:57  2020/3/6
 * @Annotation:LeetCode--349
 */
public class Solution1 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        //自己的答案 8ms 39.7MB
        /*TreeSet<Integer> treeSet1 = new TreeSet<>();
        TreeSet<Integer> treeSet2 = new TreeSet<>();

        for(int num1:nums1){
            treeSet1.add(num1);xq
        }

        for(int num2:nums2){
            if(treeSet1.contains(num2)){
                treeSet2.add(num2);
            }
        }

        int[] temp = new int[treeSet2.size()];

        for(int i=0;i<temp.length;i++){
            temp[i] = treeSet2.first();
            treeSet2.remove(temp[i]);
        }

        return temp;*/

        //老师的答案 8ms 39.4MB
        TreeSet<Integer> treeSet1 = new TreeSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for(int num1:nums1){
            treeSet1.add(num1);
        }

        for(int num2:nums2){
            if(treeSet1.contains(num2)){
                list.add(num2);
                treeSet1.remove(num2);
            }
        }

        int[] temp = new int[list.size()];

        for(int i=0;i<temp.length;i++){
            temp[i] = list.get(i);
        }

        return temp;
    }

    public static void main(String[] args) {
        int[] i = {1,3,5,7,9};
        int[] j = {10,9,8,7,3,6,5,4,2,1,0};
        int[] x = intersection(i,j);
        for(int s:x){
            System.out.print(s+",");
        }
    }
}
