package SegmentTree;

/**
 * @Author Alex Zheng
 * @Date 2020/10/8 15:12
 * @Annotation 303 / 307
 */
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length>0){
            Integer[] data = new Integer[nums.length];
            for (int i=0;i<nums.length;i++){
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<Integer>(data,(a,b)->a+b);
        }
    }

    //307号问题相比303 多了对数据的更新操作
    public void update(int i,int val){
        if (segmentTree==null){
            throw new IllegalArgumentException("Segment Tree is null");
        }
        segmentTree.set(i,val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree==null){
            throw new IllegalArgumentException("Segment Tree is null");
        }
        return segmentTree.query(i,j);
    }

}
