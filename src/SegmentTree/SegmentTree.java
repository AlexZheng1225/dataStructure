package SegmentTree;


/**
 * @Author Alex Zheng
 * @Date 2020/10/1 14:53
 * @Annotation
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    //融合器
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        //创建线段树空间
        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);

    }

    //在treeIndex的位置创建表示区间[l......r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        //若l==r
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        //创建treeIndex的左右子树
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //创建节点左右子树 要求左右子树表示的区间范围
        //int mid = (l+r)/2 //数值足够大时可能会溢出
        int mid = l + (r - l) / 2;
        //左区间 l....mid 右区间 mid+1...r
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        //左右子树进行融合,具体怎么融合根据业务规则定
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    //返回区间[queryL,queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        //递归
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    //在以treeID为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //元素都落在右边
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
            //元素都落在左边
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

            //要求区间节点一部分落在左孩子，一部分落在右孩子
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
    }

    //将index位置的值更新为e
    public void set(int index,E e){
        if (index<0||index>=data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    //在以treeIndex为根的线段树中更新index的值为e 以及前面各个区间的值
    private void set(int treeIndex,int l,int r,int index,E e){
        if (l==r){
            tree[treeIndex]=e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r,index,e);
            //元素都落在左边
        } else if (index <= mid) {
            set(leftTreeIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    public int getSize() {
        return data.length;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < data.length; i++) {
            res.append(data[i]);
            if (i != data.length - 1) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
//        SegmentTree<Integer> segTree = new SegmentTree<Integer>(nums, new Merger<Integer>() {
        //匿名内部类
//            @Override
//            public Integer merge(Integer a, Integer b) {
//                return a + b;
//            }
//        });
        SegmentTree<Integer> segTree = new SegmentTree<Integer>(nums, ((a, b) -> a + b));
        System.out.println(segTree);
        System.out.println(segTree.query(0, 2));
    }


}
