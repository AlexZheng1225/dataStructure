package linkedList;

/**
 * @Author: ZhengCheng
 * @Date: created in 22:37  2020/2/25
 * @Annotation:
 */
public class Sum {

    public static int sum(int[] arr){
        return sum(arr,0);
    }

    //计算arr[l....n] 这个区间内所有数字的和
    private static int sum(int[] arr,int l){
        if(l==arr.length){
            return 0;
        }
        return arr[l]+sum(arr,l+1);
    }

    public static void main(String[] args) {

        int[] nums = {1,2,6,2,3,9,2,5,2};
        System.out.println(sum(nums));

    }

}
