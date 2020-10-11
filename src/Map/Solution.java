package Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @Author: ZhengCheng
 * @Date: created in 15:14  2020/3/6
 * @Annotation:LeetCode--350
 */
public class Solution {
    public static int[] intersect(int[] nums1, int[] nums2) {

        //老师的思路
        TreeMap<Integer,Integer> map = new TreeMap<>();

        for(int num1:nums1){
            if(!map.containsKey(num1)){
                map.put(num1,1);
            }else{
                map.put(num1,map.get(num1)+1);
            }
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int num2:nums2){
            if(map.containsKey(num2)){
                arrayList.add(num2);
                map.put(num2,map.get(num2)-1);
                if(map.get(num2)==0){
                    map.remove(num2);
                }
            }
        }



        int[] temp = new int[arrayList.size()];
        for(int i=0;i<arrayList.size();i++){
            temp[i] = arrayList.get(i);
        }

        return temp;
    }

    public static void main(String[] args) {
        int[] i = {1,3,5,7,7,11,11};
        int[] j = {10,9,8,7,7,11,11};
        int[] x = intersect(i,j);
        for(int s:x){
            System.out.print(s+",");
        }
    }

}
